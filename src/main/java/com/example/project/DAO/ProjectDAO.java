package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.ProjectDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    //ThemDuAn
    private static final String INSERT_PROJ = "INSERT INTO company.project VALUES(?, ?, ?, ? ,? ,? , ?, ?, ? ,?)";

    //SuaDuAn
    private static final String UPDATE_PROJ = "UPDATE company.project SET idCreator = ?, idAssignee = ?, name = ?, description = ?, startDate = ?, endDate = ?, progress = ?, idTeam = ?, bonus = ? WHERE id = ?";

    //XoaDuAn
    private static final String DELETE_PROJ = "DELETE FROM company.project WHERE id = ?";

    //TimKiemDuAn
    //By id
    private static final String SEARCH_PROJ_BY_ID = "SELECT * FROM company.project WHERE id = ?";
    //By creator
    private static final String SEARCH_PROJ_BY_CREATOR = "SELECT * FROM company.project WHERE idCreator = ?";
    //By assignee
    private static final String SEARCH_PROJ_BY_ASSIGNEE = "SELECT * FROM company.project WHERE idAssignee = ?";
    //By name
    private static final String SEARCH_PROJ_BY_NAME = "SELECT * FROM company.project WHERE name = ?";
    //By team
    private static final String SEARCH_PROJ_BY_TEAM = "SELECT * FROM company.project WHERE idTeam = ?";

    private static final String INSERT_PROJ1= "INSERT INTO company.project (idCreator, idAssignee, name, description, startDate, endDate, progress, idTeam, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean insertProject(ProjectDTO proj) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_PROJ1)) {
//                statement.setInt(1, proj.getId());
//                statement.setInt(2, proj.getIdCreator());
//                statement.setInt(3, proj.getIdAssignee());
//                statement.setString(4, proj.getName());
//                statement.setString(5, proj.getDescription());
//                statement.setDate(6, proj.getStartDate());
//                statement.setDate(7, proj.getEndDate());
//                statement.setInt(8, proj.getProgress());
//                statement.setInt(9, proj.getIdTeam());
//                statement.setBigDecimal(10, proj.getBonus());
                statement.setInt(1, proj.getIdCreator());
                statement.setInt(2, proj.getIdAssignee());
                statement.setString(3, proj.getName());
                statement.setString(4, proj.getDescription());
                statement.setDate(5, proj.getStartDate());
                statement.setDate(6, proj.getEndDate());
                statement.setInt(7, proj.getProgress());
                statement.setInt(8, proj.getIdTeam());
                statement.setBigDecimal(9, proj.getBonus());
                statement.executeUpdate();
                return true;

