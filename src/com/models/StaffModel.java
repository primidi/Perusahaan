package com.models;

import com.configs.DatabaseConfig;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class StaffModel {
    DatabaseConfig conn;
    Connection database;

    public StaffModel() {
        this.conn = new DatabaseConfig();
        this.database = conn.getConn();
    }

    public ResultSet getStaffById(String id) throws SQLException {
        Statement stmt = this.database.createStatement();
        String query = "SELECT * FROM staff WHERE id = " + id;
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }

    public void addStaff(
            String id,
            String lastName,
            String firstName,
            String mi,
            String address,
            String city,
            String state,
            String telephone,
            String email
    ) throws SQLException {
        Statement stmt = this.database.createStatement();
        String query = "INSERT INTO staff VALUES('" + id + "', '" + lastName + "', '" + firstName + "', '" +
                mi + "', '" + address + "', '" + city + "', '" + state + "', '" + telephone + "', '" + email + "')";
        stmt.executeUpdate(query);
    }

    public void updateStaffById(
            String id,
            String lastName,
            String firstName,
            String mi,
            String address,
            String city,
            String state,
            String telephone,
            String email
    ) throws SQLException {
        Statement stmt = this.database.createStatement();
        String query = "UPDATE staff SET `lastName` = '" + lastName +
                "', `firstName` = '" + firstName + "', `mi` = '" + mi + "', `address` = '" + address +
                "', `city` = '" + city + "', `state` = '" + state + "', `telephone` = '" + telephone +
                "', `email` = '" + email + "' WHERE id = " + id;
        stmt.executeUpdate(query);
    }
}
