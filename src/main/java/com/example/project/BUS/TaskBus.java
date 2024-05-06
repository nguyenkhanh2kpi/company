package com.example.project.BUS;

import com.example.project.DAO.EmployeeDAO;
import com.example.project.DAO.TaskDAO;
import com.example.project.DAO.TeamDAO;
import com.example.project.DTO.*;
import com.example.project.controllers.AddTaskController;
import com.example.project.controllers.ViewOrUpdateTaskController;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TaskBus {

    TaskDAO taskDAO = new TaskDAO();
    UserBUS userBUS = new UserBUS();
    EmployeeDAO employeeDAO = new EmployeeDAO();
    ProjectBUS projectBUS = new ProjectBUS();
    TeamDAO teamDAO = new TeamDAO();
    TeamBUS  teamBUS = new TeamBUS();
    public boolean InsertTask(AddTaskController controller, String username) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName(controller.getNameTxt().getText());
        taskDTO.setDescription(controller.getDesTxt().getText());
        taskDTO.setIdCreator(userBUS.getIDFromUsername(username));
        taskDTO.setIdAssignee(controller.getAssignedCmb().getSelectionModel().getSelectedItem().getId());
        taskDTO.setIdProject(controller.getProjectCmb().getSelectionModel().getSelectedItem().getId());
        taskDTO.setDeadline(Date.valueOf(controller.getDueDatePic().getValue()));
        taskDTO.setIdTeam(projectBUS.getIdTeamFromIDProject(taskDTO.getIdProject()));
        BigDecimal bonusDecimal = new BigDecimal(controller.getBonusPic().getText());
        taskDTO.setBonus(bonusDecimal);
        return taskDAO.insertTask1(taskDTO);
    }
    public boolean UpdateTask(ViewOrUpdateTaskController controller, TaskDTO taskDTO) {
        taskDTO.setTaskName(controller.getTitletxt().getText());
        taskDTO.setDescription(controller.getDestxt().getText());
        taskDTO.setIdAssignee(controller.getAssignesCmb().getSelectionModel().getSelectedItem().getId());
        taskDTO.setIdProject(controller.getProjectCmb().getSelectionModel().getSelectedItem().getId());
        taskDTO.setDeadline(Date.valueOf(controller.getDueDatetxt().getValue()));
        taskDTO.setIdTeam(projectBUS.getIdTeamFromIDProject(taskDTO.getIdProject()));
        BigDecimal bonusDecimal = new BigDecimal(controller.getBonustxt().getText());
        taskDTO.setBonus(bonusDecimal);
        return taskDAO.updateTask(taskDTO);
    }

    public List<TaskDTO> getAllTask() {
        return taskDAO.getAllTasks();
    }

    public List<UserDTO> getMembersByProject(ProjectDTO projectDTO) {
        List<UserDTO> usersInProject = new ArrayList<>();
        List<UserDTO> allUsers = employeeDAO.getAll();
        List<UserTeamDTO> allUserTeamRelationships = teamBUS.getAllUserTeam();


        for (UserTeamDTO userTeam : allUserTeamRelationships) {
            if (userTeam.getIdTeam() == projectDTO.getIdTeam()) {
                int userId = userTeam.getIdUser();
                for (UserDTO user : allUsers) {
                    if (user.getId() == userId && !usersInProject.contains(user)) {
                        usersInProject.add(user);
                        break;
                    }
                }
            }
        }

        return usersInProject;
    }

}
