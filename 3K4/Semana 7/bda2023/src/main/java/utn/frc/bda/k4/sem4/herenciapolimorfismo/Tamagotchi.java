package utn.frc.bda.k4.sem4.herenciapolimorfismo;

import java.util.Scanner;

public class Tamagotchi {

    private static final char OPCION_COMER = 'c';
    private static final String ACCION_COMER = "comer";
    private static final char OPCION_BEBER = 'b';
    private static final String ACCION_BEBER = "beber";
    private static final char OPCION_CORRER = 'r';
    private static final String ACCION_CORRER = "correr";
    private static final char OPCION_SALTAR = 's';
    private static final String ACCION_SALTAR = "saltar";
    private static final char OPCION_DORMIR = 'd';
    private static final String ACCION_DORMIR = "dormir";
    private static final char OPCION_DESPERTAR = 'p';
    private static final String ACCION_DESPERTAR = "despertar";
    private static final char OPCION_SALIR_PROGRAMA = 'q';
    private static final String ACCION_SALIR_PROGRAMA = "salir";
    private static final short MIN_ENERGIA = 0;
    private static final short MAX_ENERGIA = 100;
    private static final double ENERGIA_COMER = 1.1;
    private static final double ENERGIA_BEBER = 1.05;
    private static final short ENERGIA_DORMIR = 25;
    private static final double ENERGIA_CORRER = .65;
    private static final double ENERGIA_SALTAR = .85;
    private static final short MIN_HUMOR = 0;
    private static final short MAX_HUMOR = 5;
    private static final short HUMOR_DORMIR = 2;
    private static final short HUMOR_DESPERTAR = -1;
    private static final short HUMOR_INGESTA = -1;
    private static final short INGESTAS_MUERE = 5;
    private static final short INGESTAS_HUMOR = 3;
    private static final short ACTIVIDADES_SE_EMPACA = 3;
    private static final boolean PERMITIR_MORIR_A_LA_MASCOTA = false;
    private static final boolean SALIR_SI_LA_MASCOTA_MUERE = true;

    private String nombre;
    private boolean alive = true;
    private boolean sleeping = false;
    private short energia = 0;
    private short humor = 0;
    private short cntIngestas = 0;
    private short cntActividades = 0;

    public Tamagotchi(String nombre, short energia, short humor) {
        this.nombre = nombre;
        updateEnergia(energia);
        updateHumor(humor);
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAlive() {
        return alive;
    }

    private void updateEnergia(double incremento) {
        energia *= incremento;
        if (energia < MIN_ENERGIA) {
            energia = MIN_ENERGIA;
        } else if (energia > MAX_ENERGIA) {
            energia = MAX_ENERGIA;
        }
    }

    private void updateEnergia(short incremento) {
        energia += incremento;
        if (energia < MIN_ENERGIA) {
            energia = MIN_ENERGIA;
        } else if (energia > MAX_ENERGIA) {
            energia = MAX_ENERGIA;
        }
    }

    private void updateHumor(short incremento) {
        humor += incremento;
        if (humor < MIN_HUMOR) {
            humor = MIN_HUMOR;
        } else if (humor > MAX_HUMOR) {
            humor = MAX_HUMOR;
        }
    }

    public boolean comer() {
        return ingerir(ENERGIA_COMER);
    }

    public boolean beber() {
        return ingerir(ENERGIA_BEBER);
    }

    private boolean ingerir(double incrementoEnergia) {
        if (!alive || sleeping) {
            return false;
        }

        if (cntIngestas + 1 >= INGESTAS_MUERE && !PERMITIR_MORIR_A_LA_MASCOTA) {
            return false;
        }

        cntActividades = 0;
        cntIngestas++;

        if (cntIngestas >= INGESTAS_MUERE) {
            alive = false;
            return false;
        }

        updateEnergia(incrementoEnergia);

        if (cntIngestas >= INGESTAS_HUMOR) {
            updateHumor(HUMOR_INGESTA);
        }

        return humor > MIN_HUMOR ? true : dormir();
    }

    private boolean dormir() {
        if (!alive || sleeping) {
            return false;
        }

        cntIngestas = 0;
        cntActividades = 0;
        sleeping = true;

        updateEnergia(ENERGIA_DORMIR);
        updateHumor(HUMOR_DORMIR);

        return true;
    }

    private boolean despertar() {
        if (!alive || !sleeping) {
            return false;
        }

        sleeping = false;

        updateHumor(HUMOR_DESPERTAR);

        return true;
    }

    private boolean correr() {
        return realizarActividad(ENERGIA_CORRER);
    }

    private boolean saltar() {
        return realizarActividad(ENERGIA_SALTAR);
    }

    private boolean realizarActividad(double incrementoEnergia) {
        if (!alive || sleeping) {
            return false;
        }

        if ((short)(energia * incrementoEnergia) <= MIN_ENERGIA && !PERMITIR_MORIR_A_LA_MASCOTA) {
            return false;
        }

        cntIngestas = 0;
        cntActividades++;

        updateEnergia(incrementoEnergia);

        if (energia <= MIN_ENERGIA) {
            alive = false;
            return false;
        }

        return cntActividades < ACTIVIDADES_SE_EMPACA ? true : dormir();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("[ energia: %3d", energia));
        builder.append(String.format(", humor: %2d", humor));
        builder.append(", vive: " + (alive ? "Si" : "No"));
        if (alive) {
            builder.append(", duerme: " + (sleeping ? "Si" : "No"));
        }
        builder.append(" ]");

        return builder.toString();
    }

