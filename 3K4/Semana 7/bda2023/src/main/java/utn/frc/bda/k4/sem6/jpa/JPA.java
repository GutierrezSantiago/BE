package utn.frc.bda.k4.sem6.jpa;

import utn.frc.bda.k4.lib.util.menu.Menu;
import utn.frc.bda.k4.lib.util.menu.MenuItem;

import java.util.Scanner;

/**
 *
 * @author scarafia
 */
public abstract class JPA {
  
  // PostgreSQL
  public static final String PG_PERSISTENCE_UNIT_NAME = "pgPU";
  
  // H2 Database Engine
  public static final String H2_PERSISTENCE_UNIT_NAME = "h2PU";
  
  // PostgreSQL
  public static final String MY_PERSISTENCE_UNIT_NAME = "myPU";
  
  // Run
  public static void run(Scanner sc) {

    MenuItem[] ops = {
            new MenuItem("1", "JPA Tests") {
              @Override
              public void execute() {
                JPAPreTests.run();
              }
            },
            new MenuItem("2", "JPA Directo") {
              @Override
              public void execute() {
                JPADirecto.run();
              }
            },
    };

    Menu menu = new Menu(sc, ops);
    
    menu.run();
    
  }

}
