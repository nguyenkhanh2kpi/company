package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.TaskDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    //ThemTask
    private static final String INSERT_TASK = "INSERT INTO company.task VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //SuaTask
    private static final String UPDATE_TASK = "UPDATE company.task SET idCreator = ?, idAssignee = ?, taskName = ?, description = ?, deadline = ?, progress = ?, idTeam = ?, bonus = ?, idProject = ? WHERE id = ?";

    //XoaTask
    private static final String DELETE_TASK = "DELETE FROM company.task WHERE id = ?";

    //TimKiemTask
    //By id
    private static final String SEARCH_TASK_BY_ID = "SELECT * FROM company.task WHERE id = ?";
    //By creator
    private static final String SEARCH_TASK_BY_CREATOR = "SELECT * FROM company.task WHERE idCreator = ?";
    //By assignee
    private static final String SEARCH_TASK_BY_ASSIGNEE = "SELECT * FROM company.task WHERE idAsignee = ?";
    //By task name
    private static final String SEARCH_TASK_BY_TASKNAME = "SELECT * FROM company.task WHERE taskName = ?";
    //By team
    private static final String SEARCH_TASK_BY_TEAM = "SELECT * FROM company.task WHERE idTeam = ?";
    //By project
    private static final String SEARCH_TASK_BY_PROJ = "SELECT * FROM company.task WHERE idProject = ?";

    public boolean insertTask(TaskDTO task) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_TASK)) {
                statement.setInt(1, task.getId());
                statement.setInt(2, task.getIdCreator());
                statement.setInt(3, task.getIdAssignee());
                statement.setString(4, task.getTaskName());
                statement.setString(5, task.getDescription());
                statement.setDate(6, task.getDeadline());
                statement.setInt(7, task.getProgress());
                statement.setInt(8, task.getIdTeam());
                statement.setBigDecimal(9, task.getBonus());
                statement.setInt(10, task.getIdProject());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTask(TaskDTO task) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_TASK)) {
                statement.setInt(1, task.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTask(TaskDTO task) {
        try (Connection connection = new DBConnection().createConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_TASK)) {
                statement.setInt(1, task.getIdCreator());
                statement.setInt(2, task.getIdAssignee());
                statement.setString(3, task.getTaskName());
                statement.setString(4, task.getDescription());
                statement.setDate(5, task.getDeadline());
                statement.setInt(6, task.getProgress());
                statement.setInt(7, task.getIdTeam());
                statement.setBigDecimal(8, task.getBonus());
                statement.setInt(9, task.getIdProject());
                statement.setInt(10, task.getId());

                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TaskDTO> searchUser(String information, int mode) {
        List<TaskDTO> results = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection()) {
            switch (mode) {
                case 0:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TASK_BY_ID)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TaskDTO task = new TaskDTO();
                                task.setId(resultSet.getInt(1));
                                task.setIdCreator(resultSet.getInt(2));
                                task.setIdAssignee(resultSet.getInt(3));
                                task.setTaskName(resultSet.getString(4));
                                task.setDescription(resultSet.getString(5));
                                task.setDeadline(resultSet.getDate(6));
                                task.setProgress(resultSet.getInt(7));
                                task.setIdTeam(resultSet.getInt(8));
                                task.setBonus(resultSet.getBigDecimal(9));
                                task.setIdProject(resultSet.getInt(10));

                                results.add(task);
                            }
                        }
                    }
                    break;
                case 1:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TASK_BY_CREATOR)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TaskDTO task = new TaskDTO();
                                task.setId(resultSet.getInt(1));
                                task.setIdCreator(resultSet.getInt(2));
                                task.setIdAssignee(resultSet.getInt(3));
                                task.setTaskName(resultSet.getString(4));
                                task.setDescription(resultSet.getString(5));
                                task.setDeadline(resultSet.getDate(6));
                                task.setProgress(resultSet.getInt(7));
                                task.setIdTeam(resultSet.getInt(8));
                                task.setBonus(resultSet.getBigDecimal(9));
                                task.setIdProject(resultSet.getInt(10));

                                results.add(task);
                            }
                        }
                    }
                    break;
                case 2:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TASK_BY_ASSIGNEE)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TaskDTO task = new TaskDTO();
                                task.setId(resultSet.getInt(1));
                                task.setIdCreator(resultSet.getInt(2));
                                task.setIdAssignee(resultSet.getInt(3));
                                task.setTaskName(resultSet.getString(4));
                                task.setDescription(resultSet.getString(5));
                                task.setDeadline(resultSet.getDate(6));
                                task.setProgress(resultSet.getInt(7));
                                task.setIdTeam(resultSet.getInt(8));
                                task.setBonus(resultSet.getBigDecimal(9));
                                task.setIdProject(resultSet.getInt(10));

                                results.add(task);
                            }
                        }
                    }
                    break;
                case 3:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TASK_BY_TASKNAME)) {
                        statement.setString(1, information);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TaskDTO task = new TaskDTO();
                                task.setId(resultSet.getInt(1));
                                task.setIdCreator(resultSet.getInt(2));
                                task.setIdAssignee(resultSet.getInt(3));
                                task.setTaskName(resultSet.getString(4));
                                task.setDescription(resultSet.getString(5));
                                task.setDeadline(resultSet.getDate(6));
                                task.setProgress(resultSet.getInt(7));
                                task.setIdTeam(resultSet.getInt(8));
                                task.setBonus(resultSet.getBigDecimal(9));
                                task.setIdProject(resultSet.getInt(10));

                                results.add(task);
                            }
                        }
                    }
                    break;
                case 4:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TASK_BY_TEAM)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TaskDTO task = new TaskDTO();
                                task.setId(resultSet.getInt(1));
                                task.setIdCreator(resultSet.getInt(2));
                                task.setIdAssignee(resultSet.getInt(3));
                                task.setTaskName(resultSet.getString(4));
                                task.setDescription(resultSet.getString(5));
                                task.setDeadline(resultSet.getDate(6));
                                task.setProgress(resultSet.getInt(7));
                                task.setIdTeam(resultSet.getInt(8));
                                task.setBonus(resultSet.getBigDecimal(9));
                                task.setIdProject(resultSet.getInt(10));

                                results.add(task);
                            }
                        }
                    }
                    break;
                case 5:
                    try (PreparedStatement statement = connection.prepareStatement(SEARCH_TASK_BY_PROJ)) {
                        int id = Integer.parseInt(information);
                        statement.setInt(1, id);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            while(resultSet.next()){
                                TaskDTO task = new TaskDTO();
                                task.setId(resultSet.getInt(1));
                                task.setIdCreator(resultSet.getInt(2));
                                task.setIdAssignee(resultSet.getInt(3));
                                task.setTaskName(resultSet.getString(4));
                                task.setDescription(resultSet.getString(5));
                                task.setDeadline(resultSet.getDate(6));
                                task.setProgress(resultSet.getInt(7));
                                task.setIdTeam(resultSet.getInt(8));
                                task.setBonus(resultSet.getBigDecimal(9));
                                task.setIdProject(resultSet.getInt(10));

                                results.add(task);
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
