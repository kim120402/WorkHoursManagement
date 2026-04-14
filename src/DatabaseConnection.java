import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    // JDBC classes 
    // local variable needs a value before assigning
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public DatabaseConnection() {
        try {
            // Load Driver
            Class.forName(OracleInfo.DRIVER_CLASS_ORACLE);
            System.out.println("Driver is loaded");
            
            // Connection
            connection = DriverManager.getConnection(
                OracleInfo.URL,
                OracleInfo.U,
                OracleInfo.P
            );
            System.out.println("Database is connected");

            statement = connection.createStatement();
        
        // handle errors
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}