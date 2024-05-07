package com.example.project.controllers;

import com.example.project.Service.LoginService;
import com.example.project.Untilities.CustomAlert;
import com.example.project.core.Routes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    @FXML
    private Label btnForgot;


    private final LoginService loginService = new LoginService();

    public void Submit(ActionEvent actionEvent) {
        if (loginService.authenticateUser(Username.getText(), Password.getText())) {
            Routes routes = new Routes(new Stage());
            routes.goToHome((Stage) Username.getScene().getWindow(),Username.getText());
        } else {
            CustomAlert.showAlertError("Login", "Tên đăng nhập hoặc mật khẩu không đúng");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onForgot() {
        Routes routes = new Routes(new Stage());
        routes.goToReset();
    }
}
