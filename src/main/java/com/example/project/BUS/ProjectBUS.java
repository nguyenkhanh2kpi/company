package com.example.project.BUS;

import com.example.project.DAO.ProjectDAO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.controllers.ProjectController;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class ProjectBUS {
    ProjectDAO projectDAO = new ProjectDAO();
    UserBUS userBUS = new UserBUS();
    public boolean addProject(ProjectController controller, String username) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(controller.getProjectNametxt().getText());
        projectDTO.setDescription(controller.getProjectDestxt().getText());
        BigDecimal bonusDecimal = new BigDecimal(controller.getBunusTxt().getText());
        projectDTO.setBonus(bonusDecimal);
        projectDTO.setIdCreator(userBUS.getIDFromUsername(username));
//        projectDTO.setIdCreator(1);
        projectDTO.setIdTeam(controller.getAssignToTxt().getSelectionModel().getSelectedItem().getId());
        projectDTO.setStartDate(Date.valueOf(controller.getStartDateTxt().getValue()));
        projectDTO.setEndDate(Date.valueOf(controller.getEndDateTxt().getValue()));
        projectDTO.setIdAssignee(userBUS.getIDFromUsername(username));
        return projectDAO.insertProject(projectDTO);
    }

    public int getIdTeamFromIDProject(int idProject) {
        List<ProjectDTO> projectDTOS = projectDAO.getAllProjects();
        for (ProjectDTO project : projectDTOS) {
            if (project.getId() == idProject) {
                return project.getIdTeam();
            }
        }return -1;
    }


}
