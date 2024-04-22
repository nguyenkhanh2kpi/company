package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.LeaveRequestDTO;

import java.sql.*;

public class LeaveRequestDAO {
    //Request
    private static final String INSERT_LEAVEREQUEST = "INSERT INTO company.leaverequest VALUES(?, ?, ?, ?, ?, ?, ?, ? ,?)";

    //Status
    private static final String UPDATE_STATUS = "UPDATE company.leaverequest SET status = ? WHERE id = ?";

    public boolean request(LeaveRequestDTO lr) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_LEAVEREQUEST)) {
                statement.setInt(1, lr.getId());
                statement.setInt(2, lr.getIdUser());
                statement.setDate(3, lr.getRequestDate());
                statement.setDate(4, lr.getStartDate());
                statement.setDate(5, lr.getEndDate());
                statement.setInt(6, lr.getNumberDay());
                statement.setString(7, lr.getContent());
                statement.setString(8, lr.getStatus());
                statement.setInt(9, lr.getIdApprover());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatus(LeaveRequestDTO lr) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
                statement.setString(1, lr.getStatus());
                statement.setInt(2, lr.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
