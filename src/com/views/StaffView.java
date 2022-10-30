package com.views;

import com.controllers.StaffController;

import javax.management.InstanceAlreadyExistsException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class StaffView extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JLabel lbId;
    private JTextField tfId;
    private JLabel lbLastName;
    private JTextField tfLastName;
    private JLabel lbFirstName;
    private JTextField tfFirstName;
    private JLabel lbMi;
    private JTextField tfMi;
    private JLabel lbAddress;
    private JTextField tfAddress;
    private JLabel lbCity;
    private JTextField tfCity;
    private JLabel lbState;
    private JTextField tfState;
    private JLabel lbTelephone;
    private JTextField tfTelephone;
    private JLabel lbEmail;
    private JTextField tfEmail;
    private JButton btnView;
    private JButton btnInsert;
    private JButton btnUpdate;
    private JButton btnClear;
    private JPanel alertPanel = new JPanel();
    private JLabel lbAlert = new JLabel("Record Not Found");

    private StaffController controller = new StaffController();

    public StaffView() throws SQLException {
        super("BINUS Staff Data");

        setLayout(new GridLayout(2, 1));

        this.alertPanel.add(this.lbAlert);
        add(alertPanel);
        add(mainPanel);

        this.btnView.addActionListener(this);
        this.btnInsert.addActionListener(this);
        this.btnUpdate.addActionListener(this);
        this.btnClear.addActionListener(this);

        setSize(720, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void clearTextField() {
        this.tfId.setText("");
        this.tfLastName.setText("");
        this.tfFirstName.setText("");
        this.tfMi.setText("");
        this.tfAddress.setText("");
        this.tfCity.setText("");
        this.tfState.setText("");
        this.tfTelephone.setText("");
        this.tfEmail.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = this.tfId.getText();

        if (e.getSource() == btnView) {
            try {
                if (id.equals("")) {
                    throw new SQLException("Insert the staff ID in the ID field!");
                }

                ResultSet rs = this.controller.getStaffById(id);

                if (!rs.next()) {
                    throw new SQLException("Record with id = " + id + " not found!");
                }

                this.lbAlert.setText("Record Found");

                this.tfId.setText(rs.getString("id"));
                this.tfLastName.setText(rs.getString("lastName"));
                this.tfFirstName.setText(rs.getString("firstName"));
                this.tfMi.setText(rs.getString("mi"));
                this.tfAddress.setText(rs.getString("address"));
                this.tfCity.setText(rs.getString("city"));
                this.tfState.setText(rs.getString("state"));
                this.tfTelephone.setText(rs.getString("telephone"));
                this.tfEmail.setText(rs.getString("email"));
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == btnInsert) {
            try {
                if (
                        id.equals("") || this.tfLastName.getText().equals("") ||
                        this.tfFirstName.getText().equals("") || this.tfMi.getText().equals("") ||
                        this.tfAddress.getText().equals("") || this.tfCity.getText().equals("") ||
                        this.tfState.getText().equals("") || this.tfTelephone.getText().equals("") ||
                        this.tfEmail.getText().equals("")
                ) {
                    throw new InputMismatchException("All fields must not be blank!");
                }

                ResultSet rs = this.controller.getStaffById(id);

                if (rs.next()) {
                    throw new InstanceAlreadyExistsException("Staff with id = " + id + " already exists!");
                }

                String msg = this.controller.addStaff(
                        id,
                        this.tfLastName.getText(),
                        this.tfFirstName.getText(),
                        this.tfMi.getText(),
                        this.tfAddress.getText(),
                        this.tfCity.getText(),
                        this.tfState.getText(),
                        this.tfTelephone.getText(),
                        this.tfEmail.getText()
                );
                JOptionPane.showMessageDialog(this, msg);
            } catch (InputMismatchException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            } catch (InstanceAlreadyExistsException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == btnUpdate) {
            try {
                if (
                        id.equals("") || this.tfLastName.getText().equals("") ||
                        this.tfFirstName.getText().equals("") || this.tfMi.getText().equals("") ||
                        this.tfAddress.getText().equals("") || this.tfCity.getText().equals("") ||
                        this.tfState.getText().equals("") || this.tfTelephone.getText().equals("") ||
                        this.tfEmail.getText().equals("")
                ) {
                    throw new InputMismatchException("All fields must not be blank!");
                }

                ResultSet rs = this.controller.getStaffById(id);

                if (!rs.next()) {
                    throw new InputMismatchException("Staff with id = " + id + " not found!");
                }

                String msg = this.controller.updateStaffById(
                        id,
                        this.tfLastName.getText(),
                        this.tfFirstName.getText(),
                        this.tfMi.getText(),
                        this.tfAddress.getText(),
                        this.tfCity.getText(),
                        this.tfState.getText(),
                        this.tfTelephone.getText(),
                        this.tfEmail.getText()
                );
                JOptionPane.showMessageDialog(this, msg);
            } catch (InputMismatchException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == btnClear) {
            clearTextField();
        }
    }

    public static void main(String[] args) throws SQLException {
        new StaffView();
    }
}
