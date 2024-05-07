package com.example.project.core.control;

import com.example.project.DAO.LeaveRequestDAO;
import com.example.project.DTO.LeaveRequestDTO;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.Routes;
import com.example.project.core.enums.LeaveStatus;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LeaveRequestControl implements Initializable {
    @FXML
    public Text allRequest;
    @FXML
    public Text acceptedRequest;
    @FXML
    public Text rejectedRequest;
    @FXML
    public Text waitingRequest;
    @FXML
    public Text canceledRequest;
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


    @FXML
    ComboBox<LeaveStatus> leaveCmb;

    public LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAO();
    public String username;
    Routes routes = new Routes(new Stage());

    public void onAddClick() {
        routes.goToLeaveRequest(username, LeaveRequestControl.this);
    }

    public void onViewClick() {
        LeaveRequestDTO selectedItem = leaveTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            routes.viewOrEditLeave(selectedItem, username,LeaveRequestControl.this);
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
        LeaveRequestCounterDisplay(requestDTOS);
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

    public void LeaveRequestCounterDisplay(List<LeaveRequestDTO> data){
        allRequest.setText(Integer.toString(data.size()));
        var ref = new Object() {
            int acceptedCounter = 0;
            int rejectedCounter = 0;
            int waitingCounter = 0;
            int canceledCounter = 0;
        };
        data.forEach(LeaveRequestDTO ->{
            String status = LeaveRequestDTO.getStatus();
            if(status.contains("Accepted")){
                ref.acceptedCounter++;
            } else if (status.contains("Rejected")) {
                ref.rejectedCounter++;
            } else if (status.contains("Waiting")) {
                ref.waitingCounter++;
            } else if (status.contains("Canceled")) {
                ref.canceledCounter++;
            }
        });
        acceptedRequest.setText(Integer.toString(ref.acceptedCounter));
        rejectedRequest.setText(Integer.toString(ref.rejectedCounter));
        waitingRequest.setText(Integer.toString(ref.waitingCounter));
        canceledRequest.setText(Integer.toString(ref.canceledCounter));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
        leaveCmb.setItems(FXCollections.observableArrayList(LeaveStatus.CREATED, LeaveStatus.CANCEL, LeaveStatus.APPROVE, LeaveStatus.REFUSE));
        leaveCmb.setOnAction(actionEvent -> {
            String selectedStatus = leaveCmb.getSelectionModel().getSelectedItem().toString();
            List<LeaveRequestDTO> requestDTOS = leaveRequestDAO.getAllLeaveRequests().stream()
                    .filter(leaveRequestDTO -> leaveRequestDTO.getStatus().toString().equals(selectedStatus))
                    .collect(Collectors.toList());
            LeaveRequestCounterDisplay(requestDTOS);
            leaveTable.setItems(FXCollections.observableArrayList(requestDTOS));
        });

    }

    public void onReload() {
        LoadData();
    }
}
