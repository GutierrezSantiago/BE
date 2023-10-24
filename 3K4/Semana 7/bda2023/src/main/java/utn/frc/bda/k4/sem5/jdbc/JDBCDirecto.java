package utn.frc.bda.k4.sem5.jdbc;

import utn.frc.bda.k4.lib.domain.Persona;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class JDBCDirecto {

    // PostgreSQL
    private static void pg_dbquery() throws Exception, ClassNotFoundException {

        Class.forName(JDBC.PG_DRIVER_NAME);
        Connection cn = DriverManager.getConnection(JDBC.PG_URL, JDBC.PG_USR, JDBC.PG_PWD);

        Statement stmt = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //Statement stmt = cn.createStatement();

        String query = "SELECT * FROM persona ORDER BY apellido, nombre";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Integer id = rs.getInt("pid");
            String dni = rs.getString("dni");
            String ape = rs.getString("apellido");
            String nom = rs.getString("nombre");
            Persona p = new Persona(id, dni, ape, nom);
            System.out.println(p);
        }

        rs.close();

        stmt.close();

        cn.close();

    }

    // H2 Database Engine
    private static void h2_dbquery() throws Exception, ClassNotFoundException {

        Class.forName(JDBC.H2_DRIVER_NAME);
        Connection cn = DriverManager.getConnection(JDBC.H2_URL);

        Statement stmt = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String query = "SELECT * FROM persona ORDER BY apellido, nombre";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Integer id = rs.getInt("pid");
            String dni = rs.getString("dni");
            String ape = rs.getString("apellido");
            String nom = rs.getString("nombre");
            Persona p = new Persona(id, dni, ape, nom);
            System.out.println(p);
        }

        rs.close();

        stmt.close();

        cn.close();

    }

    public static void run() {
        try {
            System.out.println("-------------------------");
            System.out.println("Consultando PostgreSQL...");
            System.out.println("-------------------------");
            pg_dbquery();
            System.out.println("---------------------------------");
            System.out.println("Consultando H2 Database Engine...");
            System.out.println("---------------------------------");
            h2_dbquery();
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró la clase");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
