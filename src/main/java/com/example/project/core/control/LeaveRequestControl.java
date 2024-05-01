package com.example.project.core.control;

import com.example.project.DAO.LeaveRequestDAO;
import com.example.project.DTO.LeaveRequestDTO;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.Routes;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LeaveRequestControl implements Initializable {
    @FXML
    private TableColumn<LeaveRequestDTO, Integer> idcol;

    @FXML
    private TableColumn<LeaveRequestDTO, Integer> usercol;

    @FXML
    private TableColumn<LeaveRequestDTO, String> contentCol;

    @FXML
    private TableColumn<LeaveRequestDTO, Date> date;

    @FXML
    private TableColumn<LeaveRequestDTO, Date> startDate;

    @FXML
    private TableColumn<LeaveRequestDTO, Date> endDatecol;

    @FXML
    private TableColumn<LeaveRequestDTO, Integer> nodcol;

    @FXML
    private TableColumn<LeaveRequestDTO, String> statuscol;

    @FXML
    private TableView<LeaveRequestDTO> leaveTable;


    public LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAO();
    public String username;
    Routes routes = new Routes(new Stage());

    public void onAddClick() {
        routes.goToLeaveRequest(username);
    }

    public void onViewClick() {
        LeaveRequestDTO selectedItem = leaveTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            routes.viewOrEditLeave(selectedItem, username);
        } else {
            CustomToast.toast("No item is selected.",ToastStatus.INFO);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void LoadData() {
        List<LeaveRequestDTO> requestDTOS = leaveRequestDAO.getAllLeaveRequests();
        ObservableList<LeaveRequestDTO> data = FXCollections.observableArrayList(requestDTOS);
        leaveTable.setItems(data);
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usercol.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
        date.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDatecol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        nodcol.setCellValueFactory(new PropertyValueFactory<>("numberDay"));
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
    }

    public void onReload() {
        LoadData();
    }
}