//                statement.executeUpdate();
//                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProject(ProjectDTO proj) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_PROJ)) {
                statement.setInt(1, proj.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProject(ProjectDTO proj) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_PROJ)) {
                statement.setInt(1, proj.getIdCreator());
                statement.setInt(2, proj.getIdAssignee());
                statement.setString(3, proj.getName());
                statement.setString(4, proj.getDescription());
                statement.setDate(5, proj.getStartDate());
                statement.setDate(6, proj.getEndDate());
                statement.setInt(7, proj.getProgress());
                statement.setInt(8, proj.getIdTeam());
                statement.setBigDecimal(9, proj.getBonus());
                statement.setInt(10, proj.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> results = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection()) {
            String query = "SELECT * FROM project";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ProjectDTO proj = new ProjectDTO();
                        proj.setId(resultSet.getInt("id"));
                        proj.setIdCreator(resultSet.getInt("idCreator"));
                        proj.setIdAssignee(resultSet.getInt("idAssignee"));
                        proj.setName(resultSet.getString("name"));
                        proj.setDescription(resultSet.getString("description"));
                        proj.setStartDate(resultSet.getDate("startDate"));
                        proj.setEndDate(resultSet.getDate("endDate"));
                        proj.setProgress(resultSet.getInt("progress"));
                        proj.setIdTeam(resultSet.getInt("idTeam"));
                        proj.setBonus(resultSet.getBigDecimal("bonus"));

                        results.add(proj);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    public List<ProjectDTO> searchProject(String information, int mode) {
        List<ProjectDTO> results = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection()) {
            switch (mode) {
                case 0:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_PROJ_BY_ID)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                ProjectDTO proj = new ProjectDTO();
                                proj.setId(resultSet.getInt(1));
                                proj.setIdCreator(resultSet.getInt(2));
                                proj.setIdAssignee(resultSet.getInt(3));
                                proj.setName(resultSet.getString(4));
                                proj.setDescription(resultSet.getString(5));
                                proj.setStartDate(resultSet.getDate(6));
                                proj.setEndDate(resultSet.getDate(7));
                                proj.setProgress(resultSet.getInt(8));
                                proj.setIdTeam(resultSet.getInt(9));
                                proj.setBonus(resultSet.getBigDecimal(10));

                                results.add(proj);
                            }
                        }
                    }
                    break;
                case 1:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_PROJ_BY_CREATOR)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                ProjectDTO proj = new ProjectDTO();
                                proj.setId(resultSet.getInt(1));
                                proj.setIdCreator(resultSet.getInt(2));
                                proj.setIdAssignee(resultSet.getInt(3));
                                proj.setName(resultSet.getString(4));
                                proj.setDescription(resultSet.getString(5));
                                proj.setStartDate(resultSet.getDate(6));
                                proj.setEndDate(resultSet.getDate(7));
                                proj.setProgress(resultSet.getInt(8));
                                proj.setIdTeam(resultSet.getInt(9));
                                proj.setBonus(resultSet.getBigDecimal(10));

                                results.add(proj);
                            }
                        }
                    }
                    break;
                case 2:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_PROJ_BY_ASSIGNEE)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                ProjectDTO proj = new ProjectDTO();
                                proj.setId(resultSet.getInt(1));
                                proj.setIdCreator(resultSet.getInt(2));
                                proj.setIdAssignee(resultSet.getInt(3));
                                proj.setName(resultSet.getString(4));
                                proj.setDescription(resultSet.getString(5));
                                proj.setStartDate(resultSet.getDate(6));
                                proj.setEndDate(resultSet.getDate(7));
                                proj.setProgress(resultSet.getInt(8));
                                proj.setIdTeam(resultSet.getInt(9));
                                proj.setBonus(resultSet.getBigDecimal(10));

                                results.add(proj);
                            }
                        }
                    }
                    break;
                case 3:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_PROJ_BY_NAME)) {
                        statement.setString(1, information);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                ProjectDTO proj = new ProjectDTO();
                                proj.setId(resultSet.getInt(1));
                                proj.setIdCreator(resultSet.getInt(2));
                                proj.setIdAssignee(resultSet.getInt(3));
                                proj.setName(resultSet.getString(4));
                                proj.setDescription(resultSet.getString(5));
                                proj.setStartDate(resultSet.getDate(6));
                                proj.setEndDate(resultSet.getDate(7));
                                proj.setProgress(resultSet.getInt(8));
                                proj.setIdTeam(resultSet.getInt(9));
                                proj.setBonus(resultSet.getBigDecimal(10));

                                results.add(proj);
                            }
                        }
                    }
                    break;
                case 4:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_PROJ_BY_TEAM)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                ProjectDTO proj = new ProjectDTO();
                                proj.setId(resultSet.getInt(1));
                                proj.setIdCreator(resultSet.getInt(2));
                                proj.setIdAssignee(resultSet.getInt(3));
                                proj.setName(resultSet.getString(4));
                                proj.setDescription(resultSet.getString(5));
                                proj.setStartDate(resultSet.getDate(6));
                                proj.setEndDate(resultSet.getDate(7));
                                proj.setProgress(resultSet.getInt(8));
                                proj.setIdTeam(resultSet.getInt(9));
                                proj.setBonus(resultSet.getBigDecimal(10));

                                results.add(proj);
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
