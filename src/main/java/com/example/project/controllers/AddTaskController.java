package com.example.project.controllers;

import com.example.project.BUS.TaskBus;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DAO.ProjectDAO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.Untilities.CustomValidate;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {
    @FXML
    private ComboBox<UserDTO> assignedCmb;

    @FXML
    private TextField bonusPic;

    @FXML
    private TextField creatorNametxt;

    @FXML
    private TextArea desTxt;

    @FXML
    private DatePicker dueDatePic;

    @FXML
    private TextField nameTxt;

    @FXML
    private ComboBox<ProjectDTO> projectCmb;

    public String username;
    @FXML
    private Button save;

    TaskBus taskBus = new TaskBus();
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save.setOnAction(actionEvent -> Save());
        Load();
    }

    public void Load() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<UserDTO> users = employeeDAO.getAll();
        assignedCmb.setItems(FXCollections.observableArrayList(users));

        ProjectDAO projectDAO = new ProjectDAO();
        List<ProjectDTO> projectDTOS = projectDAO.getAllProjects();
        projectCmb.setItems(FXCollections.observableArrayList(projectDTOS));
    }

    public void Save() {
        if (validate()) {
            if(taskBus.InsertTask(this,username)) {
                CustomToast.toast("Success", ToastStatus.SUCCESS);
            } else {
                CustomToast.toast("Fail", ToastStatus.FAIL);
            }
        } else {
            CustomToast.toast("Fail", ToastStatus.FAIL);
        }
    }

    public boolean validate() {
        if (!CustomValidate.validateText(nameTxt.getText())) {
            CustomAlert.showAlertError("name", "name must not be null");
            return false;
        }
        if (!CustomValidate.validateText(desTxt.getText())) {
            CustomAlert.showAlertError("description", "description must not be null");
            return false;
        }
        if(assignedCmb.getValue()==null) {
            CustomAlert.showAlertError("", "Please select a assignee");
            return false;
        }
        if(projectCmb.getValue()==null) {
            CustomAlert.showAlertError("", "Please select a project");
            return false;
        }
        if(dueDatePic.getValue()==null) {
            CustomAlert.showAlertError("", "Please select a deadline");
            return false;
        }
        if (!CustomValidate.validateNumber(bonusPic.getText(),"")) {
            CustomAlert.showAlertError("bonus", "bonus must be valid");
            return false;
        }
        return true;
    }

    public ComboBox<UserDTO> getAssignedCmb() {
        return assignedCmb;
    }

    public void setAssignedCmb(ComboBox<UserDTO> assignedCmb) {
        this.assignedCmb = assignedCmb;
    }

    public TextField getBonusPic() {
        return bonusPic;
    }

    public void setBonusPic(TextField bonusPic) {
        this.bonusPic = bonusPic;
    }

    public TextField getCreatorNametxt() {
        return creatorNametxt;
    }

    public void setCreatorNametxt(TextField creatorNametxt) {
        this.creatorNametxt = creatorNametxt;
    }

    public TextArea getDesTxt() {
        return desTxt;
    }

    public void setDesTxt(TextArea desTxt) {
        this.desTxt = desTxt;
    }

    public DatePicker getDueDatePic() {
        return dueDatePic;
    }

    public void setDueDatePic(DatePicker dueDatePic) {
        this.dueDatePic = dueDatePic;
    }

    public TextField getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(TextField nameTxt) {
        this.nameTxt = nameTxt;
    }

    public ComboBox<ProjectDTO> getProjectCmb() {
        return projectCmb;
    }

    public void setProjectCmb(ComboBox<ProjectDTO> projectCmb) {
        this.projectCmb = projectCmb;
    }


}
