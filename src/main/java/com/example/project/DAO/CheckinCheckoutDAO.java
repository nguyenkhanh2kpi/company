package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.CheckinCheckoutDTO;

import java.sql.*;

public class CheckinCheckoutDAO {
    //Checkin
    private static final String INSERT_CHECKIN_CHECKOUT = "INSERT INTO company.checkin_checkout VALUES(?, ?, ? ,?, ?, ?)";

    //Checkout
    private static final String UPDATE_CHECKOUT = "UPDATE company.checkin_checkout SET checkoutTime = ?, totalHours = ? WHERE id = ?";

    public boolean checkin(CheckinCheckoutDTO cico) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_CHECKIN_CHECKOUT)) {
                statement.setInt(1, cico.getId());
                statement.setInt(2, cico.getIdUser());
                statement.setDate(3, cico.getCheckinTime());
                statement.setDate(4, cico.getCheckoutTime());
                statement.setFloat(5, cico.getTotalHours());
                statement.setDate(6, cico.getDate());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkout(CheckinCheckoutDTO cico) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CHECKOUT)) {
                statement.setDate(1, cico.getCheckoutTime());
                statement.setFloat(2, cico.getTotalHours());
                statement.setInt(3, cico.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
