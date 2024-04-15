package com.example.project.controllers;

import com.example.project.Service.LoginService;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    private final LoginService loginService = new LoginService();


    public void Submit(ActionEvent actionEvent) throws IOException {
        if (loginService.authenticateUser(Username.getText(), Password.getText())) {
            Utils.getHomePage(new Stage(), (Stage) Username.getScene().getWindow());
        } else {
            CustomAlert.showAlertError("Login","Đăng nhập thất bại");
        }
    }



}
