package com.controllers;

import com.models.StaffModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffController {
    StaffModel model;

    public StaffController() {
        this.model = new StaffModel();
    }

    public ResultSet getStaffById(String id) throws SQLException {
        return this.model.getStaffById(id);
    }

    public String addStaff(
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
        try {
            this.model.addStaff(
                    id,
                    lastName,
                    firstName,
                    mi,
                    address,
                    city,
                    state,
                    telephone,
                    email
            );

            return "Insert data success!";
        } catch (SQLException exception) {
            throw new SQLException(exception.getMessage());
        }
    }

    public String updateStaffById(
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
        try {
            this.model.updateStaffById(
                    id,
                    lastName,
                    firstName,
                    mi,
                    address,
                    city,
                    state,
                    telephone,
                    email
            );

            return "Update data success!";
        } catch (SQLException exception) {
            throw new SQLException(exception.getMessage());
        }
    }
}
