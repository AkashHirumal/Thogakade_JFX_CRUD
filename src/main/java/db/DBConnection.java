package db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private static DBConnection dbConnection;
    private Connection connection;

    private String URL="jdbc:mysql://localhost:3306/thogakade";
    private final String userName ="root";
    private  final String password = "12345678";




    private DBConnection() throws SQLException {

        connection = DriverManager.getConnection(URL,userName,password);
        System.out.println(connection);
    }

    public static DBConnection getInstance() throws SQLException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

}
