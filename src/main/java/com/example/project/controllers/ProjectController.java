package com.example.project.controllers;


import com.example.project.BUS.ProjectBUS;
import com.example.project.DAO.TeamDAO;
import com.example.project.DTO.TeamDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.Untilities.CustomValidate;
import com.example.project.core.control.ProjectControl;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectController implements Initializable {
    @FXML
    private ComboBox<TeamDTO> assignToTxt;

    @FXML
    private TextField bunusTxt;

    @FXML
    private DatePicker endDateTxt;

    @FXML
    private Button huyTxt;

    @FXML
    private Button luuTxt;

    @FXML
    private TextArea projectDestxt;

    @FXML
    private TextField projectNametxt;

    @FXML
    private DatePicker startDateTxt;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    ProjectBUS projectBUS = new ProjectBUS();
    TeamDAO teamDAO = new TeamDAO();

    public ProjectControl projectControl;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        luuTxt.setOnAction(actionEvent -> onLuu());
        List<TeamDTO> teamDTOS = teamDAO.getAllTeams();
        assignToTxt.setItems(FXCollections.observableArrayList(teamDTOS));
    }

    public void onLuu() {
        if(validate()) {
            if(projectBUS.addProject(this, username)) {
                CustomToast.toast("Success", ToastStatus.SUCCESS);
                projectControl.loadTable();
                Stage stage = (Stage) projectNametxt.getScene().getWindow();
                stage.close();
            } else  {
                CustomToast.toast("Something went wrong", ToastStatus.FAIL);
            }
        }
    }

    public boolean validate() {
        if (!CustomValidate.validateText(projectNametxt.getText())) {
            CustomAlert.showAlertError("name", "name must not be null");
            return false;
        }
        if (!CustomValidate.validateText(projectDestxt.getText())) {
            CustomAlert.showAlertError("Description", "Description must not be null");
            return false;
        }
        if (assignToTxt.getValue()==null) {
            CustomAlert.showAlertError("Assign", "Assign must not be null");
            return false;
        }
        if (startDateTxt.getValue() == null) {
            CustomAlert.showAlertError("Start date", "Start date must not be null");
            return false;
        }
        if (endDateTxt.getValue() == null) {
            CustomAlert.showAlertError("End date", "End date must not be null");
            return false;
        }
        if (!CustomValidate.validateNumber(bunusTxt.getText(),"")) {
            CustomAlert.showAlertError("Bonus date", "Invalid bonus");
            return false;
        }
        return true;
    }
    public ComboBox<TeamDTO > getAssignToTxt() {
        return assignToTxt;
    }

    public void setAssignToTxt(ComboBox<TeamDTO> assignToTxt) {
        this.assignToTxt = assignToTxt;
    }

    public TextField getBunusTxt() {
        return bunusTxt;
    }

    public void setBunusTxt(TextField bunusTxt) {
        this.bunusTxt = bunusTxt;
    }

    public DatePicker getEndDateTxt() {
        return endDateTxt;
    }

    public void setEndDateTxt(DatePicker endDateTxt) {
        this.endDateTxt = endDateTxt;
    }

    public Button getHuyTxt() {
        return huyTxt;
    }

    public void setHuyTxt(Button huyTxt) {
        this.huyTxt = huyTxt;
    }

    public Button getLuuTxt() {
        return luuTxt;
    }

    public void setLuuTxt(Button luuTxt) {
        this.luuTxt = luuTxt;
    }

    public TextArea getProjectDestxt() {
        return projectDestxt;
    }

    public void setProjectDestxt(TextArea projectDestxt) {
        this.projectDestxt = projectDestxt;
    }

    public TextField getProjectNametxt() {
        return projectNametxt;
    }

    public void setProjectNametxt(TextField projectNametxt) {
        this.projectNametxt = projectNametxt;
    }

    public DatePicker getStartDateTxt() {
        return startDateTxt;
    }

    public void setStartDateTxt(DatePicker startDateTxt) {
        this.startDateTxt = startDateTxt;
    }

    public ProjectControl getProjectControl() {
        return projectControl;
    }

    public void setProjectControl(ProjectControl projectControl) {
        this.projectControl = projectControl;
    }
}
