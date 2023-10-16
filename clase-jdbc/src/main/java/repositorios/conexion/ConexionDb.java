package repositorios.conexion;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {

    public static ConexionDb instance;
    private final String connectionString;
    private Connection connection;

    private ConexionDb() throws URISyntaxException, SQLException {
        var url = ConexionDb.class.getResource("/data/aeropuerto.db");
        var path = new File(url.toURI()).getAbsolutePath();

        connectionString = String.format("jdbc:sqlite:%s", path);

        DriverManager.registerDriver(new org.sqlite.JDBC());
    }

    public static ConexionDb getInstance() throws URISyntaxException, SQLException {
        if (instance == null) {
            instance = new ConexionDb();
        }
        return instance;
    }

    public Connection openConnection() throws SQLException {
        connection = DriverManager.getConnection(this.connectionString);
        return connection;
    }

}
