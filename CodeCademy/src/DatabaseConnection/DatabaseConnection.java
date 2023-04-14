package DatabaseConnection;

import java.sql.*;

public class DatabaseConnection {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyRE;user=janko;password=Morgen;encrypt=true;trustServerCertificate=true";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
