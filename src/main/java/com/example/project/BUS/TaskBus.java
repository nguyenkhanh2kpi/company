package com.example.project.BUS;

import com.example.project.DAO.TaskDAO;
import com.example.project.DTO.TaskDTO;
import com.example.project.controllers.AddTaskController;
import java.math.BigDecimal;
import java.sql.Date;

public class TaskBus {

    TaskDAO taskDAO = new TaskDAO();
    UserBUS userBUS = new UserBUS();
    ProjectBUS projectBUS = new ProjectBUS();
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
}
