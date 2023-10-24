package utn.frc.backend.tutor.sac;

import java.util.*;

import utn.frc.backend.tutor.sac.domain.Persona;
import utn.frc.backend.tutor.sac.domain.Alumno;
import utn.frc.backend.tutor.sac.domain.Docente;
import utn.frc.backend.tutor.sac.domain.Materia;
import utn.frc.backend.tutor.sac.domain.Curso;
import utn.frc.backend.tutor.sac.lib.util.Util;
import utn.frc.backend.tutor.sac.lib.util.menu.Menu;
import utn.frc.backend.tutor.sac.lib.util.menu.MenuItem;

public class Domain {

    private static SACStore store = new SACStore();

    public static void main(String[] args) {
        //System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);

        run(scanner);

        scanner.close();
    }

    private static void run(Scanner sc) {
        MenuItem[] ops = {
                new MenuItem("l", "Limpiar Datos") {
                    @Override
                    public void execute() {
                        if (Menu.confirm(sc, "Se eliminarán todos los datos. Desea continuar?")) {
                            store.clearAll();
                        }
                    }
                },
                new MenuItem("g", "Generar Datos") {
                    @Override
                    public void execute() {
                        if (Menu.confirm(sc, "Se eliminarán todos los datos. Desea continuar?")) {
                            generarDatos();

                            listar("Personas (y el polimorfismo estático?... minga!):", store.findPersonas());
                            listar("Alumnos:", store.findAlumnos());
                            listar("Docentes:", store.findDocentes());
                            listar("Materias:", store.findMaterias());
                            // todo: listar("Cursos:", store.findCuros);
                            //      - actividad para los alumnos
                        }
                    }
                },
                new MenuItem("p", "Listar Personas") {
                    @Override
                    public void execute() {
                        if (Menu.confirm(sc, "Incluir detalle?")) {
                            store.findPersonas().forEach(persona -> mostrarPersona(persona));
                            // todo:
                            //      - actividad para los alumnos
                            //      - incluir detalle:
                            //          - legajo alumno (sólo si aplica)
                            //          - legajo docente (sólo si aplica)
                            //      - ver/completar abajo
                        } else {
                            listar("Personas (y el polimorfismo estático?... minga!):", store.findPersonas());
                        }
                    }
                },
                new MenuItem("a", "Listar Alumnos") {
                    @Override
                    public void execute() {
                        if (Menu.confirm(sc, "Incluir detalle?")) {
                            store.findAlumnos().forEach(alumno -> mostrarAlumno(alumno));
                            // todo: store.findAlumnos(SACStore.FetchType.Eager).forEach(alumno -> mostrarAlumno(alumno));
                            //      - actividad para los alumnos
                            //      - incluir detalle:
                            //          - inscripciones (cursos)
                            //      - ver/completar abajo
                        } else {
                            listar("Alumnos:", store.findAlumnos());
                        }
                    }
                },
                new MenuItem("d", "Listar Docentes") {
                    @Override
                    public void execute() {
                        if (Menu.confirm(sc, "Incluir detalle?")) {
                            store.findDocentes(SACStore.FetchType.Eager).forEach(docente -> mostrarDocente(docente));
                            // todo:
                            //      - actividad para los alumnos
                            //      - incluir detalle:
                            //          - cursos
                            //      - ver/completar abajo
                        } else {
                            listar("Docentes:", store.findDocentes());
                        }
                    }
                },
                new MenuItem("m", "Listar Materias") {
                    @Override
                    public void execute() {
                        if (Menu.confirm(sc, "Incluir detalle?")) {
                            store.findMaterias(SACStore.FetchType.Eager).forEach(materia -> mostrarMateria(materia));
                            // todo:
                            //      - actividad para los alumnos
                            //      - incluir detalle:
                            //          - cursos
                            //      - ver/completar abajo
                        } else {
                            listar("Materias:", store.findMaterias());
                        }
                    }
                },
                new MenuItem("c", "Listar Cursos") {
                    @Override
                    public void execute() {
                        System.out.println("Actividad para los alumnos");
                        //if (Menu.confirm(sc, "Incluir detalle?")) {
                        //    store.findCursos(SACStore.FetchType.Eager).forEach(curso -> mostrarCurso(curso));
                        //} else {
                        //    listar("Cursos:", store.findCursos());
                        //}
                        // todo:
                        //      - actividad para los alumnos
                        //      - incluir detalle:
                        //          - inscripciones (alumnos)
                        //      - ver/completar abajo
                    }
                },
                new MenuItem("t", "Tests") {
                    @Override
                    public void execute() {
                        tests(sc);
                    }
                },
        };

        Menu menu = new Menu(sc, ops);

        menu.run();

    }

