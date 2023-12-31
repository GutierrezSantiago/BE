package utn.frc.bda.k4.sem5.jdbc;

import utn.frc.bda.k4.lib.db.DBManager;
import utn.frc.bda.k4.lib.domain.Alumno;
import utn.frc.bda.k4.lib.domain.crud.DBAlumno;

import java.util.List;

public abstract class JDBCconDBManager {

    private static void listAlumnos(List<Alumno> alumnos) {

        System.out.println("Listando alumnos:");
        System.out.println("=================");

        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }

    }

    private static void runBatch(DBManager db) {

        try {

            db.connect();

            listAlumnos(DBAlumno.loadList(db));

            System.out.println("Creando nuevo alumno...");
            Alumno alumno = new Alumno("dni999", "Lojuno", "Johny", "leg999");
            System.out.println("Nuevo alumno creado: " + alumno);

            Integer id = DBAlumno.saveDB(db, alumno);
            System.out.println("Nuevo alumno guardado: " + alumno);

            alumno = DBAlumno.loadDB(db, id);
            System.out.println("Nuevo alumno cargado: " + alumno);

            alumno.setApellido("Quito");
            alumno.setNombre("Armando Esteban");
            System.out.println("Nuevo alumno modificado: " + alumno);

            id = DBAlumno.saveDB(db, alumno);
            System.out.println("Nuevo alumno guardado: " + alumno);

            alumno = DBAlumno.loadDB(db, id);
            System.out.println("Nuevo alumno cargado: " + alumno);

            DBAlumno.deleteDB(db, alumno);
            System.out.println("Nuevo alumno eliminado: " + alumno);

            alumno = DBAlumno.loadDB(db, id);
            id = alumno == null ? null : alumno.getId();
            System.out.println("Se intenta cargar nuevamente: (" + id + ") " + alumno);

        } catch (Exception e) {

            System.err.println(e.getMessage());

        } finally {

            if (db != null) {
                db.close();
            }

        }

    }

    public static void run() {

        // PostgreSQL
        System.out.println("---------------------------------");
        System.out.println("Conectando PostgreSQL...");
        System.out.println("---------------------------------");
        runBatch(
                new DBManager(DBManager.POSTGRES_DRIVER_NAME, JDBC.PG_URL, JDBC.PG_USR, JDBC.PG_PWD)
        );

        //// H2 Database Engine
        //System.out.println("---------------------------------");
        //System.out.println("Conectando H2 Database Engine...");
        //System.out.println("---------------------------------");
        //runBatch(
        //  new DBManager(DBManager.H2_DRIVER_NAME, JDBC.H2_URL)
        //);

    }

}
