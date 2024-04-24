package com.example.project.core;

import com.example.project.DAO.EmployeeDAO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.Utils;
import com.example.project.controllers.HomeController;
import com.example.project.controllers.UserProfileController;
import com.example.project.core.enums.Role;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;

public class Setup {
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
}
