package com.example.project.BUS;

import com.example.project.DTO.TeamDTO;
import com.example.project.controllers.AddTeamController;

public class TeamBUS {

/// add
    public TeamDTO controllerToTeamDTO(AddTeamController controller) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setAvatar(null);
        teamDTO.setName(controller.getNameTxt().getText());
        teamDTO.setDescription(controller.getDesTxt().getText());
        teamDTO.setIdLeader(controller.getLeaderCmb().getSelectionModel().getSelectedItem().getId());
        return teamDTO;
    }

    public TeamDTO controllerToTeamDTO(AddTeamController controller, TeamDTO teamDTO) {
        teamDTO.setAvatar(null);
        teamDTO.setName(controller.getNameTxt().getText());
        teamDTO.setDescription(controller.getDesTxt().getText());
        teamDTO.setIdLeader(controller.getLeaderCmb().getSelectionModel().getSelectedItem().getId());
        return teamDTO;
    }

}
