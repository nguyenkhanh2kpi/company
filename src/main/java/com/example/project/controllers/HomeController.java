package com.example.project.controllers;

import com.example.project.Untilities.Utils;
import com.example.project.core.Routes;
import com.example.project.core.enums.Content;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    BorderPane contentContainer;

    @FXML
    Button buttonUserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            HomeTabClick();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Button getButtonUserName() {
        return buttonUserName;
    }

    public void setButtonUserName(Button buttonUserName) {
        this.buttonUserName = buttonUserName;
    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    Routes routes = new Routes(new Stage());

    public void UserClick() throws IOException {
        Utils.getContent(contentContainer, Content.USER);
    }

    public void ProjectTabClick() throws IOException {
        Utils.getContent(contentContainer, Content.PROJECT);
    }

    public void LeaveTabClick() throws IOException {
        Utils.getContent(contentContainer, Content.LEAVE);
    }

    public void SalaryClick() throws IOException {
        Utils.getContent(contentContainer, Content.SALARY);
    }

    public void TaskTabClick() throws IOException {
        Utils.getContent(contentContainer, Content.TASK);
    }

    public void HomeTabClick() throws IOException {
        Utils.getContent(contentContainer, Content.HOME);
    }

    public void TimeTabClick() throws IOException {
        Utils.getContent(contentContainer, Content.TIME);
    }

    public void TeamTabClick() throws IOException {
        Utils.getContent(contentContainer, Content.TEAM);
    }

    public void ProfileClick() {
        routes.goToUserProfile(buttonUserName.getText());
    }



}
