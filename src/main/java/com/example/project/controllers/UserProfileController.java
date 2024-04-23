package com.example.project.controllers;

import com.example.project.Untilities.CustomAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {

    @FXML
    TextField usernameTxt;

    @FXML
    Button saveProfileButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveProfileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomAlert.showAlertError("ads","das");
            }
        });
    }

    public TextField getUsernameTxt() {
        return usernameTxt;
    }

    public void setUsernameTxt(TextField usernameTxt) {
        this.usernameTxt = usernameTxt;
    }



}
