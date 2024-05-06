package com.example.project.controllers;

import com.example.project.BUS.ProjectBUS;
import com.example.project.BUS.TeamBUS;
import com.example.project.DAO.TeamDAO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.DTO.TeamDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.Untilities.CustomValidate;
import com.example.project.core.control.ProjectControl;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOrUpdateProjectController implements Initializable {

    @FXML
    private TextField BonusTxt;

    @FXML
    private TextArea desTxt;

    @FXML
    private DatePicker endDatePic;

    @FXML
    private TextField nameTxt;

    @FXML
    private DatePicker startDatePic;

    @FXML
    private ComboBox<TeamDTO> teamCmb;

    public ProjectControl projectControl;


    public ProjectDTO selected ;

    public ProjectDTO getSelected() {
        return selected;
    }
    TeamDAO teamDAO = new TeamDAO();
    TeamBUS teamBUS = new TeamBUS();

    ProjectBUS projectBUS = new ProjectBUS();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<TeamDTO> teamDTOS = teamDAO.getAllTeams();
        teamCmb.setItems(FXCollections.observableArrayList(teamDTOS));


    }


    public void onLuu() {
        if(validate()) {
            if(projectBUS.updateProject(this,selected)) {
                projectControl.loadTable();
                Stage stage = (Stage) nameTxt.getScene().getWindow();
                stage.close();
                CustomToast.toast("Success", ToastStatus.SUCCESS);
            } else  {
                CustomToast.toast("Something went wrong", ToastStatus.FAIL);
            }
        }
    }
    





    public boolean validate() {
        if (!CustomValidate.validateText(nameTxt.getText())) {
            CustomAlert.showAlertError("name", "name must not be null");
            return false;
        }
        if (!CustomValidate.validateText(desTxt.getText())) {
            CustomAlert.showAlertError("Description", "Description must not be null");
            return false;
        }
        if (teamCmb.getValue()==null) {
            CustomAlert.showAlertError("Assign", "Assign must not be null");
            return false;
        }
        if (startDatePic.getValue() == null) {
            CustomAlert.showAlertError("Start date", "Start date must not be null");
            return false;
        }
        if (endDatePic.getValue() == null) {
            CustomAlert.showAlertError("End date", "End date must not be null");
            return false;
        }
        if (!CustomValidate.validateNumber(BonusTxt.getText(),"")) {
            CustomAlert.showAlertError("Bonus date", "Invalid bonus");
            return false;
        }
        return true;
    }



    public void setSelected(ProjectDTO selected) {
        this.selected = selected;
    }
    public TextField getBonusTxt() {
        return BonusTxt;
    }

    public void setBonusTxt(TextField bonusTxt) {
        BonusTxt = bonusTxt;
    }

    public TextArea getDesTxt() {
        return desTxt;
    }

    public void setDesTxt(TextArea desTxt) {
        this.desTxt = desTxt;
    }

    public DatePicker getEndDatePic() {
        return endDatePic;
    }

    public void setEndDatePic(DatePicker endDatePic) {
        this.endDatePic = endDatePic;
    }

    public TextField getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(TextField nameTxt) {
        this.nameTxt = nameTxt;
    }

    public DatePicker getStartDatePic() {
        return startDatePic;
    }

    public void setStartDatePic(DatePicker startDatePic) {
        this.startDatePic = startDatePic;
    }

    public ComboBox<TeamDTO> getTeamCmb() {
        return teamCmb;
    }

    public void setTeamCmb(ComboBox<TeamDTO> teamCmb) {
        this.teamCmb = teamCmb;
    }

    public ProjectControl getProjectControl() {
        return projectControl;
    }

    public void setProjectControl(ProjectControl projectControl) {
        this.projectControl = projectControl;
    }

}
