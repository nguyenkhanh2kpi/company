package com.example.project.controllers;

import com.example.project.DTO.ProjectDTO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ViewOrUpdateProjectController {

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

    public ProjectDTO selected ;

    public ProjectDTO getSelected() {
        return selected;
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
}
