package com.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    String url, username, password;
    Connection conn = null;

    public DatabaseConfig() {
        this.url = "jdbc:mysql://localhost/perusahaan_tk4";
        this.username = "root";
        this.password = "";
    }

    public Connection getConn() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.conn = DriverManager.getConnection(url, username, password);
                System.out.println("Connection succeed!");
            } catch (ClassNotFoundException | SQLException error) {
                System.out.println("Connection failed, " + error.getMessage());
            }
        }

        return conn;
    }
}
