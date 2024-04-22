package com.example.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserProfileController {

    @FXML
    TextField usernameTxt;

    public TextField getUsernameTxt() {
        return usernameTxt;
    }

    public void setUsernameTxt(TextField usernameTxt) {
        this.usernameTxt = usernameTxt;
    }


}
