package utn.frc.bda.k4.sem6.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import utn.frc.bda.k4.lib.domain.jpa.JPAAlumno;

/**
 *
 * @author scarafia
 */
public abstract class JPADirecto {

  private static void listAlumnos(List<JPAAlumno> alumnos) {

    System.out.println("Listando alumnos:");
    System.out.println("=================");

    for (JPAAlumno alumno : alumnos) {
      System.out.println(alumno);
    }

  }

  private static void runBatch(EntityManager em) {

    List<JPAAlumno> alumnos = em.createNamedQuery("JPAAlumno.findAllById").getResultList();
    listAlumnos(alumnos);

    System.out.println("Creando nuevo alumno...");
    JPAAlumno alumno = new JPAAlumno("leg999", "dni999", "Lojuno", "Johny");
    System.out.println("Nuevo alumno creado: " + alumno);
    
    em.getTransaction().begin();
    em.persist(alumno);
    em.getTransaction().commit();
    Integer id = alumno.getId();
    System.out.println("Nuevo alumno guardado: " + alumno);

    alumno = em.createNamedQuery("JPAAlumno.findById", JPAAlumno.class).setParameter("id", id).getSingleResult();
    System.out.println("Nuevo alumno cargado: " + alumno);

    alumno.setApellido("Quito");
    alumno.setNombre("Armando Esteban");
    System.out.println("Nuevo alumno modificado: " + alumno);

    em.getTransaction().begin();
    em.persist(alumno);
    em.getTransaction().commit();
    id = alumno.getId();
    System.out.println("Nuevo alumno guardado: " + alumno);

    alumno = em.createNamedQuery("JPAAlumno.findById", JPAAlumno.class).setParameter("id", id).getSingleResult();
    System.out.println("Nuevo alumno cargado: " + alumno);
    
    em.getTransaction().begin();
    alumno.setApellido("Chuca");
    alumno.setNombre("Kevin");
    em.getTransaction().commit();
    id = alumno.getId();
    System.out.println("Nuevo alumno modificado (y guardado): " + alumno);
    
    alumno = em.createNamedQuery("JPAAlumno.findById", JPAAlumno.class).setParameter("id", id).getSingleResult();
    System.out.println("Nuevo alumno cargado: " + alumno);
    
    em.getTransaction().begin();
    em.remove(alumno);
    em.getTransaction().commit();
    System.out.println("Nuevo alumno eliminado: " + alumno);
    
    //alumno = em.createNamedQuery("JPAAlumno.findById", JPAAlumno.class).setParameter("id", id).getSingleResult();
    alumno = em.find(JPAAlumno.class, id);
    id = alumno == null ? null : alumno.getId();
    System.out.println("Se intenta cargar nuevamente: (" + id + ") " + alumno);
    
  }

  public static void runDB(String DBName, String PUName) {
    EntityManagerFactory emf;
    EntityManager em;

    System.out.println("---------------------------------");
    System.out.printf("Conectando %s...\n", DBName);
    System.out.println("---------------------------------");

    emf = Persistence.createEntityManagerFactory(PUName);
    em = emf.createEntityManager();

    runBatch(em);

    em.close();
    emf.close();
  }

  public static void run() {
    // PostgreSQL
    runDB("PostgreSQL", JPA.PG_PERSISTENCE_UNIT_NAME);

    //// H2 Database Engine
    //runDB("H2 Database Engine", JPA.H2_PERSISTENCE_UNIT_NAME);
    //
    //// MySQL
    //runDB("MySQL", JPA.MY_PERSISTENCE_UNIT_NAME);
  }


}
