package com.example.project.controllers;

import com.example.project.BUS.LeaveBUS;
import com.example.project.DTO.LeaveRequestDTO;
import com.example.project.core.enums.LeaveStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewOrUpdateLeaveController implements Initializable {
    @FXML
    private TextField approveUsertxt;

    @FXML
    private TextArea contentTxt;

    @FXML
    private DatePicker datePic;

    @FXML
    private Text dayTxt;

    @FXML
    private DatePicker fromPic;

    @FXML
    private ComboBox<LeaveStatus> statusCmb;

    @FXML
    private DatePicker toPic;

    @FXML
    private TextField userTxt;

    public LeaveRequestDTO leaveRequestDTO;

    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<LeaveStatus> statusList = FXCollections.observableArrayList(LeaveStatus.values());
        statusCmb.setItems(statusList);
    }

    public void cancelClick() {
        LeaveBUS leaveBUS = new LeaveBUS();
        leaveBUS.cancel(leaveRequestDTO, username);
    }

    public void applyClick() {
        LeaveBUS leaveBUS = new LeaveBUS();
        LeaveStatus selectedStatus = statusCmb.getValue();
        leaveBUS.update(leaveRequestDTO, username, selectedStatus);
    }


    public TextField getApproveUsertxt() {
        return approveUsertxt;
    }

    public void setApproveUsertxt(TextField approveUsertxt) {
        this.approveUsertxt = approveUsertxt;
    }

    public TextArea getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(TextArea contentTxt) {
        this.contentTxt = contentTxt;
    }

    public DatePicker getDatePic() {
        return datePic;
    }

    public void setDatePic(DatePicker datePic) {
        this.datePic = datePic;
    }

    public Text getDayTxt() {
        return dayTxt;
    }

    public void setDayTxt(Text dayTxt) {
        this.dayTxt = dayTxt;
    }

    public DatePicker getFromPic() {
        return fromPic;
    }

    public void setFromPic(DatePicker fromPic) {
        this.fromPic = fromPic;
    }

    public ComboBox<LeaveStatus> getStatusCmb() {
        return statusCmb;
    }

    public void setStatusCmb(ComboBox<LeaveStatus> statusCmb) {
        this.statusCmb = statusCmb;
    }

    public DatePicker getToPic() {
        return toPic;
    }

    public void setToPic(DatePicker toPic) {
        this.toPic = toPic;
    }

    public TextField getUserTxt() {
        return userTxt;
    }

    public void setUserTxt(TextField userTxt) {
        this.userTxt = userTxt;
    }
    public LeaveRequestDTO getLeaveRequestDTO() {
        return leaveRequestDTO;
    }

    public void setLeaveRequestDTO(LeaveRequestDTO leaveRequestDTO) {
        this.leaveRequestDTO = leaveRequestDTO;
    }

}
