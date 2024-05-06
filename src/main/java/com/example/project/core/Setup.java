package com.example.project.core;

import com.example.project.BUS.TeamBUS;
import com.example.project.BUS.UserBUS;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DTO.LeaveRequestDTO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.DTO.TeamDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.Utils;
import com.example.project.controllers.*;
import com.example.project.core.control.ProjectControl;
import com.example.project.core.control.TeamControl;
import com.example.project.core.enums.LeaveStatus;
import com.example.project.core.enums.Role;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.stream.Collectors;

public class Setup {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();

    public void setUpHomeController(HomeController controller, String username) {
        controller.getButtonUserName().setText(username);
        controller.setUserName(username);

        UserBUS userBUS = new UserBUS();
        UserDTO user = userBUS.getUserFromUsername(username);
        if(user.getIdRole()==1) {
            controller.getProjectBtn().setDisable(true);
            controller.getTeamBtn().setDisable(true);
            controller.getUserBtn().setDisable(true);
            controller.getSalaryBtn().setDisable(true);
        }

    }

    public void setUpUserProfileController(UserProfileController controller, String username) {
        List<UserDTO> userDTOList = new EmployeeDAO().searchUser(username, 1);

        if (!userDTOList.isEmpty()) {
            UserDTO userDTO = userDTOList.get(0);
            controller.setUserDTO(userDTO);

            TextField usernameTxt = controller.getUsernameTxt();
            usernameTxt.setText(userDTO.getUsername());

            TextField emailTxt = controller.getEmailTxt();
            emailTxt.setText(userDTO.getEmail());

            TextField fullNameTxt = controller.getFullNameTxt();
            fullNameTxt.setText(userDTO.getFullName());

            TextField phoneTxt = controller.getPhoneTxt();
            phoneTxt.setText(userDTO.getPhoneNumber());

            TextField AddressTxt = controller.getAddressTxt();
            AddressTxt.setText(userDTO.getAddress());

            TextField passwordTxt = controller.getPasswordTxt();
            passwordTxt.setText(userDTO.getPASSWORD());

            TextField repass = controller.getRePasswordTxt();
            repass.setText(userDTO.getPASSWORD());

            ImageView imageView = controller.getImageView();
            imageView.setImage(new Image(userDTO.getAvatar()));

            ComboBox<Role> comboBox = controller.getRoleCmb();
            comboBox.setValue(Utils.getRoleFromID(userDTO.getIdRole()));

        }
    }

    public void setUpTaskController(AddTaskController controller, String username) {
        controller.setUsername(username);
    }

    public void setUpViewEditTaskController() {
    }

    public void setUpLeaveAddController(LeaveRequestController controller, String username) {
        controller.setUsername(username);
    }


    public void setUpViewEditLeaveController(ViewOrUpdateLeaveController controller, LeaveRequestDTO leaveRequestDTO, String username) {
        controller.getUserTxt().setText(String.valueOf(leaveRequestDTO.getIdUser()));
        controller.getContentTxt().setText(leaveRequestDTO.getContent());
        controller.getDatePic().setValue(leaveRequestDTO.getStartDate().toLocalDate());
        controller.getFromPic().setValue(leaveRequestDTO.getStartDate().toLocalDate());
        controller.getToPic().setValue(leaveRequestDTO.getEndDate().toLocalDate());
        controller.getStatusCmb().setValue(LeaveStatus.valueOf(leaveRequestDTO.getStatus()));
        int approverId = leaveRequestDTO.getIdApprover();
        controller.getApproveUsertxt().setText(approverId != 0 ? String.valueOf(approverId) : "");
        controller.getDayTxt().setText(String.valueOf(leaveRequestDTO.getNumberDay()) + "Day");
        controller.setLeaveRequestDTO(leaveRequestDTO);
        controller.setUsername(username);
    }

    public void setProjectController(ProjectController controller, String username, ProjectControl projectControl) {
        controller.setUsername(username);
        controller.setProjectControl(projectControl);
    }

    public void setUpViewEditProjectController(ViewOrUpdateProjectController controller, ProjectDTO projectDTO, ProjectControl projectControl) {
        TeamBUS teamBUS = new TeamBUS();
        controller.setSelected(projectDTO);
        controller.getNameTxt().setText(projectDTO.getName());
        controller.getDesTxt().setText(projectDTO.getDescription());
        controller.getBonusTxt().setText(projectDTO.getBonus().toString());
        controller.getEndDatePic().setValue(projectDTO.getEndDate().toLocalDate());
        controller.getStartDatePic().setValue(projectDTO.getStartDate().toLocalDate());
        controller.getTeamCmb().setValue(teamBUS.getTeamDTObyId(projectDTO.getIdTeam()));
        controller.setProjectControl(projectControl);
    }

    private int getLeaderIndex(List<UserDTO> leaders, int idLeader) {
        for (int i = 0; i < leaders.size(); i++) {
            if (leaders.get(i).getId() == idLeader) {
                return i;
            }
        }
        return -1;
    }

    public void setUpAddTeamController(boolean isUpdate, AddTeamController controller, TeamControl control, TeamDTO teamDTO) {
        List<UserDTO> users = employeeDAO.getAll();
        List<UserDTO> leaders = users.stream()
                .filter(userDTO -> userDTO.getIdPosition() == 1)
                .collect(Collectors.toList());

        controller.setTeamControl(control);
        controller.getNameTxt().setText("");
        controller.getUpdateText().setText(isUpdate ? "Update" : "New a team");
        if (isUpdate) {
            controller.getNameTxt().setText(teamDTO.getName());
            controller.getLeaderCmb().getSelectionModel().select(getLeaderIndex(leaders, teamDTO.getIdLeader()));
            controller.getDesTxt().setText(teamDTO.getDescription());
            controller.setTeamDTO(teamDTO);
        }
        controller.getNewMem().setDisable(!isUpdate);
        controller.getNewMem1().setDisable(!isUpdate);
        if(isUpdate) {
            controller.setUpListview(controller.getListview());

        }
    }


}
