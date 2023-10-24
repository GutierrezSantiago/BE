package utn.frc.bda.k4.sem6.jpa;

import utn.frc.bda.k4.lib.domain.jpa.JPAAlumno;
import utn.frc.bda.k4.lib.domain.jpa.JPADocente;
import utn.frc.bda.k4.lib.domain.jpa.JPAPersona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JPAPreTests {

    public static void runBatch(EntityManager em) {
        // ---------------------------------------------------------------------
        System.out.println("-----------------------------------------------------");
        System.out.println("Docentes");
        System.out.println("-----------------------------------------------------");
        List<JPADocente> docentes = em.createNamedQuery("JPADocente.findAllById").getResultList();
        docentes.forEach(System.out::println);
        JPADocente d;
        try {
            d = em.find(JPADocente.class, 1);
            System.out.println(d);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            d = (JPADocente) em.createNamedQuery("JPADocente.findById").setParameter("id", 17).getSingleResult();
            System.out.println(d);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // ---------------------------------------------------------------------
        //System.out.println("-----------------------------------------------------");
        //System.out.println("Alumnos");
        //System.out.println("-----------------------------------------------------");
        //List<JPAAlumno> alumnos = em.createNamedQuery("JPAAlumno.findAllById").getResultList();
        //alumnos.forEach(System.out::println);
        //JPAAlumno a;
        //try {
        //    a = em.find(JPAAlumno.class, 5);
        //    System.out.println(a);
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}
        //try {
        //    a = (JPAAlumno) em.createNamedQuery("JPAAlumno.findById").setParameter("id", 15).getSingleResult();
        //    System.out.println(a);
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}

        // ---------------------------------------------------------------------
        //System.out.println("-----------------------------------------------------");
        //System.out.println("Personas");
        //System.out.println("-----------------------------------------------------");
        //List<JPAPersona> personas = em.createNamedQuery("JPAPersona.findAllById").getResultList();
        //personas.forEach(System.out::println);
        //JPAPersona p;
        //try {
        //    p = em.find(JPAPersona.class, 8);
        //    System.out.println(p);
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}
        //try {
        //    p = (JPAPersona) em.createNamedQuery("JPAPersona.findById").setParameter("id", 1).getSingleResult();
        //    System.out.println(p);
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}

        // ---------------------------------------------------------------------
        //System.out.println("-----------------------------------------------------");
        //System.out.println("Prueba Transaction");
        //System.out.println("-----------------------------------------------------");
        //em.getTransaction().begin();
        //JPAPersona pp = em.find(JPAPersona.class, 17);
        //JPADocente dd = em.find(JPADocente.class, 17);
        //pp.setApellido("QQQQQQ");
        //dd.setApellido("PPPPPPP");
        //dd.setLegajo("Leg27");
        //em.getTransaction().commit();
        // -------------------------------------------------------------------------
    }


    public static void run() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(JPA.PG_PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        runBatch(em);

        em.close();
        emf.close();

    }

}
