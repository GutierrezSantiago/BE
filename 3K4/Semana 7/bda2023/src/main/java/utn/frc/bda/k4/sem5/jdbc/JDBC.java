package utn.frc.bda.k4.sem5.jdbc;

import utn.frc.bda.k4.lib.util.menu.Menu;
import utn.frc.bda.k4.lib.util.menu.MenuItem;

import java.util.Scanner;

public abstract class JDBC {

    // PostgreSQL
    public static final String PG_DRIVER_NAME = "org.postgresql.Driver";
    //public static final String PG_URL = "jdbc:postgresql://localhost:5432/sacdb";
    public static final String PG_URL = "jdbc:postgresql://pg96:5432/sacdb";
    public static final String PG_USR = "sacusr";
    public static final String PG_PWD = "sacpwd";

    // H2 Database Engine
    public static final String H2_DRIVER_NAME = "org.h2.Driver";
    public static final String H2_URL = "jdbc:h2:file:/t/dbdata/bda2023/sacdb";

    // Run
    public static void run(Scanner sc) {

        MenuItem[] ops = {
                new MenuItem("1", "JDBC Directo") {
                    @Override
                    public void execute() {
                        JDBCDirecto.run();
                    }
                },
                new MenuItem("2", "JDBC Con DBManager") {
                    @Override
                    public void execute() {
                        JDBCconDBManager.run();
                    }
                },
        };

        Menu menu = new Menu(sc, ops);

        menu.run();
    }

}
