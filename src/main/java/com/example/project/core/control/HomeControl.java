package com.example.project.core.control;

import com.example.project.DAO.LeaveRequestDAO;
import com.example.project.DAO.ProjectDAO;
import com.example.project.DAO.TaskDAO;
import com.example.project.DAO.TeamDAO;
import com.example.project.DTO.LeaveRequestDTO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.DTO.TaskDTO;
import com.example.project.DTO.TeamDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeControl implements Initializable {

    @FXML
    public Text projectNumber;
    @FXML
    public Text taskNumber;
    @FXML
    public Text teamNumber;
    @FXML
    public Text timeNumber;
    @FXML
    public Text salaryNumber;
    @FXML
    public Text leaveNumber;
    public ProjectDAO projectDAO = new ProjectDAO();
    public TaskDAO taskDAO = new TaskDAO();
    public LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAO();
    public TeamDAO teamDAO = new TeamDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
    }

    public void LoadData(){
        List<ProjectDTO> projectData = new ArrayList<>();
        projectData = projectDAO.getAllProjects();

        List<TaskDTO> taskData = new ArrayList<>();
        taskData = taskDAO.getAllTasks();

        List<TeamDTO> teamData = new ArrayList<>();
        teamData = teamDAO.getAllTeams();

        List<LeaveRequestDTO> leaveData = new ArrayList<>();
        leaveData = leaveRequestDAO.getAllLeaveRequests();

        projectNumber.setText(Integer.toString(projectData.size()));
        taskNumber.setText(Integer.toString(taskData.size()));
        teamNumber.setText(Integer.toString(teamData.size()));
        leaveNumber.setText(Integer.toString(leaveData.size()));
    }
}
