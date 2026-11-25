
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/employmentdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Mysql123@";

    private Database() {

    }

    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException exception) {
            throw new SQLException(exception);
        }

        connection = DriverManager.getConnection(
                DB_CONNECTION, DB_USER, DB_PASSWORD);

        return connection;
    }
}
