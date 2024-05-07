package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.LeaveRequestDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {
    //Request
    private static final String INSERT_LEAVEREQUEST = "INSERT INTO company.leaverequest VALUES(?, ?, ?, ?, ?, ?, ?, ? ,?)";
    private static final String NEW_LEAVE = "INSERT INTO company.leaverequest (idUser, requestDate, startDate, endDate, numberDay, content, status)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?);\n";
    private static final String GET_ALL_LEAVE_REQUESTS = "SELECT * FROM company.leaverequest";
    //Status
    private static final String UPDATE_STATUS = "UPDATE company.leaverequest SET status = ?,idApprover =? WHERE id = ?";

    public boolean request(LeaveRequestDTO lr) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(NEW_LEAVE)) {
                statement.setInt(1, lr.getIdUser());
                statement.setDate(2, lr.getRequestDate());
                statement.setDate(3, lr.getStartDate());
                statement.setDate(4, lr.getEndDate());
                statement.setInt(5, lr.getNumberDay());
                statement.setString(6, lr.getContent());
                statement.setString(7, lr.getStatus());
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
                statement.setInt(2, lr.getIdApprover());
                statement.setInt(3, lr.getId());
                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        List<LeaveRequestDTO> leaveRequests = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(GET_ALL_LEAVE_REQUESTS)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        LeaveRequestDTO lr = new LeaveRequestDTO();
                        lr.setId(resultSet.getInt("id"));
                        lr.setIdUser(resultSet.getInt("idUser"));
                        lr.setRequestDate(resultSet.getDate("requestDate"));
                        lr.setStartDate(resultSet.getDate("startDate"));
                        lr.setEndDate(resultSet.getDate("endDate"));
                        lr.setNumberDay(resultSet.getInt("numberDay"));
                        lr.setContent(resultSet.getString("content"));
                        lr.setStatus(resultSet.getString("status"));
                        leaveRequests.add(lr);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return leaveRequests;
    }

}
