package com.example.project.controllers;

import com.example.project.BUS.TeamBUS;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DAO.TeamDAO;
import com.example.project.DTO.TeamDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.DTO.UserTeamDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.Untilities.CustomValidate;
import com.example.project.core.control.TeamControl;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

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

    @FXML
    private Button newMem;

    @FXML
    private Button newMem1;

    public Button getNewMem1() {
        return newMem1;
    }

    public void setNewMem1(Button newMem1) {
        this.newMem1 = newMem1;
    }

    public Button getNewMem() {
        return newMem;
    }

    public void setNewMem(Button newMem) {
        this.newMem = newMem;
    }

    @FXML
    private ListView<UserDTO> listview;

    public ListView<UserDTO> getListview() {
        return listview;
    }

    public void setListview(ListView<UserDTO> listview) {
        this.listview = listview;
    }

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


//        setUpListview(listview);


        newMem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<UserDTO> userDTOS = employeeDAO.getAll();
                List<UserTeamDTO> userTeamDTOS = teamBUS.getAllUserTeam();
                userDTOS = userDTOS.stream()
                        .filter(userDTO -> userTeamDTOS.stream()
                                .noneMatch(userTeamDTO -> userTeamDTO.getIdUser() == userDTO.getId()
                                        && userTeamDTO.getIdTeam()==teamDTO.getId()))
                        .collect(Collectors.toList());
                Dialog<UserDTO> dialog = new Dialog<>();
                dialog.setTitle("Select New Member");
                ComboBox<UserDTO> comboBox = new ComboBox<>();
                comboBox.setItems(FXCollections.observableArrayList(userDTOS));
                GridPane gridPane = new GridPane();
                gridPane.add(comboBox, 0, 0);
                dialog.getDialogPane().setContent(gridPane);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        return comboBox.getValue();
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(selectedMember -> {
                    if (selectedMember != null) {
                        teamBUS.insertMember(AddTeamController.this, selectedMember.getUsername(), teamDTO);
                        Stage stage = (Stage) nameTxt.getScene().getWindow();
                        stage.close();
                    }
                });
            }
        });
    }

    public void removeMem() {
        List<UserDTO> userDTOS = employeeDAO.getAll();
        List<UserTeamDTO> userTeamDTOS = teamBUS.getAllUserTeam();

        userDTOS = userDTOS.stream()
                .filter(userDTO -> userTeamDTOS.stream()
                        .anyMatch(userTeamDTO -> userTeamDTO.getIdUser() == userDTO.getId()
                                && userTeamDTO.getIdTeam()==teamDTO.getId() ))
                .collect(Collectors.toList());
        Dialog<UserDTO> dialog = new Dialog<>();
        dialog.setTitle("Select New Member");
        ComboBox<UserDTO> comboBox = new ComboBox<>();
        comboBox.setItems(FXCollections.observableArrayList(userDTOS));
        GridPane gridPane = new GridPane();
        gridPane.add(comboBox, 0, 0);
        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return comboBox.getValue();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(selectedMember -> {
            if (selectedMember != null) {
                if(teamBUS.deleteMember(AddTeamController.this, selectedMember.getUsername(), teamDTO)) {
                    Stage stage = (Stage) nameTxt.getScene().getWindow();
                    stage.close();
                    CustomToast.toast("update team success", ToastStatus.SUCCESS);
                } else  {
                    CustomToast.toast("some thing went wrong", ToastStatus.FAIL);
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
        if (updateText.getText().equals("Update")) {
            if (teamDAO.updateTeam(teamBUS.controllerToTeamDTO(this, teamDTO))) {
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

    public void setUpListview(ListView<UserDTO> userDTOListView) {
        List<UserDTO> userDTOS = employeeDAO.getAll();
        List<UserTeamDTO> userTeamDTOS = teamBUS.getAllUserTeam();

        userDTOS = userDTOS.stream()
                .filter(userDTO -> userTeamDTOS.stream()
                        .anyMatch(userTeamDTO -> userTeamDTO.getIdUser() == userDTO.getId()
                                && userTeamDTO.getIdTeam()==teamDTO.getId() ))
                .collect(Collectors.toList());
        ObservableList<UserDTO> observableUserDTOS = FXCollections.observableArrayList(userDTOS);
        userDTOListView.setItems(observableUserDTOS);
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
        if (leaderCmb.getSelectionModel().getSelectedItem() == null) {
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
