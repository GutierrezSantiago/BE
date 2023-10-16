package repositorios.implementaciones;

import entidades.Aeropuerto;
import repositorios.conexion.ConexionDb;
import repositorios.interfaces.AeropuertoRepository;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeropuertoRepositoryImpl extends RepositoryImpl<Aeropuerto>
        implements AeropuertoRepository {

    public AeropuertoRepositoryImpl() {
        super();
    }

    @Override
    public Aeropuerto getByCodigoInternacional(String codigoInternacional) {
        return null;
    }

    @Override
    public void add(Aeropuerto entidad) {
        final String sql = """
                insert into Aeropuertos (nombre, codigo_internacional)
                values (?,?);
                """;

        try (final Connection connection = conexionDb.openConnection();) {

            var statement = connection.prepareStatement(sql);
            statement.setString(1, entidad.getNombre());
            statement.setString(2, entidad.getCodigoInternacional());
            statement.execute();

        } catch (SQLException e) {
            System.out.println("La comunicacion con la base de datos no esta funcionando");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Aeropuerto update(Aeropuerto entidad) {
        return null;
    }

    @Override
    public Aeropuerto delete(int codigo) {
        return null;
    }

    @Override
    public Aeropuerto getById(int codigo) {
        final String sql = """
                select codigo, nombre, codigo_internacional from Aeropuertos where codigo = ?;
                """;
        Aeropuerto aeropuerto = null;

        try (Connection connection = conexionDb.openConnection();) {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, codigo);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                var nombre = result.getString("nombre");
                var codInter = result.getString("codigo_internacional");

                aeropuerto = new Aeropuerto(codigo, nombre, codInter);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aeropuerto;
    }

    @Override
    public List<Aeropuerto> getAll() {
        final String sql = """
                select codigo, nombre, codigo_internacional from Aeropuertos;
                """;
        ArrayList<Aeropuerto> list = new ArrayList<>();

        try (Connection connection = conexionDb.openConnection();
             var statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql);) {
            while (result.next()) {
                var codigo = result.getInt("codigo");
                var nombre = result.getString("nombre");
                var codInter = result.getString("codigo_internacional");

                Aeropuerto aeropuerto = new Aeropuerto(codigo, nombre, codInter);
                list.add(aeropuerto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
