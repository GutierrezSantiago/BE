package repositorios.implementaciones;

import entidades.Aeropuerto;
import entidades.Terminal;
import repositorios.conexion.ConexionDb;
import repositorios.interfaces.TerminalRepository;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TerminalRepositoryImpl extends RepositoryImpl<Terminal>
        implements TerminalRepository {

    public TerminalRepositoryImpl() {
        super();
    }

    @Override
    public void add(Terminal entidad) {

    }

    @Override
    public Terminal update(Terminal entidad) {
        return null;
    }

    @Override
    public Terminal delete(int codigo) {
        return null;
    }

    @Override
    public Terminal getById(int codigo) {
        return null;
    }

    @Override
    public List<Terminal> getAll() {
        return null;
    }

    @Override
    public List<Terminal> getByAeropuertoId(int id) {
        final String sql = """
                    select T.codigo, T.aeropuerto_id, A.nombre, A.codigo_internacional \s
                    from Aeropuertos as A \s
                        join Terminales as T \s
                          on T.aeropuerto_id = A.codigo \s
                    where T.aeropuerto_id = ?;
                """;
        ArrayList<Terminal> list = new ArrayList<>();
        try (final Connection connection = conexionDb.openConnection())
        {
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {

                var codigo = result.getInt("codigo");
                var aerpuerto_id = result.getInt("aeropuerto_id");
                var nombre = result.getString("nombre");
                var codInt = result.getString("codigo_internacional");

                Aeropuerto a = new Aeropuerto(aerpuerto_id,nombre, codInt);
                Terminal t = new Terminal(codigo, a);
                list.add(t);
            }
            result.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