    private static void generarDatos() {
        try {
            store.generarDatos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listar(String title, Collection collection) {
        System.out.println(Util.fill('=', 80));
        System.out.println(title);
        for (Object item : collection) {
            System.out.println(Util.rPad(item, 80, 8));
        }
        System.out.println();
    }

    private static void mostrarPersona(Persona persona) {
        if (persona == null) {
            System.out.println("Persona nula");
            return;
        }
        System.out.println(Util.rPad(persona, 80, 2, 1, '='));
        System.out.println(Util.rPad("Legajo Alumno: Actividad para los alumnos (sólo si aplica)", 80, 8));
        System.out.println(Util.rPad("Legajo Docente: Actividad para los alumnos (sólo si aplica)", 80, 8));
        // todo:
        //      - actividad para los alumnos
        //      - incluir detalle:
        //          - legajo alumno (sólo si aplica)
        //          - legajo docente (sólo si aplica)
        //          (polimorfismo estático ???)
        System.out.println();
    }

    public static void mostrarAlumno(Alumno alumno) {
        if (alumno == null) {
            System.out.println("Alumno nulo");
            return;
        }
        System.out.println(Util.rPad(alumno, 80, 2, 1, '='));
        System.out.println("Cursos: Actividad para los alumnos");
        // todo:
        //      - actividad para los alumnos
        //      - incluir detalle:
        //          - inscripciones (cursos)
        System.out.println();
    }

    public static void mostrarDocente(Docente docente) {
        if (docente == null) {
            System.out.println("Docente nulo");
            return;
        }
        System.out.println(Util.rPad(docente, 80, 2, 1, '='));
        System.out.println("Materias: ");
        docente.getMaterias().forEach(materia -> System.out.println(Util.lPad(materia, 80)));
        System.out.println("Cursos: Actividad para los alumnos");
        // todo:
        //      - actividad para los alumnos
        //      - incluir detalle:
        //          - cursos
        System.out.println();
    }

    private static void mostrarMateria(Materia materia) {
        if (materia == null) {
            System.out.println("Materia nula");
            return;
        }
        System.out.println(Util.rPad(materia, 80, 2, 1, '='));
        System.out.println("Docentes: ");
        materia.getDocentes().forEach(docente -> System.out.println(Util.lPad(docente, 80)));
        System.out.println("Cursos: Actividad para los alumnos");
        // todo:
        //      - actividad para los alumnos
        //      - incluir detalle:
        //          - cursos
        System.out.println();
    }

    public static void mostrarCurso(Curso curso) {
        if (curso == null) {
            System.out.println("Curso nulo");
            return;
        }
        // todo:
        //      - actividad para los alumnos
        //      - incluir detalle:
        //          - inscripciones (alumnos)
    }

    private static void tests(Scanner sc) {
        MenuItem[] ops = {
                new MenuItem("1", "1er test") {
                    @Override
                    public void execute() {
                        try {
                            store.generarDatos();

                            Docente docente = new Docente("nnnn-02-07/", "Mastropiero / Mastropiano", "Johann Sebastian / Johann Severo / Peter Illich / Wolfgang Amadeus / Etcétera", "LD-nnn");
                            Materia materia = store.findMateria(2);
                            docente.getMaterias().add(materia);

                            System.out.println(Util.fill('*', 80));
                            store.saveDocente(docente);
                            mostrarDocente(docente);
                            mostrarDocente(store.findDocente(docente.getPid(), SACStore.FetchType.Eager));
                            mostrarMateria(materia);
                            mostrarMateria(store.findMateria(materia.getMid(), SACStore.FetchType.Eager));

                            System.out.println(Util.fill('*', 80));
                            store.saveDocente(docente, SACStore.FetchType.Eager);
                            mostrarDocente(docente);
                            mostrarDocente(store.findDocente(docente.getPid(), SACStore.FetchType.Eager));
                            mostrarMateria(materia);
                            mostrarMateria(store.findMateria(materia.getMid(), SACStore.FetchType.Eager));

                            System.out.println(Util.fill('*', 80));
                            store.removeMateria(2);
                            mostrarDocente(docente);
                            mostrarDocente(store.findDocente(docente.getPid(), SACStore.FetchType.Eager));
                            mostrarMateria(materia);
                            mostrarMateria(store.findMateria(materia.getMid(), SACStore.FetchType.Eager));

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                },
                new MenuItem("2", "2do test") {
                    @Override
                    public void execute() {
                        try {
                            store.generarDatos();

                            Docente docente = new Docente("nnnn-02-07/", "Mastropiero / Mastropiano", "Johann Sebastian / Johann Severo / Peter Illich / Wolfgang Amadeus / Etcétera", "LD-nnn");
                            store.saveDocente(docente);
                            Materia materia = store.findMateria(2);
                            materia.getDocentes().add(docente);

                            System.out.println(Util.fill('*', 80));
                            store.saveMateria(materia);
                            mostrarDocente(docente);
                            mostrarDocente(store.findDocente(docente.getPid(), SACStore.FetchType.Eager));
                            mostrarMateria(materia);
                            mostrarMateria(store.findMateria(materia.getMid(), SACStore.FetchType.Eager));

                            System.out.println(Util.fill('*', 80));
                            store.saveMateria(materia, SACStore.FetchType.Eager);
                            mostrarDocente(docente);
                            mostrarDocente(store.findDocente(docente.getPid(), SACStore.FetchType.Eager));
                            mostrarMateria(materia);
                            mostrarMateria(store.findMateria(materia.getMid(), SACStore.FetchType.Eager));

                            System.out.println(Util.fill('*', 80));
                            store.removeMateria(2);
                            mostrarDocente(docente);
                            mostrarDocente(store.findDocente(docente.getPid(), SACStore.FetchType.Eager));
                            mostrarMateria(materia);
                            mostrarMateria(store.findMateria(materia.getMid(), SACStore.FetchType.Eager));

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                },
                new MenuItem("3", "3er test") {
                    @Override
                    public void execute() {
                        System.out.println("Actividad para los alumnos");
                        // todo: actividad para los alumnos
                    }
                },
        };

        Menu menu = new Menu(sc, ops);

        menu.run();

    }

}
