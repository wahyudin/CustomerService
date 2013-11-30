package org.ifunpas.fo.cs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

	Connection con;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        return DBConnectionHolder.INSTANCE;
    }

    private static class DBConnectionHolder {

        private static final DBConnection INSTANCE = new DBConnection();
    }

    public void connect() {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=customer_service_fo";

            Class.forName(driver);
            con = DriverManager.getConnection(url, "root", "wahyudin");
            System.out.println("Connection Succesfull");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return con;
    }
}
