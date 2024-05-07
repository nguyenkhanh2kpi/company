package com.example.project.controllers;

import com.example.project.BUS.UserBUS;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.Utils;
import com.example.project.core.Routes;
import com.example.project.core.enums.Content;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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


    @FXML
    private Button homeBtn;

    @FXML
    private Button leaveBtn;

    @FXML
    private Button projectBtn;

    @FXML
    private Button salaryBtn;

    @FXML
    private Button taskBtn;

    @FXML
    private Button teamBtn;

    @FXML
    private Button timeBtn;

    @FXML
    private Button userBtn;

    @FXML
    private ImageView avatar;

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

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
        Utils.getContentProject(contentContainer, buttonUserName.getText());
    }

    public void LeaveTabClick() throws IOException {
        Utils.getContentLeave(contentContainer, buttonUserName.getText());
    }

    public void SalaryClick() throws IOException {
        Utils.getContent(contentContainer, Content.SALARY);
    }

    public void TaskTabClick() throws IOException {
        Utils.getContentTask(contentContainer, buttonUserName.getText());
    }

    public void HomeTabClick() throws IOException {
        Utils.getContent(contentContainer, Content.HOME);
    }

    public void TimeTabClick() throws IOException {
        Utils.getContent(contentContainer, buttonUserName);
    }


    public void TeamTabClick() throws IOException {
        Utils.getContent(contentContainer, Content.TEAM);
    }

    public void logout() {
        Stage stage = (Stage) buttonUserName.getScene().getWindow();
        stage.close();
        Routes routes = new Routes(new Stage());
        routes.goToLogin();
    }

    public void ProfileClick() {
        routes.goToUserProfile(buttonUserName.getText());
    }

    public Button getHomeBtn() {
        return homeBtn;
    }

    public void setHomeBtn(Button homeBtn) {
        this.homeBtn = homeBtn;
    }

    public Button getLeaveBtn() {
        return leaveBtn;
    }

    public void setLeaveBtn(Button leaveBtn) {
        this.leaveBtn = leaveBtn;
    }

    public Button getProjectBtn() {
        return projectBtn;
    }

    public void setProjectBtn(Button projectBtn) {
        this.projectBtn = projectBtn;
    }

    public Button getSalaryBtn() {
        return salaryBtn;
    }

    public void setSalaryBtn(Button salaryBtn) {
        this.salaryBtn = salaryBtn;
    }

    public Button getTaskBtn() {
        return taskBtn;
    }

    public void setTaskBtn(Button taskBtn) {
        this.taskBtn = taskBtn;
    }

    public Button getTeamBtn() {
        return teamBtn;
    }

    public void setTeamBtn(Button teamBtn) {
        this.teamBtn = teamBtn;
    }

    public Button getTimeBtn() {
        return timeBtn;
    }

    public void setTimeBtn(Button timeBtn) {
        this.timeBtn = timeBtn;
    }

    public Button getUserBtn() {
        return userBtn;
    }

    public void setUserBtn(Button userBtn) {
        this.userBtn = userBtn;
    }
}
