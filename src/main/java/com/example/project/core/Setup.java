package com.example.project.core;

import com.example.project.DAO.EmployeeDAO;
import com.example.project.DTO.TeamDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.Utils;
import com.example.project.controllers.AddTeamController;
import com.example.project.controllers.HomeController;
import com.example.project.controllers.UserProfileController;
import com.example.project.core.control.TeamControl;
import com.example.project.core.enums.Role;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.stream.Collectors;

public class Setup {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    public void setUpHomeController(HomeController controller, String username) {
        Button button = controller.getButtonUserName();
        button.setText(username);
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

    public void setUpTaskController() {

    }

    public void setUpViewEditTaskController() {
    }

    public void setUpLeaveAddController() {
    }

    public void setUpViewEditLeaveController() {
    }

    public void setProjectController() {
    }

    public void setUpViewEditProjectController() {
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
        controller.getNameTxt().setText("asd");
        controller.getUpdateText().setText(isUpdate ? "update" : "add");
        if(isUpdate) {
            controller.getNameTxt().setText(teamDTO.getName());
            controller.getLeaderCmb().getSelectionModel().select(getLeaderIndex(leaders, teamDTO.getIdLeader()));
            controller.getDesTxt().setText(teamDTO.getDescription());
            controller.setTeamDTO(teamDTO);
        }

    }



}
