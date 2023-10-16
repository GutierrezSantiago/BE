package repositorios.implementaciones;

import repositorios.conexion.ConexionDb;

import java.net.URISyntaxException;
import java.sql.SQLException;

public abstract class RepositoryImpl<T> {
    protected final ConexionDb conexionDb;

    public RepositoryImpl(){
        try {

            conexionDb = ConexionDb.getInstance();

        } catch (URISyntaxException e) {
            System.out.println("La ubicacion del data source es incorrecta");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("El Driver del Data Source no esta registrado correctamente");
            throw new RuntimeException(e);
        }
    }
}
