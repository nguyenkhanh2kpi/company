package com.example.project.core.control;

import com.example.project.BUS.CheckInBus;
import com.example.project.DAO.CheckinCheckoutDAO;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DTO.CheckinCheckoutDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.CustomToast;
import com.example.project.controllers.HomeController;
import com.example.project.core.ExcelExporter;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TimeControl implements Initializable {

    @FXML
    TableView timeTable;
    @FXML
    TableColumn nameCol;
    @FXML
    TableColumn checkInCol;
    @FXML
    TableColumn checkOutCol;
    @FXML
    TableColumn dateCol;
    @FXML
    TableColumn hourCol;

    @FXML
    private DatePicker datePic;
    @FXML
    private TextField checkInTxt;
    @FXML
    private TextField checkOutTxt;

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField totalTxt;

    @FXML
    TableColumn idCOl;
    EmployeeDAO employeeDAO = new EmployeeDAO();
    private static CheckinCheckoutDAO checkinCheckoutDAO = new CheckinCheckoutDAO();

    Button  userNameButton;

    public Button getUserNameButton() {
        return userNameButton;
    }

    public void setUserNameButton(Button userNameButton) {
        this.userNameButton = userNameButton;
    }

    public void LoadData() {
        List<CheckinCheckoutDTO> checkinCheckoutDTOS = checkinCheckoutDAO.getAllCheckins();
        ObservableList<CheckinCheckoutDTO> data = FXCollections.observableArrayList(checkinCheckoutDTOS);
        idCOl.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        checkInCol.setCellValueFactory(new PropertyValueFactory<>("checkinTime"));
        checkOutCol.setCellValueFactory(new PropertyValueFactory<>("checkoutTime"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        hourCol.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
        timeTable.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
        timeTable.setOnMouseClicked(d -> handleTableRowClick());
    }

    public void handleTableRowClick() {
        CheckinCheckoutDTO selectedItem = (CheckinCheckoutDTO) timeTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            LocalTime checkinTime = selectedItem.getCheckinTime().toLocalTime();
            LocalTime checkoutTime = selectedItem.getCheckoutTime() != null ? selectedItem.getCheckoutTime().toLocalTime() : null;
            String checkinTimeString = checkinTime != null ? checkinTime.toString() : "";
            String checkoutTimeString = checkoutTime != null ? checkoutTime.toString() : "";
            LocalDateTime date = selectedItem.getDate();
            if (date != null) {
                LocalDate localDate = date.toLocalDate();
                datePic.setValue(localDate);
            }

            List<UserDTO> userName = employeeDAO.searchUser(Integer.toString(selectedItem.getIdUser()), 0);
            nameTxt.setText(userName.get(0).getFullName());
            checkInTxt.setText(checkinTimeString);
            checkOutTxt.setText(checkoutTimeString);
            totalTxt.setText(Float.toString(selectedItem.getTotalHours()));
        }
    }

    public void checkInClick() {
        List<UserDTO> users = employeeDAO.searchUser(userNameButton.getText().toString(),1);
        var user = users.get(0);
        LocalDate today = LocalDate.now();
        List<CheckinCheckoutDTO> list = checkinCheckoutDAO.getAllCheckins();
        List<CheckinCheckoutDTO> filteredList = list.stream()
                .filter(ck -> ck.getIdUser() == user.getId() && ck.getDate().toLocalDate().isEqual(today))
                .collect(Collectors.toList());

        if (!filteredList.isEmpty()) {
            CustomToast.toast("User have checked in.", ToastStatus.INFO);
        } else {
            if(checkinCheckoutDAO.checkin(CheckInBus.checkinDTO(user.getId()))) {
                LoadData();
                CustomToast.toast("User have checked in.", ToastStatus.SUCCESS);
            } else {
                LoadData();
                CustomToast.toast("Some thing went wrong.", ToastStatus.FAIL);
            }
        }
    }

    public void checkOutClick() {
        List<UserDTO> users = employeeDAO.searchUser(userNameButton.getText().toString(),1);
        var user = users.get(0);
        LocalDate today = LocalDate.now();
        List<CheckinCheckoutDTO> list = checkinCheckoutDAO.getAllCheckins();
        List<CheckinCheckoutDTO> filteredList = list.stream()
                .filter(ck -> ck.getIdUser() == user.getId() && ck.getDate().toLocalDate().isEqual(today))
                .collect(Collectors.toList());
        if(checkinCheckoutDAO.checkout(CheckInBus.checkOutDTO(filteredList.get(0)))) {
            LoadData();
            CustomToast.toast("User have checked out.", ToastStatus.SUCCESS);
        } else {
            LoadData();
            CustomToast.toast("Some thing went wrong.", ToastStatus.FAIL);
        }
    }

    public void onDownCico()  {
        try {
            ExcelExporter.exportToExcel(timeTable, new Stage());
            CustomToast.toast("Success export file", ToastStatus.SUCCESS);
        } catch(Exception e) {
            CustomToast.toast("Something went wrong", ToastStatus.FAIL);
        }

    }
}
