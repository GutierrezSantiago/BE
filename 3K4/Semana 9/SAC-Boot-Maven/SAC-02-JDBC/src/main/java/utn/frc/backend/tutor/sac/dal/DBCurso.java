package utn.frc.backend.tutor.sac.dal;

import utn.frc.backend.tutor.sac.domain.Alumno;
import utn.frc.backend.tutor.sac.domain.Curso;
import utn.frc.backend.tutor.sac.lib.db.DBManager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scarafia
 */
public abstract class DBCurso {

  public static final String CURSO_ID = "cid";
  public static final String CURSO_NOMBRE = "nombre";
  public static final String CURSO_DESCRIPCION = "descripcion";
  public static final String CURSO_CUPO = "cupo";
  public static final String CURSO_FINICIO = "finicio";
  public static final String CURSO_FFIN = "ffin";
  public static final String CURSO_MATERIA_ID = "mid";
  public static final String CURSO_DOCENTE_ID = "pid";
  public static final String CURSO_ALUMNO_CURSO_ID = "cid";
  public static final String CURSO_ALUMNO_ALUMNO_ID = "pid";

  // ---------------------------------------------------------------------------
  // build
  // ---------------------------------------------------------------------------
  /**
   * Construye un Curso a partir de un ResultSet previamente ejecutado.
   *
   * @param db
   * @param rs
   * @return
   * @throws Exception
   */
  protected static Curso buildCurso(DBManager db, ResultSet rs) throws Exception {
    Curso curso = null;
    if (rs.next()) {
      curso = new Curso(
        rs.getInt(CURSO_ID),
        rs.getString(CURSO_NOMBRE),
        rs.getString(CURSO_DESCRIPCION),
        rs.getShort(CURSO_CUPO),
        rs.getDate(CURSO_FINICIO),
        rs.getDate(CURSO_FFIN),
        DBMateria.loadDB(db, rs.getInt(CURSO_MATERIA_ID)),
        DBDocente.loadDB(db, rs.getInt(CURSO_DOCENTE_ID))
      );
    }
    return curso;
  }

  // ---------------------------------------------------------------------------
  protected static List<Curso> buildCursos(DBManager db, ResultSet rs) throws Exception {
    List<Curso> r = new ArrayList();

    Curso curso;
    while ((curso = buildCurso(db, rs)) != null) {
      r.add(curso);
    }

    return r;
  }

  // ---------------------------------------------------------------------------
  // load
  // ---------------------------------------------------------------------------
  /**
   * Carga un curso de la ddbb a partir de su identificador.
   *
   * @param db
   * @param id
   * @return
   * @throws Exception
   */
  public static Curso loadDB(DBManager db, Integer id) throws Exception {
    if (db == null) {
      throw new Exception("DBCurso Error: DBManager NO especificado");
    }
    if (id == null) {
      throw new Exception("DBCurso Error: Identificador NO especificado");
    }

    String query = String.format(
      "SELECT * FROM curso c WHERE c.%s = ?", CURSO_ID
    );

    db.prepareQuery(query);
    db.setInt(1, id);

    ResultSet rs = db.executeQuery(query);
    Curso curso = buildCurso(db, rs);
    rs.close();
    return curso;
  }

  // ---------------------------------------------------------------------------
  /**
   * Carga un conjunto de cursos de la ddbb a partir de sus identificadores.
   *
   * @param db
   * @param ids
   * @return
   * @throws Exception
   */
  public static List<Curso> loadDB(DBManager db, Integer[] ids) throws Exception {
    if (ids == null) {
      throw new Exception("DBCurso Error: Identificadores NO especificados");
    }

    return loadList(db, ids);
  }

