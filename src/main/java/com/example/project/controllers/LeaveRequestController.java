package com.example.project.controllers;

import com.example.project.BUS.LeaveBUS;
import com.example.project.DAO.LeaveRequestDAO;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.control.LeaveRequestControl;
import com.example.project.core.enums.ToastStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LeaveRequestController implements Initializable {
    @FXML
    private Button addBtn;

    @FXML
    private DatePicker dateTime;

    @FXML
    private DatePicker fromt;

    @FXML
    private TextArea titletxt;

    @FXML
    private DatePicker tot;

    LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAO();
    LeaveBUS leaveBUS = new LeaveBUS();

    public String username;

    LeaveRequestControl control;

    public LeaveRequestControl getControl() {
        return control;
    }

    public void setControl(LeaveRequestControl control) {
        this.control = control;
    }

    @FXML
    private Text usernametxt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateTime.setValue(LocalDate.now());
    }

    public void onAdd() {
        if (validate()) {
            if (leaveRequestDAO.request(leaveBUS.toDTO(this))) {
                control.LoadData();
                Stage stage = (Stage) usernametxt.getScene().getWindow();
                stage.close();
                CustomToast.toast("Success !!", ToastStatus.SUCCESS);
            } else {
                CustomToast.toast("Some thing went wrong !!", ToastStatus.FAIL);
            }
        }
    }


    public boolean validate() {
        if (titletxt.getText().isEmpty()) {
            CustomToast.toast("Title cannot be empty!", ToastStatus.FAIL);
            return false;
        }
        LocalDate fromDate = fromt.getValue();
        LocalDate toDate = tot.getValue();
        if (fromDate != null && toDate != null && fromDate.isAfter(toDate)) {
            CustomToast.toast("From date cannot be after To date!", ToastStatus.FAIL);
            return false;
        }
        if(fromDate==null || toDate == null) {
            CustomToast.toast("Date cannot be null!", ToastStatus.FAIL);
            return false;
        }
        return true;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(Button addBtn) {
        this.addBtn = addBtn;
    }

    public DatePicker getDateTime() {
        return dateTime;
    }

    public void setDateTime(DatePicker dateTime) {
        this.dateTime = dateTime;
    }

    public DatePicker getFromt() {
        return fromt;
    }

    public void setFromt(DatePicker fromt) {
        this.fromt = fromt;
    }

    public TextArea getTitletxt() {
        return titletxt;
    }

    public void setTitletxt(TextArea titletxt) {
        this.titletxt = titletxt;
    }

    public DatePicker getTot() {
        return tot;
    }

    public void setTot(DatePicker tot) {
        this.tot = tot;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Text getUsernametxt() {
        return usernametxt;
    }

    public void setUsernametxt(Text usernametxt) {
        this.usernametxt = usernametxt;
    }


}
