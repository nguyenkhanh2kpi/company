package com.example.project.controllers;

import com.example.project.BUS.TaskBus;
import com.example.project.DAO.ProjectDAO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.DTO.TaskDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.Untilities.CustomValidate;
import com.example.project.core.control.TaskControl;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOrUpdateTaskController implements Initializable {
    @FXML
    private ComboBox<UserDTO> assignesCmb;

    @FXML
    private TextField bonustxt;

    @FXML
    private TextArea destxt;

    @FXML
    private DatePicker dueDatetxt;

    @FXML
    private TextField persontxt;

    @FXML
    private TextField processtxt;

    @FXML
    private ComboBox<ProjectDTO> projectCmb;

    @FXML
    private TextField team;

    @FXML
    private VBox teamtxt;

    @FXML
    private TextField titletxt;
    TaskBus taskBus = new TaskBus();

    TaskDTO taskDTO = new TaskDTO();

    TaskControl taskControl = new TaskControl();

    public TaskControl getTaskControl() {
        return taskControl;
    }

    public void setTaskControl(TaskControl taskControl) {
        this.taskControl = taskControl;
    }

    public TaskDTO getTaskDTO() {
        return taskDTO;
    }

    public void setTaskDTO(TaskDTO taskDTO) {
        this.taskDTO = taskDTO;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectDAO projectDAO = new ProjectDAO();
        List<ProjectDTO> projectDTOS = projectDAO.getAllProjects();
        projectCmb.setItems(FXCollections.observableArrayList(projectDTOS));

        projectCmb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ProjectDTO projectDTO = projectCmb.getSelectionModel().getSelectedItem();
                List<UserDTO> users = taskBus.getMembersByProject(projectDTO);
                assignesCmb.setItems(FXCollections.observableArrayList(users));
            }
        });
    }

    public void onUpdate() {
        if (validate()) {
            if(taskBus.UpdateTask(this,taskDTO)) {
                Stage stage = (Stage) titletxt.getScene().getWindow();
                taskControl.loadTable();
                stage.close();
                CustomToast.toast("Success", ToastStatus.SUCCESS);
            } else {
                CustomToast.toast("Fail", ToastStatus.FAIL);
            }
        } else {
            CustomToast.toast("Fail", ToastStatus.FAIL);
        }
    }

    public void onExit() {}
    public boolean validate() {
        if (!CustomValidate.validateText(titletxt.getText())) {
            CustomAlert.showAlertError("name", "name must not be null");
            return false;
        }
        if (!CustomValidate.validateText(destxt.getText())) {
            CustomAlert.showAlertError("description", "description must not be null");
            return false;
        }
        if(assignesCmb.getValue()==null) {
            CustomAlert.showAlertError("", "Please select a assignee");
            return false;
        }
        if(projectCmb.getValue()==null) {
            CustomAlert.showAlertError("", "Please select a project");
            return false;
        }
        if(dueDatetxt.getValue()==null) {
            CustomAlert.showAlertError("", "Please select a deadline");
            return false;
        }
        if (!CustomValidate.validateNumber(bonustxt.getText(),"")) {
            CustomAlert.showAlertError("bonus", "bonus must be valid");
            return false;
        }
        if (!CustomValidate.validateNumber(processtxt.getText(),"")) {
            CustomAlert.showAlertError("bonus", "process must be valid");
            return false;
        }
        return true;
    }

    public ComboBox<UserDTO> getAssignesCmb() {
        return assignesCmb;
    }

    public void setAssignesCmb(ComboBox<UserDTO> assignesCmb) {
        this.assignesCmb = assignesCmb;
    }

    public TextField getBonustxt() {
        return bonustxt;
    }

    public void setBonustxt(TextField bonustxt) {
        this.bonustxt = bonustxt;
    }

    public TextArea getDestxt() {
        return destxt;
    }

    public void setDestxt(TextArea destxt) {
        this.destxt = destxt;
    }

    public DatePicker getDueDatetxt() {
        return dueDatetxt;
    }

    public void setDueDatetxt(DatePicker dueDatetxt) {
        this.dueDatetxt = dueDatetxt;
    }

    public TextField getPersontxt() {
        return persontxt;
    }

    public void setPersontxt(TextField persontxt) {
        this.persontxt = persontxt;
    }

    public TextField getProcesstxt() {
        return processtxt;
    }

    public void setProcesstxt(TextField processtxt) {
        this.processtxt = processtxt;
    }

    public ComboBox<ProjectDTO> getProjectCmb() {
        return projectCmb;
    }

    public void setProjectCmb(ComboBox<ProjectDTO> projectCmb) {
        this.projectCmb = projectCmb;
    }

    public TextField getTeam() {
        return team;
    }

    public void setTeam(TextField team) {
        this.team = team;
    }

    public VBox getTeamtxt() {
        return teamtxt;
    }

    public void setTeamtxt(VBox teamtxt) {
        this.teamtxt = teamtxt;
    }

    public TextField getTitletxt() {
        return titletxt;
    }

    public void setTitletxt(TextField titletxt) {
        this.titletxt = titletxt;
    }

}
