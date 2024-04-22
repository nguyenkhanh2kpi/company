package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.TeamDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.DTO.UserTeamDTO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class TeamDAO {
    //ThemNhom
    private static final String INSERT_TEAM = "INSERT INTO company.teams VALUES(?, ?, ?, ?, ?)";
    //ThemTV
    private static final String INSERT_TEAM_MEMBER = "INSERT INTO company.user_team VALUES(?, ?, ?)";

    //SuaNhom
    private static final String UPDATE_TEAM = "UPDATE company.teams SET name = ?, description = ?, idLeader = ?, avatar = ?";

    //XoaNhom
    private static final String DELETE_TEAM = "DELETE FROM company.teams WHERE id = ?";
    //XoaTV
    private static final String DELETE_TEAM_MEMBER = "DELETE FROM company.user_team WHERE id = ?";

    //TimKiemNhom
    //By id
    private static final String SEARCH_TEAM_BY_ID = "SELECT * FROM company.teams WHERE id = ?";
    //By name
    private static final String SEARCH_TEAM_BY_NAME = "SELECT * FROM company.teams WHERE name = ?";
    //By leader
    private static final String SEARCH_TEAM_BY_LEADER = "SELECT * FROM company.teams WHERE idLeader = ?";

    public boolean insertTeam(TeamDTO team) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_TEAM)) {
                statement.setInt(1, team.getId());
                statement.setString(2, team.getName());
                statement.setString(3, team.getDescription());
                statement.setInt(4, team.getIdLeader());
                statement.setBlob(5, team.getAvatar());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertTeamMember(UserTeamDTO team, int idTeam) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_TEAM_MEMBER)) {
                statement.setInt(1, team.getId());
                statement.setInt(2, team.getIdUser());
                statement.setInt(3, idTeam);

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTeam(TeamDTO team) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_TEAM)) {
                statement.setString(1, team.getName());
                statement.setString(2, team.getDescription());
                statement.setInt(3, team.getIdLeader());
                statement.setBlob(4, team.getAvatar());
                statement.setInt(5, team.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTeam(TeamDTO team) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_TEAM)) {
                statement.setInt(1, team.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTeamMember(UserTeamDTO team) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_TEAM_MEMBER)) {
                statement.setInt(1, team.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TeamDTO> searchTeam(String information, int mode) {
        List<TeamDTO> results = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection()) {
            switch (mode){
                case 0:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TEAM_BY_ID)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TeamDTO team = new TeamDTO();
                                team.setId(resultSet.getInt(1));
                                team.setName(resultSet.getString(2));
                                team.setDescription(resultSet.getString(3));
                                team.setIdLeader(resultSet.getInt(4));
                                team.setAvatar(resultSet.getBlob(5));
                                results.add(team);
                            }
                        }
                    }
                    break;
                case 1:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TEAM_BY_NAME)) {
                        statement.setString(1, information);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TeamDTO team = new TeamDTO();
                                team.setId(resultSet.getInt(1));
                                team.setName(resultSet.getString(2));
                                team.setDescription(resultSet.getString(3));
                                team.setIdLeader(resultSet.getInt(4));
                                team.setAvatar(resultSet.getBlob(5));
                                results.add(team);
                            }
                        }
                    }
                    break;
                case 2:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TEAM_BY_LEADER)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TeamDTO team = new TeamDTO();
                                team.setId(resultSet.getInt(1));
                                team.setName(resultSet.getString(2));
                                team.setDescription(resultSet.getString(3));
                                team.setIdLeader(resultSet.getInt(4));
                                team.setAvatar(resultSet.getBlob(5));
                                results.add(team);
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
