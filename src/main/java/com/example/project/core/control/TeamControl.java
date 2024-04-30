package com.example.project.core.control;

import com.example.project.DAO.TeamDAO;
import com.example.project.DTO.TeamDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.Routes;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeamControl implements Initializable {

    @FXML
    Button addTeamBtn;

    @FXML
    Button editTeamBtn;

    @FXML
    Button deleteTeamBtn;

    @FXML
    TableView teamTable;
    @FXML
    TableColumn idColumn;
    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn desColumn;
    @FXML
    TableColumn leaderColumn;


    Routes routes = new Routes(new Stage());
    TeamDAO teamDAO = new TeamDAO();

    public void LoadTable() {
        List<TeamDTO> teamDTOS = teamDAO.getAllTeams();
        teamTable.getItems().clear();
        ObservableList<TeamDTO> data = FXCollections.observableArrayList(teamDTOS);
        teamTable.setItems(data);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        leaderColumn.setCellValueFactory(new PropertyValueFactory<>("idLeader"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadTable();
    }

    public void onAddTeamBtn() {
        routes.addOrUpdateTeam(false, this, null);
    }
    public void onEditTeamBtn() {
        TeamDTO selectedTeam = (TeamDTO) teamTable.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            routes.addOrUpdateTeam(true,this,selectedTeam);
        } else {
            CustomAlert.showAlertError("Team","Please select a team to edit.");
        }
    }

    public void onDeleteTeam() {
        TeamDTO selectedTeam = (TeamDTO) teamTable.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected team?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean success = teamDAO.deleteTeam(selectedTeam);
                    if (success) {
                        CustomToast.toast("deleted successfully. " , ToastStatus.SUCCESS);
                        LoadTable();
                    } else {
                        CustomToast.toast("Failed to delete team with ID " , ToastStatus.FAIL);
                    }
                }
            });
        } else {
            CustomAlert.showAlertError("Team", "Please select a team to delete.");
        }
    }






}
