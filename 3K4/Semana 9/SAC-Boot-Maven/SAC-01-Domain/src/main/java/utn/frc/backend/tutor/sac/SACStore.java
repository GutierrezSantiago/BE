package utn.frc.backend.tutor.sac;

import utn.frc.backend.tutor.sac.domain.*;
import utn.frc.backend.tutor.sac.lib.store.IntStore;
import utn.frc.backend.tutor.sac.lib.store.exceptions.StoreableRelationException;
import utn.frc.backend.tutor.sac.lib.store.exceptions.StoreableValidationException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SACStore {

    public enum FetchType { Lazy, Eager } // y "Save"Type :)

    private IntStore<Persona> personas = new IntStore<>(false, true);
    private IntStore<Persona> alumnos = new IntStore<>(false);
    private IntStore<Persona> docentes = new IntStore<>(false);
    private IntStore<Materia> materias = new IntStore<>(false, true);
    private IntStore<Curso> cursos = new IntStore<>(false, true);

    public boolean existsPersona(Integer pid) {
        return personas.exists(pid);
    }

    public Persona findPersona(Integer pid) {
        return new Persona().update(personas.find(pid)); // null
    }

    public Set<Persona> findPersonas() {
        return personas.findAll().stream().map(persona -> persona.clone()).collect(Collectors.toSet());
    }

    public Persona savePersona(Persona persona) throws StoreableValidationException {
        personas.save(persona);
        if (alumnos.exists(persona.getPid())) {
            alumnos.save(persona);
        }
        if (docentes.exists(persona.getPid())) {
            docentes.save(persona);
        }
        return persona;
    }

    public Persona removePersona(Integer pid) throws StoreableRelationException {
        if (!personas.exists(pid)) {
            return null;
        }

        if (alumnos.exists(pid)) {
            throw new StoreableRelationException("Alumno asociado. No se puede eliminar.");
        }

        if (docentes.exists(pid)) {
            throw new StoreableRelationException("Docente asociado. No se puede eliminar.");
        }

        return personas.remove(pid);
    }

    public boolean existsAlumno(Integer pid) {
        return alumnos.exists(pid);
    }

    public Alumno findAlumno(Integer pid) {
        return new Alumno().update((Alumno)alumnos.find(pid)); // null
    }

    public Set<Alumno> findAlumnos() {
        return alumnos.findAll().stream().map(alumno -> (Alumno) alumno.clone()).collect(Collectors.toSet());
    }

    public Alumno saveAlumno(Alumno alumno) throws StoreableValidationException {
        Persona persona = personas.save(alumno);
        alumnos.save(alumno);
        if (docentes.exists(persona.getPid())) {
            docentes.save(persona);
        }
        return alumno;
    }

    public Alumno saveAlumno(Integer pid, String legajo) throws StoreableValidationException {
        Alumno alumno = new Alumno(personas.find(pid), legajo);
        alumnos.save(alumno);
        return alumno;
    }

    public Alumno removeAlumno(Integer pid) throws StoreableRelationException {
        if (!alumnos.exists(pid)) {
            return null;
        }

        for (Curso curso : cursos.findAll()) {
            if (curso.getAlumnos().stream().anyMatch(alumno -> alumno.getPid() == pid)) {
                throw new StoreableRelationException("Alumno matriculado. No se puede eliminar.");
            }
        }

        return (Alumno) alumnos.remove(pid);
    }

    public boolean existsDocente(Integer pid) {
        return docentes.exists(pid);
    }

    public Docente findDocente(Integer pid) {
        return findDocente(pid, FetchType.Lazy);
    }

    public Docente findDocente(Integer pid, FetchType mode) {
        Docente data = (Docente) docentes.find(pid);
        Docente docente = new Docente().update(data); // null

        if (docente != null && mode == FetchType.Eager) {
            docente.setMaterias(
                    data.getMaterias().stream()
                    .map(materia -> new Materia(materia))
                    .collect(Collectors.toSet()));

        }

        return docente;
    }

    public Set<Materia> findDocenteMaterias(Integer pid) {
        Docente docente = (Docente) docentes.find(pid);

        if (docente == null) {
            return new HashSet<>();
        }

        return docente.getMaterias().stream()
                .map(materia -> new Materia(materia))
                .collect(Collectors.toSet());
    }

    public Set<Docente> findDocentes() {
        return findDocentes(FetchType.Lazy);
    }

    public Set<Docente> findDocentes(FetchType mode) {
        return docentes
                .findAll().stream()
                .map(data -> {
                    Docente docente = new Docente((Docente) data);

                    if (mode == FetchType.Eager) {
                        docente.setMaterias(
                                ((Docente) data).getMaterias().stream()
                                        .map(materia -> new Materia(materia))
                                        .collect(Collectors.toSet())
                        );
                    }

                    return docente;
                })
                .collect(Collectors.toSet());
    }

    public Docente saveDocente(Docente docente) throws StoreableValidationException, StoreableRelationException {
        return saveDocente(docente, FetchType.Lazy);
    }

    public Docente saveDocente(Docente docente, FetchType mode) throws StoreableValidationException, StoreableRelationException {
        Persona persona = personas.save(docente);
        docentes.save(docente);
        if (alumnos.exists(persona.getPid())) {
            alumnos.save(persona);
        }

        if (mode == FetchType.Eager) {
            // valido que las materias existan
            if (docente.getMaterias().stream().anyMatch(materia -> !materias.exists(materia.getMid()))) {
                throw new StoreableRelationException("Materia inexistente");
            }

            // para c/ materia a desvincular, que no haya curso con par (docente,materia)
            Docente docData = (Docente) docentes.find(docente.getPid());
            Set<Materia> matData = docData.getMaterias();

            Set<Materia> aBorrar = matData.stream().filter(materia -> !docente.getMaterias().contains(materia)).collect(Collectors.toSet());
            for (Materia materia : aBorrar) {
                if (cursos.findAll().stream().anyMatch(curso -> curso.getDocente().equals(docData) && curso.getMateria().equals(materia))) {
                    throw new StoreableRelationException("Curso asignado. No se puede eliminar.");
                }
            }

            // desvinculo docente de materias "eliminadas"
            aBorrar.stream().forEach(materia -> materia.getDocentes().remove(docData));

            // desvinculo materias de docente
            aBorrar.stream().forEach(materia -> docData.getMaterias().remove(materia));

            // cargo materias a vincular:
            //  - originales almacenadas por id
            //  - no se validan las materias recibidas c/ el docente
            //  - to-do dato modificado (o basura) será descartado
            //  - finalemente estas materias (copias) serán vinculadas
            //    al docente retornado
            docente.getMaterias().stream().forEach(materia -> {
                // vinculo materia al docente
                docData.getMaterias().add(materias.find(materia.getMid()));

                // vinculo docente a la materia
                materias.find(materia.getMid()).getDocentes().add(docData);
            });
            docente.setMaterias(
                    docData
                            .getMaterias().stream()
                            .map(materia -> new Materia(materia))
                            .collect(Collectors.toSet())
            );
        }

        return docente;
    }

    public Docente saveDocente(Integer pid, String legajo) throws StoreableValidationException {
        Docente docente = new Docente(personas.find(pid), legajo);
        docentes.save(docente);
        return docente;
    }

    public Docente removeDocente(Integer pid) throws StoreableRelationException {
        if (!docentes.exists(pid)) {
            return null;
        }

        Docente docente = (Docente) docentes.find(pid);

        if (cursos.findAll().stream().anyMatch(curso -> curso.getDocente().equals(docente))) {
            throw new StoreableRelationException("Curso asignado. No se puede eliminar.");
        }

        // desvinculo docente de materias
        docente.getMaterias().stream().forEach(materia -> materia.getDocentes().remove(docente));

        // desvinculo materias de docente
        docente.getMaterias().clear();

        return (Docente) docentes.remove(pid);
    }

    public boolean existsMateria(Integer mid) {
        return materias.exists(mid);
    }

    public Materia findMateria(Integer mid) {
        return findMateria(mid, FetchType.Lazy);
    }

    public Materia findMateria(Integer mid, FetchType mode) {
        Materia data = materias.find(mid);
        Materia materia = new Materia().update(data); // null

        if (materia != null && mode == FetchType.Eager) {
            materia.setDocentes(
                    data.getDocentes().stream()
                            .map(docente -> new Docente(docente))
                            .collect(Collectors.toSet())
            );
        }

        return materia;
    }

    public Set<Docente> findMateriaDocentes(Integer mid) {
        Materia materia = materias.find(mid);

        if (materia == null) {
            return new HashSet<>();
        }

        return materia.getDocentes().stream()
                .map(docente -> new Docente(docente))
                .collect(Collectors.toSet());
    }

    public Set<Materia> findMaterias() {
        return findMaterias(FetchType.Lazy);
    }

    public Set<Materia> findMaterias(FetchType mode) {
        return materias
                .findAll().stream()
                .map(data -> {
                    Materia materia = new Materia(data);

                    if (mode == FetchType.Eager) {
                        materia.setDocentes(
                                data.getDocentes().stream()
                                        .map(docente -> new Docente(docente))
                                        .collect(Collectors.toSet())
                        );
                    }

                    return materia;
                })
                .collect(Collectors.toSet());
    }

    public Materia saveMateria(Materia materia) throws StoreableValidationException, StoreableRelationException {
        return saveMateria(materia, FetchType.Lazy);
    }

    public Materia saveMateria(Materia materia, FetchType mode) throws StoreableValidationException, StoreableRelationException {
        materias.save(materia);

        if (mode == FetchType.Eager) {
            // valido que los docentes existan
            if (materia.getDocentes().stream().anyMatch(docente -> !docentes.exists(docente.getPid()))) {
                throw new StoreableRelationException("Docente inexistente");
            }

            // para c/ docente a desvincular, que no haya curso con par (docente,materia)
            Materia matData = materias.find(materia.getMid());
            Set<Docente> docData = matData.getDocentes();

            Set<Docente> aBorrar = docData.stream().filter(docente -> !materia.getDocentes().contains(docente)).collect(Collectors.toSet());
            for (Docente docente : aBorrar) {
                if (cursos.findAll().stream().anyMatch(curso -> curso.getDocente().equals(docente) && curso.getMateria().equals(matData))) {
                    throw new StoreableRelationException("Curso asignado. No se puede eliminar.");
                }
            }

            // desvinculo materia de docentes "eliminados"
            aBorrar.stream().forEach(docente -> docente.getMaterias().remove(matData));

            // desvinculo docentes de materia
            aBorrar.stream().forEach(docente -> matData.getDocentes().remove(docente));

            // cargo docentes a vincular:
            //  - originales almacenadas por id
            //  - no se validan los docentes recibidos c/ la materia
            //  - to-do dato modificado (o basura) será descartado
            //  - finalemente estos docentes (copias) serán vinculados
            //    a la materia retornada
            materia.getDocentes().stream().forEach(docente -> {
                // vinculo docente a la materia
                matData.getDocentes().add((Docente) docentes.find(docente.getPid()));

                // vinculo materia al docente
                ((Docente) docentes.find(docente.getPid())).getMaterias().add(matData);
            });
            materia.setDocentes(
                    matData
                            .getDocentes().stream()
                            .map(docente -> new Docente(docente))
                            .collect(Collectors.toSet())
            );
        }

        return materia;
    }

    public Materia removeMateria(Integer mid) throws StoreableRelationException {
        if (!materias.exists(mid)) {
            return null;
        }

        Materia materia = materias.find(mid);

        if (cursos.findAll().stream().anyMatch(curso -> curso.getMateria().equals(materia))) {
            throw new StoreableRelationException("Curso asignado. No se puede eliminar.");
        }

        // desvinculo materia de docentes
        materia.getDocentes().stream().forEach(docente -> docente.getMaterias().remove(materia));

        // desvinculo docentes de materia
        materia.getDocentes().clear();

        return materias.remove(mid);
    }

    public boolean existsCurso(Integer cid) {
        throw new UnsupportedOperationException("Not supported yet");
        // todo: actividad para los alumnos
    }

    public Curso findCurso(Integer cid) {
        return findCurso(cid, FetchType.Lazy);
    }

    public Curso findCurso(Integer cid, FetchType mode) {
        throw new UnsupportedOperationException("Not supported yet");
        // todo: actividad para los alumnos
    }

    public Set<Inscripcion> findCursoInscripciones(Integer cid) {
        throw new UnsupportedOperationException("Not supported yet");
        // todo: actividad para los alumnos
    }

    public Set<Curso> findCursos() {
        return findCursos(FetchType.Lazy);
    }

    public Set<Curso> findCursos(FetchType mode) {
        throw new UnsupportedOperationException("Not supported yet");
        // todo: actividad para los alumnos
    }

    public Curso saveCurso(Curso curso) throws StoreableValidationException, StoreableRelationException {
        return saveCurso(curso, FetchType.Lazy);
    }

    public Curso saveCurso(Curso curso, FetchType mode) throws StoreableValidationException, StoreableRelationException {
        throw new UnsupportedOperationException("Not supported yet");
        // todo: actividad para los alumnos
    }

    public Curso removeCurso(Integer cid) throws StoreableRelationException {
        throw new UnsupportedOperationException("Not supported yet");
        // todo: actividad para los alumnos
    }

    public void clearAll() {
        personas.clear();
        alumnos.clear();
        docentes.clear();
        materias.clear();
        cursos.clear();
    }

    public void generarDatos() throws Exception {
        clearAll();
        generarPersonas();
        generarMaterias();
    }

    private void generarPersonas() throws Exception {
        savePersona(new Persona("1642-12-25/1727-03-20", "Newton", "Isaac"));
        saveDocente(1, "LD-10.001");
        saveAlumno(1, "LA-10.001");

        saveAlumno(new Alumno("1629-04-14/1695-07-08", "Huygens", "Christiaan", "LA-10.002"));
        saveAlumno(new Alumno("1646-07-01/1716-11-14", "Leibniz", "Gottfried Wilhelm", "LA-10.003"));
        saveAlumno(new Alumno("1736-06-14/1806-08-23", "de Coulomb", "Charles-Augustin", "LA-10.004"));
        saveAlumno(new Alumno("1736-01-19/1819-08-25", "Watt", "James", "LA-10.005"));
        saveAlumno(new Alumno("1745-02-18/1827-03-05", "Volta", "Alessandro Giuseppe Antonio Anastasio", "LA-10.006"));
        saveAlumno(new Alumno("1775-01-20/1836-06-10", "Ampère", "André-Marie", "LA-10.007"));
        saveAlumno(new Alumno("1789-03-16/1854-07-06", "Ohm", "Georg Simon", "LA-10.008"));
        saveAlumno(new Alumno("1791-09-22/1867-08-25", "Faraday", "Michael", "LA-10.009"));
        saveAlumno(new Alumno("1818-12-24/1889-10-11", "Joule", "James Prescott", "LA-10.010"));
        saveAlumno(new Alumno("1831-06-13/1879-11-05", "Maxwell", "James Clerk", "LA-10.011"));
        saveAlumno(new Alumno("1856-07-10/1943-01-07", "Tesla", "Nikola", "LA-10.012"));
        saveAlumno(new Alumno("1857-02-22/1894-01-01", "Hertz", "Heinrich Rudolf", "LA-10.013"));
        saveAlumno(new Alumno("1867-11-07/1934-07-04", "Skłodowska-Curie", "Maria Salomea", "LA-10.014"));
        saveAlumno(new Alumno("1879-03-14/1955-04-18", "Einstein", "Albert", "LA-10.015"));
        saveAlumno(new Alumno("1885-10-07/1962-11-18", "Bohr", "Niels", "LA-10.016"));

        saveDocente(new Docente("1685-03-31/1750-07-28", "Bach", "Johann Sebastian", "LD-20.001"));
        saveAlumno(17, "LA-20.001");

        saveAlumno(new Alumno("1678-03-04/1741-07-28", "Vivaldi", "Antonio Lucio", "LA-20.002"));
        saveAlumno(new Alumno("1756-01-27/1791-12-05", "Mozart", "Wolfgang Amadeus", "LA-20.003"));
        saveAlumno(new Alumno("1770-12-??/1827-03-26", "van Beethoven", "Ludwig", "LA-20.004"));
        saveAlumno(new Alumno("1810-03-01/1849-10-17", "Chopin", "Frédéric François", "LA-20.005"));
        saveAlumno(new Alumno("1811-10-22/1886-07-31", "Liszt", "Franz", "LA-20.006"));
        saveAlumno(new Alumno("1813-05-22/1883-02-13", "Wagner", "Wilhelm Richard", "LA-20.007"));
        saveAlumno(new Alumno("1825-10-25/1899-06-03", "Strauss", "Johann Baptist II", "LA-20.008"));
        saveAlumno(new Alumno("1833-05-07/1897-04-03", "Brahms", "Johannes", "LA-20.009"));
        saveAlumno(new Alumno("1840-05-07/1893-11-06", "Tchaikovsky", "Pyotr Ilyich", "LA-20.010"));
        saveAlumno(new Alumno("1928-11-10/2020-07-06", "Morricone", "Enio", "LA-20.011"));
        saveAlumno(new Alumno("1932-02-08/", "Williams", "John Towner", "LA-20.012"));
        //saveDocente(new Docente("nnnn-02-07/", "Mastropiero / Mastropiano", "Johann Sebastian / Johann Severo / Peter Illich / Wolfgang Amadeus / Etcétera", "LD-nnn"));

        saveAlumno(new Alumno("1596-03-31/1650-02-11", "Descartes", "René", "LA-30.001"));
        saveDocente(29, "LD-30.001");

        saveAlumno(new Alumno("1632-08-29/1704-10-28", "Locke", "John", "LA-30.002"));
        saveAlumno(new Alumno("1724-04-22/1804-02-12", "Kant", "Immanuel", "LA-30.003"));
        saveAlumno(new Alumno("1770-08-27/1831-11-14", "Hegel", "Georg Wilhelm Friedrich", "LA-30.004"));
        saveAlumno(new Alumno("1788-02-22/1860-09-21", "Schopenhauer", "Arthur", "LA-30.005"));
        saveAlumno(new Alumno("1798-01-19/1857-09-05", "Comte", "Isidore Marie Auguste François Xavier", "LA-30.006"));
        saveAlumno(new Alumno("1844-10-15/1900-08-25", "Nietzsche", "Friedrich Wilhelm", "LA-30.007"));
        saveAlumno(new Alumno("1864-04-21/1920-06-14", "Weber", "Maximilian Karl Emil", "LA-30.008"));
        saveAlumno(new Alumno("1889-09-26/1976-05-26", "Heidegger", "Martin", "LA-30.009"));
        saveAlumno(new Alumno("1872-05-18/1970-02-02", "Russell", "Bertrand Arthur William", "LA-30.010"));
        saveAlumno(new Alumno("1883-05-09/1955-10-18", "Ortega y Gasset", "José", "LA-30.011"));
        saveAlumno(new Alumno("1905-06-21/1980-04-15", "Sartre", "Jean-Paul Charles Aymard", "LA-30.012"));
    }

    private void generarMaterias() throws Exception {
        saveMateria(new Materia("Física", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus."));
        saveMateria(new Materia("Musica", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus."));
        saveMateria(new Materia(2, "Música", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus."));
        saveMateria(new Materia("Filosofía", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae dolor eget felis pellentesque sagittis non in odio. Maecenas risus nibh, feugiat quis varius eu, mollis sit amet massa. Vivamus."));

        Materia materia = materias.find(1);
        materia.getDocentes().add((Docente) docentes.find(1));
        saveMateria(materia, FetchType.Eager);

        materia = materias.find(2);
        materia.getDocentes().add((Docente) docentes.find(17));
        saveMateria(materia, FetchType.Eager);

        materia = materias.find(3);
        materia.getDocentes().add((Docente) docentes.find(29));
        saveMateria(materia, FetchType.Eager);

    }

    private void generarCursos() throws Exception {
        // todo:
        //      - actividad para los alumnos
        //      - incluir inscripciones
    }

}
