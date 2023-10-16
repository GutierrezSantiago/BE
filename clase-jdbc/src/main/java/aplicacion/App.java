package aplicacion;

import entidades.Aeropuerto;
import entidades.Terminal;
import repositorios.conexion.ConexionDb;
import repositorios.implementaciones.AeropuertoRepositoryImpl;
import repositorios.implementaciones.TerminalRepositoryImpl;
import repositorios.interfaces.AeropuertoRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        AeropuertoRepository repository = new AeropuertoRepositoryImpl();
        TerminalRepositoryImpl termRepo = new TerminalRepositoryImpl();

        Aeropuerto a1 = new Aeropuerto(0, "AEP", "Aeroparque");
        Aeropuerto a2 = new Aeropuerto(0, "MAD", "Como sea el nombre del gallego aeropuerto");

         repository.add(a1);
        repository.add(a2);

        //System.out.println("Listo!!!! Cargado y como se que se cargo????");
        var aeropuerto = repository.getById(1);
        if (aeropuerto != null)
        {
            termRepo.add(new Terminal(0, aeropuerto));
            termRepo.add(new Terminal(0, aeropuerto));
        }


        var list = repository.getAll();
        var terminalesByAeropuerto = termRepo.getByAeropuertoId(1);
        for (Terminal t: terminalesByAeropuerto) {
            System.out.println(t);
        }

        for (Aeropuerto a : list) {
            a.setTerminales((ArrayList<Terminal>) terminalesByAeropuerto);
            System.out.println(a);
        }

    }
}
