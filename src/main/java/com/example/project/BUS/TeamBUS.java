package com.example.project.BUS;

import com.example.project.DAO.TeamDAO;
import com.example.project.DTO.TeamDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.DTO.UserTeamDTO;
import com.example.project.controllers.AddTeamController;

import java.util.List;
import java.util.stream.Collectors;

public class TeamBUS {

/// add
    TeamDAO teamDAO = new TeamDAO();
    UserBUS userBUS = new UserBUS();
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


    public TeamDTO getTeamDTObyId(int id) {
        List<TeamDTO> teamDTOS = teamDAO.getAllTeams();
        var response = teamDTOS.stream().filter(teamDTO -> {
            return teamDTO.getId()==id;
        }).collect(Collectors.toList());
        return response.get(0);
    }

    public boolean insertMember(AddTeamController controller, String username, TeamDTO teamDTO) {
        UserDTO userDTO = userBUS.getUserFromUsername(username);
        UserTeamDTO userTeamDTO = new UserTeamDTO();
        userTeamDTO.setIdUser(userDTO.getId());
        return teamDAO.insertTeamMember(userTeamDTO,teamDTO.getId());
    }

    public List<UserTeamDTO> getAllUserTeam() {
        List<UserTeamDTO> reponse = teamDAO.getAllUserTeams();
        return reponse;
    }

    public boolean deleteMember(AddTeamController addTeamController, String username, TeamDTO teamDTO) {
        UserDTO userDTO = userBUS.getUserFromUsername(username);
        return teamDAO.deleteTeamMember(userDTO.getId(),teamDTO.getId());
    }
}
