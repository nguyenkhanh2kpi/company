package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.CheckinCheckoutDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckinCheckoutDAO {
    private static final String INSERT_CHECKIN_CHECKOUT = "INSERT INTO checkin_checkout (idUser, checkinTime, checkoutTime, totalHours, date) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_CHECKINS = "SELECT cc.id, cc.idUser, cc.checkinTime, cc.checkoutTime, cc.totalHours, cc.date, u.fullName\n" +
            "FROM checkin_checkout cc\n" +
            "INNER JOIN users u ON cc.idUser = u.id;";

    private static final String SELECT_ALL_CHECKINS_OF_CURRENT_MONTH = "SELECT cc.id, cc.idUser, cc.checkinTime, cc.checkoutTime, cc.totalHours, cc.date, u.fullName\n" +
            "FROM checkin_checkout cc\n" +
            "INNER JOIN users u ON cc.idUser = u.id\n" +
            "WHERE cc.idUser = ?\n" +
            "AND MONTH(cc.checkinTime) = MONTH(current_date())\n" +
            "AND YEAR(cc.checkinTime) = YEAR(current_date());";
    private static final String UPDATE_CHECKOUT_CHECKIN = "UPDATE checkin_checkout SET checkoutTime = ?, totalHours = ? WHERE idUser = ? AND date = ?";

    public boolean checkin(CheckinCheckoutDTO cico) {
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CHECKIN_CHECKOUT)) {
            statement.setInt(1, cico.getIdUser());
            statement.setTimestamp(2, Timestamp.valueOf(cico.getCheckinTime()));
//            statement.setTimestamp(3, cico.getCheckoutTime() != null ? Timestamp.valueOf(cico.getCheckoutTime()) : null);
//            statement.setFloat(4, cico.getTotalHours());
            statement.setNull(3, Types.TIMESTAMP); // Đặt giá trị null cho checkoutTime
            statement.setNull(4, Types.FLOAT);
            statement.setTimestamp(5, Timestamp.valueOf(cico.getDate()));
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkout(CheckinCheckoutDTO cico) {
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CHECKOUT_CHECKIN)) {
            statement.setTimestamp(1, Timestamp.valueOf(cico.getCheckoutTime()));
            statement.setFloat(2, cico.getTotalHours());
            statement.setInt(3, cico.getIdUser());
            statement.setTimestamp(4, Timestamp.valueOf(cico.getDate()));
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<CheckinCheckoutDTO> getAllCheckins() {
        List<CheckinCheckoutDTO> checkins = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CHECKINS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                Timestamp checkinTime = resultSet.getTimestamp("checkinTime");
                Timestamp checkoutTime = resultSet.getTimestamp("checkoutTime");
                float totalHours = resultSet.getFloat("totalHours");
                Timestamp date = resultSet.getTimestamp("date");
                String fullName = resultSet.getString("fullName");

                CheckinCheckoutDTO cico = new CheckinCheckoutDTO(id, idUser, checkinTime.toLocalDateTime(), checkoutTime != null ? checkoutTime.toLocalDateTime() : null, totalHours, date.toLocalDateTime(), fullName);
                checkins.add(cico);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return checkins;
    }

    public List<CheckinCheckoutDTO> getAllCheckinsOfCurrentMonth(int userID) {
        List<CheckinCheckoutDTO> checkins = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CHECKINS_OF_CURRENT_MONTH)) {

            statement.setInt(1, userID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int idUser = resultSet.getInt("idUser");
                    Timestamp checkinTime = resultSet.getTimestamp("checkinTime");
                    Timestamp checkoutTime = resultSet.getTimestamp("checkoutTime");
                    float totalHours = resultSet.getFloat("totalHours");
                    Timestamp date = resultSet.getTimestamp("date");
                    String fullName = resultSet.getString("fullName");

                    CheckinCheckoutDTO cico = new CheckinCheckoutDTO(id, idUser, checkinTime.toLocalDateTime(), checkoutTime != null ? checkoutTime.toLocalDateTime() : null, totalHours, date.toLocalDateTime(), fullName);
                    checkins.add(cico);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return checkins;
    }

}