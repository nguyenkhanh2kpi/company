package com.example.project.controllers;

import com.example.project.BUS.TeamBUS;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DAO.TeamDAO;
import com.example.project.DTO.TeamDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.Untilities.CustomValidate;
import com.example.project.core.control.TeamControl;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddTeamController implements Initializable {

    @FXML
    TextField nameTxt;

    @FXML
    TextArea desTxt;

    @FXML
    ComboBox<UserDTO> leaderCmb;

    @FXML
    Text updateText;
    private String isUpdate;
    public Text getUpdateText() {
        return updateText;
    }

    public void setUpdateText(Text updateText) {
        this.updateText = updateText;
    }

    private TeamControl teamControl;

    private TeamDTO teamDTO;




    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static TeamDAO teamDAO = new TeamDAO();
    private static TeamBUS teamBUS = new TeamBUS();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();
    }
    public void load() {
        List<UserDTO> users = employeeDAO.getAll();
        List<UserDTO> leaders = users.stream()
                .filter(userDTO -> userDTO.getIdPosition() == 1)
                .collect(Collectors.toList());

        leaderCmb.setItems(FXCollections.observableArrayList(leaders));
        leaderCmb.setCellFactory(new Callback<ListView<UserDTO>, ListCell<UserDTO>>() {
            @Override
            public ListCell<UserDTO> call(ListView<UserDTO> param) {
                return new ListCell<UserDTO>() {
                    @Override
                    protected void updateItem(UserDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getFullName());
                        }
                    }
                };
            }
        });
        leaderCmb.setButtonCell(new ListCell<UserDTO>() {
            @Override
            protected void updateItem(UserDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getFullName());
                }
            }
        });

    }


    public void onHuy() {
        teamControl.LoadTable();
        Stage stage = (Stage) nameTxt.getScene().getWindow();
        stage.close();
    }

    public void ClearTxt() {
        getNameTxt().clear();
        getDesTxt().clear();
    }
    public void onLuu() {
        if(updateText.getText().equals("update")) {
            if(teamDAO.updateTeam(teamBUS.controllerToTeamDTO(this,teamDTO)))  {
                ClearTxt();
                teamControl.LoadTable();
                CustomToast.toast("update team success", ToastStatus.SUCCESS);
            } else {
                CustomToast.toast("some thing went wrong", ToastStatus.FAIL);
            }
        } else {
            if (validate()) {
                if (teamDAO.insertTeam(teamBUS.controllerToTeamDTO(this))) {
                    ClearTxt();
                    teamControl.LoadTable();
                    CustomToast.toast("add team success", ToastStatus.SUCCESS);
                } else {
                    CustomToast.toast("some thing went wrong", ToastStatus.FAIL);
                }

            }
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
        if(leaderCmb.getSelectionModel().getSelectedItem()==null) {
            CustomAlert.showAlertError("leader", "you haven't choice leader");
        }
        return true;
    }


    public TextField getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(TextField nameTxt) {
        this.nameTxt = nameTxt;
    }

    public TextArea getDesTxt() {
        return desTxt;
    }

    public void setDesTxt(TextArea desTxt) {
        this.desTxt = desTxt;
    }

    public ComboBox<UserDTO> getLeaderCmb() {
        return leaderCmb;
    }

    public void setLeaderCmb(ComboBox<UserDTO> leaderCmb) {
        this.leaderCmb = leaderCmb;
    }

    public TeamControl getTeamControl() {
        return teamControl;
    }

    public void setTeamControl(TeamControl teamControl) {
        this.teamControl = teamControl;
    }

    public TeamDTO getTeamDTO() {
        return teamDTO;
    }

    public void setTeamDTO(TeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

}
