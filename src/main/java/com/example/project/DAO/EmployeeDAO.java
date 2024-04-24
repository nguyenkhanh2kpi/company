package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    //ThemNV
    private static final String INSERT_USER = "INSERT INTO company.users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //XoaNV
    private static final String DELETE_USER = "DELETE FROM company.users WHERE id = ?";

    //SuaNV
    private static final String UPDATE_USER = "UPDATE company.users SET username = ?, PASSWORD = ?, fullname = ?, email = ?, phoneNumber = ?, address = ?, avatar = ?, idRole = ?, idPosition = ? WHERE id = ?";

    //TimKiemNV
    //By id
    private static final String SEARCH_USER_BY_ID = "SELECT * FROM company.users WHERE id = ?";
    //By username
    private static final String SEARCH_USER_BY_USERNAME = "SELECT * FROM company.users WHERE username = ?";
    //By fullname
    private static final String SEARCH_USER_BY_FULLNAME = "SELECT * FROM company.users WHERE fullname = ?";
    //By email
    private static final String SEARCH_USER_BY_EMAIL = "SELECT * FROM company.users WHERE email = ?";
    //By phone number
    private static final String SEARCH_USER_BY_PHONE = "SELECT * FROM company.users WHERE phoneNumber = ?";

    public boolean insertUser(UserDTO user) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
                statement.setInt(1, user.getId());
                statement.setString(2, user.getUsername());
                statement.setString(3, user.getPASSWORD());
                statement.setString(4, user.getFullName());
                statement.setString(5, user.getEmail());
                statement.setString(6, user.getPhoneNumber());
                statement.setString(7, user.getAddress());
                statement.setString(8, user.getAvatar());
                statement.setInt(9, user.getIdRole());
                statement.setInt(10, user.getIdPosition());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(UserDTO user) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
                statement.setInt(1, user.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(UserDTO user) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPASSWORD());
                statement.setString(3, user.getFullName());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPhoneNumber());
                statement.setString(6, user.getAddress());
                statement.setString(7, user.getAvatar());
                statement.setInt(8, user.getIdRole());
                statement.setInt(9, user.getIdPosition());
                statement.setInt(10, user.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UserDTO> searchUser(String information, int mode) {
        List<UserDTO> results = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection()) {
            switch (mode) {
                case 0:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_USER_BY_ID)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                UserDTO user = new UserDTO();
                                user.setId(resultSet.getInt(1));
                                user.setUsername(resultSet.getString(2));
                                user.setPASSWORD(resultSet.getString(3));
                                user.setFullName(resultSet.getString(4));
                                user.setEmail(resultSet.getString(5));
                                user.setPhoneNumber(resultSet.getString(6));
                                user.setAddress(resultSet.getString(7));
                                user.setAvatar(resultSet.getString(8));
                                user.setIdRole(resultSet.getInt(9));
                                user.setIdPosition(resultSet.getInt(10));

                                results.add(user);
                            }
                        }
                    }
                    break;
                case 1:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_USER_BY_USERNAME)) {
                        statement.setString(1, information);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                UserDTO user = new UserDTO();
                                user.setId(resultSet.getInt(1));
                                user.setUsername(resultSet.getString(2));
                                user.setPASSWORD(resultSet.getString(3));
                                user.setFullName(resultSet.getString(4));
                                user.setEmail(resultSet.getString(5));
                                user.setPhoneNumber(resultSet.getString(6));
                                user.setAddress(resultSet.getString(7));
                                user.setAvatar(resultSet.getString(8));
                                user.setIdRole(resultSet.getInt(9));
                                user.setIdPosition(resultSet.getInt(10));

                                results.add(user);
                            }
                        }
                    }
                    break;
                case 2:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_USER_BY_FULLNAME)) {
                        statement.setString(1, information);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                UserDTO user = new UserDTO();
                                user.setId(resultSet.getInt(1));
                                user.setUsername(resultSet.getString(2));
                                user.setPASSWORD(resultSet.getString(3));
                                user.setFullName(resultSet.getString(4));
                                user.setEmail(resultSet.getString(5));
                                user.setPhoneNumber(resultSet.getString(6));
                                user.setAddress(resultSet.getString(7));
                                user.setAvatar(resultSet.getString(8));
                                user.setIdRole(resultSet.getInt(9));
                                user.setIdPosition(resultSet.getInt(10));

                                results.add(user);
                            }
                        }
                    }
                    break;
                case 3:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_USER_BY_EMAIL)) {
                        statement.setString(1, information);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                UserDTO user = new UserDTO();
                                user.setId(resultSet.getInt(1));
                                user.setUsername(resultSet.getString(2));
                                user.setPASSWORD(resultSet.getString(3));
                                user.setFullName(resultSet.getString(4));
                                user.setEmail(resultSet.getString(5));
                                user.setPhoneNumber(resultSet.getString(6));
                                user.setAddress(resultSet.getString(7));
                                user.setAvatar(resultSet.getString(8));
                                user.setIdRole(resultSet.getInt(9));
                                user.setIdPosition(resultSet.getInt(10));

                                results.add(user);
                            }
                        }
                    }
                    break;
                case 4:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_USER_BY_PHONE)) {
                        statement.setString(1, information);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                UserDTO user = new UserDTO();
                                user.setId(resultSet.getInt(1));
                                user.setUsername(resultSet.getString(2));
                                user.setPASSWORD(resultSet.getString(3));
                                user.setFullName(resultSet.getString(4));
                                user.setEmail(resultSet.getString(5));
                                user.setPhoneNumber(resultSet.getString(6));
                                user.setAddress(resultSet.getString(7));
                                user.setAvatar(resultSet.getString(8));
                                user.setIdRole(resultSet.getInt(9));
                                user.setIdPosition(resultSet.getInt(10));

                                results.add(user);
                            }
                        }
                    }
                    break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}