  // ---------------------------------------------------------------------------
  // save
  // ---------------------------------------------------------------------------
  /**
   * Guarda un curso en la ddbb.
   *
   * @param db
   * @param curso
   * @return
   * @throws Exception
   */
  public static Integer saveDB(DBManager db, Curso curso) throws Exception {
    if (db == null) {
      throw new Exception("DBCurso Error: DBManager NO especificado");
    }
    if (curso == null) {
      throw new Exception("DBCurso Error: Curso NO especificado");
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // chanchada:
    //    - debería implementarse patrón
    //    - pero se deja así:
    //        - con fines demostrativos solamente
    //        - sólo para "save" (p/ mostrar ventajas)
    //          (si bien en la bd está implementado CRUD completo)
    //    - la decisión insert/update la hará el SP
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    if (db.getDriverName().equals(DBManager.POSTGRES_DRIVER_NAME)) {
      return pgSaveDB(db, curso);
    }

    return curso.getCid() == null
      ? insertDB(db, curso)
      : updateDB(db, curso);
  }

  // ---------------------------------------------------------------------------
  private static Integer insertDB(DBManager db, Curso curso) throws Exception {

    db.prepareUpdate(String.format(
      "INSERT INTO curso (%s, %s, %s, %s, %s, %s, %s) "
      + "VALUES (?, ?, ?, ?, ?, ?, ?)",
      CURSO_NOMBRE, CURSO_DESCRIPCION, CURSO_CUPO, CURSO_FINICIO, CURSO_FFIN, CURSO_MATERIA_ID, CURSO_DOCENTE_ID
    ));
    db.setString(1, curso.getNombre());
    db.setString(2, curso.getDescripcion());
    db.setShort(3, curso.getCupo());
    db.setDate(4, curso.getFInicio());
    db.setDate(5, curso.getFFin());
    db.setInt(
      6,
      curso.getMateria() == null ? null : curso.getMateria().getMid());
    db.setInt(
      7,
      curso.getDocente() == null ? null : curso.getDocente().getPid());

    Integer id = null;

    ResultSet rs = db.executeUpdate().getGeneratedKeys();
    if (rs.first()) {
      id = rs.getInt(1);
    }
    rs.close();

    return id;
  }

  // ---------------------------------------------------------------------------
  private static Integer updateDB(DBManager db, Curso curso) throws Exception {

    db.prepareUpdate(String.format(
      "UPDATE curso SET "
      + "%s = ?, "
      + "%s = ?, "
      + "%s = ?, "
      + "%s = ?, "
      + "%s = ?, "
      + "%s = ?, "
      + "%s = ? "
      + "WHERE %s = ?",
      CURSO_NOMBRE, CURSO_DESCRIPCION,
      CURSO_CUPO,
      CURSO_FINICIO, CURSO_FFIN,
      CURSO_MATERIA_ID, CURSO_DOCENTE_ID,
      CURSO_ID
    ));
    db.setString(1, curso.getNombre());
    db.setString(2, curso.getDescripcion());
    db.setInt(3, curso.getCupo());
    db.setDate(4, curso.getFInicio());
    db.setDate(5, curso.getFFin());
    db.setInt(
      6,
      curso.getMateria() == null ? null : curso.getMateria().getMid());
    db.setInt(
      7,
      curso.getDocente() == null ? null : curso.getDocente().getPid());
    db.setInt(8, curso.getCid());
    db.executeUpdate();

    return curso.getCid();
  }

  // ---------------------------------------------------------------------------
  private static Integer pgSaveDB(DBManager db, Curso curso) throws Exception {

    String query = "SELECT fn_savecurso(?, ?, ?, ?, ?, ?, ?, ?)";

    db.prepareQuery(query);
    db.setInt(1, curso.getCid());
    db.setString(2, curso.getNombre());
    db.setString(3, curso.getDescripcion());
    db.setShort(4, curso.getCupo());
    db.setDate(5, curso.getFInicio());
    db.setDate(6, curso.getFFin());
    db.setInt(
      7,
      curso.getMateria() == null ? null : curso.getMateria().getMid());
    db.setInt(
      8,
      curso.getDocente() == null ? null : curso.getDocente().getPid());

    Integer id = null;
    ResultSet rs = db.executeQuery();
    if (rs.first()) {
      id = rs.getInt(1);
    }
    rs.close();

    curso.setCid(id);

    return id;
  }
  
  // ---------------------------------------------------------------------------
  // delete
  // ---------------------------------------------------------------------------
  /**
   * Elimina un curso de la ddbb.
   *
   * @param db
   * @param id
   * @return
   * @throws Exception
   */
  public static Curso deleteDB(DBManager db, Integer id) throws Exception {
    Curso curso = loadDB(db, id);

    if (curso != null) {
      db.prepareUpdate(String.format("DELETE FROM curso WHERE %s = ?", CURSO_ID));
      db.setInt(1, id);
      db.execute();
    }

    return curso;
  }

  // ---------------------------------------------------------------------------
  /**
   * Elimina una curso de la ddbb.
   *
   * @param db
   * @param curso
   * @return
   * @throws Exception
   */
  public static Curso deleteDB(DBManager db, Curso curso) throws Exception {
    if (curso == null) {
      throw new Exception("DBCurso Error: Curso NO especificado");
    }

    return deleteDB(db, curso.getCid());
  }

  // ---------------------------------------------------------------------------
  /**
   * Elimina un conjunto de cursos de la ddbb a partir de sus identificadores.
   *
   * @param db
   * @param ids
   * @return
   * @throws Exception
   */
  public static List<Curso> deleteDB(DBManager db, Integer[] ids) throws Exception {
    List<Curso> cursos = loadList(db, ids);

    if (cursos != null) {
      db.prepareUpdate(String.format("DELETE FROM curso WHERE %s IN (?)", CURSO_ID));
      db.setArray(1, "INTEGER", ids);
      db.execute();
    }

    return cursos;
  }

  // ---------------------------------------------------------------------------
  /**
   * Elimina un conjunto de cursos de la ddbb.
   *
   * @param db
   * @param cursos
   * @return
   * @throws Exception
   */
  public static List<Curso> deleteDB(DBManager db, Curso[] cursos) throws Exception {
    int l = cursos.length;
    Integer[] ids = new Integer[l];

    for (int i = 0; i < l; i++) {
      ids[i] = cursos[i].getCid();
    }

    return deleteDB(db, ids);
  }

  // ---------------------------------------------------------------------------
  // list
  // ---------------------------------------------------------------------------
  /**
   * Genera una lista de cursos.
   *
   * @param db
   * @param ids
   * @param limit
   * @param offset
   * @return
   * @throws Exception
   */
  public static List<Curso> loadList(DBManager db, Integer[] ids, int limit, int offset) throws Exception {
    if (db == null) {
      throw new Exception("DBCurso Error: DBManager NO especificado");
    }
    if (limit < 0) {
      throw new Exception("DBCurso Error: limit incorrecto");
    }
    if (offset < 0) {
      throw new Exception("DBCurso Error: offset incorrecto");
    }

    String query = DBManager.buildSelectQuery(
      String.format("m.%s, c.*", DBMateria.MATERIA_NOMBRE),
      String.format("curso c JOIN materia m ON c.%s = m.%s", CURSO_MATERIA_ID, DBMateria.MATERIA_ID),
      ids == null ? null : String.format("c.%s IN (?)", CURSO_ID),
      null, null,
      String.format("m.%s, c.%s", DBMateria.MATERIA_NOMBRE, CURSO_FINICIO),
      limit, offset
    );

    db.prepareQuery(query);

    int parameterIndex = 1;

    if (ids != null) {
      db.setArray(parameterIndex++, "INTEGER", ids);
    }

    if (limit > 0) {
      db.setInt(parameterIndex++, limit);
    }

    if (offset > 0) {
      db.setInt(parameterIndex++, offset);
    }

    ResultSet rs = db.executeQuery();
    List<Curso> cursos = buildCursos(db, rs);
    rs.close();
    return cursos;
  }

  // ---------------------------------------------------------------------------
  public static List<Curso> loadList(DBManager db, Integer[] ids) throws Exception {
    return loadList(db, ids, 0, 0);
  }

  // ---------------------------------------------------------------------------
  public static List<Curso> loadList(DBManager db, int limit, int offset) throws Exception {
    return loadList(db, null, limit, offset);
  }

  // ---------------------------------------------------------------------------
  public static List<Curso> loadList(DBManager db) throws Exception {
    return loadList(db, null);
  }

  // ---------------------------------------------------------------------------
  // alumnos inscriptos: load
  // ---------------------------------------------------------------------------
  /**
   * Carga los alumnos inscriptos en un curso.
   *
   * @deprecated
   *   Código desactualizado: asume tabla de vinculación simple en vez de
   *   tabla "inscripcion".
   *   todo: actividad para los alumnos
   *            - actualizar/adaptar el código
   *
   * @param db
   * @param curso
   * @param limit
   * @param offset
   * @return
   * @throws Exception
   */
  @Deprecated
  public static List<Alumno> loadAlumnos(DBManager db, Curso curso, int limit, int offset) throws Exception {
    if (true) throw new Exception("Deprecated: actividad para los alumnos");

    if (db == null) {
      throw new Exception("DBCurso Error: DBManager NO especificado");
    }
    if (curso == null) {
      throw new Exception("DBCurso Error: Curso NO especificado");
    }

    String query = DBManager.buildSelectQuery(
      String.format(
        "p.%s, p.%s, p.%s, p.%s, a.%s",
        DBPersona.PERSONA_ID, DBPersona.PERSONA_DNI,
        DBPersona.PERSONA_APELLIDO, DBPersona.PERSONA_NOMBRE,
        DBAlumno.ALUMNO_LEGAJO
      ),
      String.format(
        "persona p "
          + "JOIN alumno a ON p.%s = a.%s "
          + "JOIN curso_alumnos ca ON a.%s = ca.%s",
        DBPersona.PERSONA_ID, DBAlumno.ALUMNO_ID,
        DBAlumno.ALUMNO_ID, CURSO_ALUMNO_ALUMNO_ID
      ),
      String.format("ca.%s = ?", CURSO_ALUMNO_CURSO_ID),
      null, null,
      String.format("p.%s, p.%s", DBPersona.PERSONA_APELLIDO, DBPersona.PERSONA_NOMBRE),
      limit, offset
    );

    db.prepareQuery(query);
    db.setInt(1, curso.getCid());

    ResultSet rs = db.executeQuery();
    List<Alumno> alumnos = DBAlumno.buildAlumnos(rs);
    rs.close();

    // Se comenta línea a fin de poder compilar
    // todo: actividad para los alumnos
    //curso.setAlumnos(alumnos);
    
    return alumnos;
  }

  // ---------------------------------------------------------------------------

  /**
   * @deprecated
   *   Código desactualizado: asume tabla de vinculación simple en vez de
   *   tabla "inscripcion".
   *   todo: actividad para los alumnos
   *            - actualizar/adaptar el código*
   */
  @Deprecated
  public static List<Alumno> loadAlumnos(DBManager db, Curso curso) throws Exception {
    if (true) throw new Exception("Deprecated: actividad para los alumnos");

    return loadAlumnos(db, curso, 0, 0);
  }

  // ---------------------------------------------------------------------------
  // alumnos inscriptos: save
  // ---------------------------------------------------------------------------
  /**
   * @deprecated
   *   Código desactualizado: asume tabla de vinculación simple en vez de
   *   tabla "inscripcion".
   *   todo: actividad para los alumnos
   *            - actualizar/adaptar el código*
   */
  @Deprecated
  public static void saveAlumnos(DBManager db, Curso curso) throws Exception {
    if (true) throw new Exception("Deprecated: actividad para los alumnos");

    if (db == null) {
      throw new Exception("DBCurso Error: DBManager NO especificado");
    }
    if (curso == null) {
      throw new Exception("DBCurso Error: Curso NO especificado");
    }

    db.prepareUpdate(String.format(
      "DELETE FROM curso_alumnos ca WHERE ca.%s = ?", CURSO_ALUMNO_CURSO_ID
    ));
    db.setInt(1, curso.getCid());
    db.executeUpdate();

    if (curso.getAlumnos().size() > 0) {
      db.prepareUpdate(String.format(
        "INSERT INTO curso_alumnos (%s, %s) VALUES (?, ?)",
        CURSO_ALUMNO_CURSO_ID, CURSO_ALUMNO_ALUMNO_ID
      ));

      for (Alumno alumno : curso.getAlumnos()) {
        db.setInt(1, curso.getCid());
        db.setInt(2, alumno.getPid());
        db.executeUpdate();
      }
    }
  }

  // ---------------------------------------------------------------------------
  // candidatos (alumnos NO inscriptos): load
  // ---------------------------------------------------------------------------
  /**
   * Carga los alumnos NO inscriptos en un curso.
   *
   * @deprecated
   *   Código desactualizado: asume tabla de vinculación simple en vez de
   *   tabla "inscripcion".
   *   todo: actividad para los alumnos
   *            - actualizar/adaptar el código*
   *
   * @param db
   * @param curso
   * @param limit
   * @param offset
   * @return
   * @throws Exception
   */
  @Deprecated
  public static List loadCandidatos(DBManager db, Curso curso, int limit, int offset) throws Exception {
    if (true) throw new Exception("Deprecated: actividad para los alumnos");

    if (db == null) {
      throw new Exception("DBCurso Error: DBManager NO especificado");
    }
    if (curso == null) {
      throw new Exception("DBCurso Error: Curso NO especificado");
    }

    String query = DBManager.buildSelectQuery(
      String.format(
        "p.%s, p.%s, p.%s, p.%s, a.%s",
        DBPersona.PERSONA_ID, DBPersona.PERSONA_DNI,
        DBPersona.PERSONA_APELLIDO, DBPersona.PERSONA_NOMBRE,
        DBAlumno.ALUMNO_LEGAJO
      ),
      String.format(
        "persona p "
        + "JOIN alumno a ON p.%s = a.%s ",
        DBPersona.PERSONA_ID, DBAlumno.ALUMNO_ID
      ),
      String.format(
        "a.%s NOT IN (%s)",
        DBAlumno.ALUMNO_ID,
        DBManager.buildSelectQuery(
          String.format("ca.%s", CURSO_ALUMNO_ALUMNO_ID),
          "curso_alumnos ca",
          String.format("ca.%s = ?", CURSO_ALUMNO_CURSO_ID),
          null, null, null
        )
      ),
      null, null,
      String.format("p.%s, p.%s", DBPersona.PERSONA_APELLIDO, DBPersona.PERSONA_NOMBRE),
      limit, offset
    );

    db.prepareQuery(query);
    db.setInt(1, curso.getCid());

    ResultSet rs = db.executeQuery();
    List<Alumno> alumnos = DBAlumno.buildAlumnos(rs);
    rs.close();

    // Se comenta línea a fin de poder compilar
    // todo: actividad para los alumnos
    //curso.setAlumnos(alumnos);
    return alumnos;
  }

  // ---------------------------------------------------------------------------
  /**
   * @deprecated
   *   Código desactualizado: asume tabla de vinculación simple en vez de
   *   tabla "inscripcion".
   *   todo: actividad para los alumnos
   *            - actualizar/adaptar el código*
   */
  @Deprecated
  public static List loadCandidatos(DBManager db, Curso curso) throws Exception {
    if (true) throw new Exception("Deprecated: actividad para los alumnos");

    return loadCandidatos(db, curso, 0, 0);
  }

  // ---------------------------------------------------------------------------
  // inscripciones: add (¿tiene sentido?)
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  // inscripciones: remove
  // ---------------------------------------------------------------------------
  /**
   * Desinscribe un alumno del curso especificado.
   *
   * @deprecated
   *   Código desactualizado: asume tabla de vinculación simple en vez de
   *   tabla "inscripcion".
   *   todo: actividad para los alumnos
   *            - actualizar/adaptar el código*
   *
   * @param db
   * @param curso
   * @param alumno
   * @throws Exception
   */
  @Deprecated
  public static void deleteInscripcion(DBManager db, Curso curso, Alumno alumno) throws Exception {
    if (true) throw new Exception("Deprecated: actividad para los alumnos");

    if (db == null) {
      throw new Exception("DBCurso Error: DBManager NO especificado");
    }
    if (curso == null) {
      throw new Exception("DBCurso Error: Curso NO especificado");
    }
    if (alumno == null) {
      throw new Exception("DBCurso Error: Alumno NO especificado");
    }

    db.prepareUpdate(String.format(
      "DELETE FROM curso_alumnos WHERE %s = ? AND %s = ?",
      CURSO_ALUMNO_CURSO_ID, CURSO_ALUMNO_ALUMNO_ID
    ));

    db.setInt(1, curso.getCid());
    db.setInt(2, alumno.getPid());
    db.executeQuery();
  }

}
