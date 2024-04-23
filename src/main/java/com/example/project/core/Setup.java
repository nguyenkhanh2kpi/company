package com.example.project.core;

import com.example.project.controllers.HomeController;
import com.example.project.controllers.UserProfileController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Setup {
    public void setUpHomeController(HomeController controller,String username) {
        controller.setUserName(username);
        Button button = controller.getButtonUserName();
        button.setText(username);
        controller.setButtonUserName(button);
    }

    public void setUpUserProfileController(UserProfileController controller, String username) {
        TextField usernameTxt = controller.getUsernameTxt();
        usernameTxt.setText(username);
//        controller.setUsernameTxt(usernameTxt);
    }

    public void setUpTaskController() {

    }
    public void setUpViewEditTaskController() {
    }

    public void setUpLeaveAddController() {
    }

    public void setUpViewEditLeaveController() {
    }
}