    private static void printStatusAccion(Tamagotchi mascota, boolean accionOk, String accion) {
        System.out.println(mascota.getNombre() + (accionOk ? "" : " NO") + " pudo " + accion);
    }

    private static void printStatusMascota(Tamagotchi mascota) {
        System.out.println("Estado de " + mascota.getNombre() + ": " + mascota);
    }

    public static void run() {
        Tamagotchi mascota = new Tamagotchi("Winfried", MAX_ENERGIA, MAX_HUMOR);
        printStatusMascota(mascota);

        Scanner scanner = new Scanner(System.in);

        char op = ' ';

        while (op != 'q' && (mascota.isAlive() || !SALIR_SI_LA_MASCOTA_MUERE)) {
            System.out.println("Seleccione una opcion:");
            System.out.println(OPCION_COMER +  ") " + ACCION_COMER);
            System.out.println(OPCION_BEBER +  ") " + ACCION_BEBER);
            System.out.println(OPCION_CORRER +  ") " + ACCION_CORRER);
            System.out.println(OPCION_SALTAR +  ") " + ACCION_SALTAR);
            System.out.println(OPCION_DORMIR +  ") " + ACCION_DORMIR);
            System.out.println(OPCION_DESPERTAR +  ") " + ACCION_DESPERTAR);
            System.out.println(OPCION_SALIR_PROGRAMA +  ") " + ACCION_SALIR_PROGRAMA);

            boolean accionOk = false;
            String accion = "realizar accion desconocida";

            op = scanner.next().toLowerCase().charAt(0);

            switch (op) {
                case OPCION_COMER:
                    accion = ACCION_COMER;
                    accionOk = mascota.comer();
                    break;
                case OPCION_BEBER:
                    accion = ACCION_BEBER;
                    accionOk = mascota.beber();
                    break;
                case OPCION_CORRER:
                    accion = ACCION_CORRER;
                    accionOk = mascota.correr();
                    break;
                case OPCION_SALTAR:
                    accion = ACCION_SALTAR;
                    accionOk = mascota.saltar();
                    break;
                case OPCION_DORMIR:
                    accion = ACCION_DORMIR;
                    accionOk = mascota.dormir();
                    break;
                case OPCION_DESPERTAR:
                    accion = ACCION_DESPERTAR;
                    accionOk = mascota.despertar();
                    break;
                case OPCION_SALIR_PROGRAMA:
                    break;
                // default: // No hace falta
            }

            if (op != OPCION_SALIR_PROGRAMA) {
                printStatusAccion(mascota, accionOk, accion);
                printStatusMascota(mascota);

                if (!mascota.isAlive() && SALIR_SI_LA_MASCOTA_MUERE) {
                    System.out.println("Lamentablemente " + mascota.getNombre() + " no está más con nosotros.");
                }
            }
        }

        System.out.println("ADIÓS!");

    }
}
