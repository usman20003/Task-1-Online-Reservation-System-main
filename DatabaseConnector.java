import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    // Constructor
    public DatabaseConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Method to establish a connection
    public Connection connect() {
        try {
            // Load the JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create the connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to close the connection
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/reservations_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        // Update with your database URL
        String user = "root"; // Update with your database username
        String password = "usman"; // Update with your database password

        DatabaseConnector dbConnector = new DatabaseConnector(url, user, password);
        Connection conn = dbConnector.connect();

        // Perform database operations here

        dbConnector.disconnect();
    }
}