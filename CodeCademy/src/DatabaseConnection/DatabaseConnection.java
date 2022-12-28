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

//    public static Connection getConnection() {
//
//        String connectionUrl = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyRE;user=janko;password=Morgen;encrypt=true;trustServerCertificate=true";
//
//        Connection con = null;
//        Statement stmt = null;
//
//        ResultSet rs = null;
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection(connectionUrl);
//            return con;
//            String SQL = "INSERT INTO Employee(Name, Email) Values('test','test@gmail.com')";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(SQL);
//
//            System.out.print(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));
//
//            while (rs.next()) {
//                String Name = rs.getString("Name");
//                String Email = rs.getString("Email");
//
//                System.out.println(Name + " " + Email);
//            }
//            System.out.println(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            if (rs != null) try {
//                rs.close();
//            } catch (Exception e) {
//            }
//            if (stmt != null) try {
//                stmt.close();
//            } catch (Exception e) {
//            }
//            if (con != null) try {
//                con.close();
//            } catch (Exception e) {
//            }
//        }
//    }
}
