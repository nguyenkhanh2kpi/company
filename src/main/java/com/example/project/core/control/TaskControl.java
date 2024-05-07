package com.example.project.core.control;

import com.example.project.BUS.ProjectBUS;
import com.example.project.BUS.TaskBus;
import com.example.project.BUS.TeamBUS;
import com.example.project.BUS.UserBUS;
import com.example.project.DAO.ProjectDAO;
import com.example.project.DAO.TaskDAO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.DTO.TaskDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.Routes;
import com.example.project.core.enums.TaskStatus;
import com.example.project.core.enums.TaskType;
import com.example.project.core.enums.ToastStatus;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TaskControl implements Initializable {
    @FXML
    private TableColumn<TaskDTO, Integer> idCol;

    @FXML
    private TableColumn<TaskDTO, String> idCreatorCol;

    @FXML
    private TableColumn<TaskDTO, String> idAssigneeCol;

    @FXML
    private TableColumn<TaskDTO, String> taskNameCol;

//    @FXML
//    private TableColumn<TaskDTO, String> descriptionCol;

    @FXML
    private TableColumn<TaskDTO, Date> deadlineCol;

    @FXML
    private TableColumn<TaskDTO, Integer> progressCol;

    @FXML
    private TableColumn<TaskDTO, String> idTeamCol;

//    @FXML
//    private TableColumn<TaskDTO, BigDecimal> bonusCol;

    @FXML
    private TableColumn<TaskDTO, String> idProjectCol;

    @FXML
    private TableView<TaskDTO> taskTable;

    @FXML
    private ComboBox<TaskType> meCmb;

    @FXML
    private ComboBox<TaskStatus> processCmb;

    @FXML
    private ComboBox<ProjectDTO> projectCmb;


    Routes routes = new Routes(new Stage());

    TaskBus taskBus = new TaskBus();

    ProjectBUS projectBUS = new ProjectBUS();
    UserBUS userBUS = new UserBUS();

    TeamBUS teamBUS = new TeamBUS();

    TaskDAO taskDAO = new TaskDAO();

    ProjectDAO projectDAO = new ProjectDAO();

    Integer userID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();

        ObservableList<TaskType> meOptions = FXCollections.observableArrayList(TaskType.values());
        meCmb.setItems(meOptions);

//        ObservableList<TaskStatus> processOptions = FXCollections.observableArrayList(TaskStatus.values());
//        processCmb.setItems(processOptions);


        List<ProjectDTO> projectDTOS = projectDAO.getAllProjects();
        ObservableList<ProjectDTO> data = FXCollections.observableArrayList(projectDTOS);
        projectCmb.setItems(data);
        meCmb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TaskType selected = meCmb.getSelectionModel().getSelectedItem();
                loadTable(selected);
            }
        });

        projectCmb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadTable(projectCmb.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void loadTable() {
        List<TaskDTO> tasks = taskBus.getAllTask();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCreatorCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(userBUS.getUserFromId(cellData.getValue().getIdCreator()).getFullName());
        });
        idAssigneeCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(userBUS.getUserFromId(cellData.getValue().getIdAssignee()).getFullName());
        });
        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        progressCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
        idTeamCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(teamBUS.getTeamDTObyId(cellData.getValue().getIdTeam()).getName());
        });
        idProjectCol.setCellValueFactory(cellData -> {
            int projectId = cellData.getValue().getIdProject();
            Optional<ProjectDTO> project = projectBUS.getFromId(projectId);
            if (project.isPresent()) {
                return new SimpleStringProperty(project.get().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });
        taskTable.setItems(FXCollections.observableArrayList(tasks));
    }

    public void loadTable(TaskType taskType) {
        List<TaskDTO> tasks = taskBus.getAllTask();
        if (taskType == TaskType.CREATED) {
            tasks =  tasks.stream().filter(taskDTO -> taskDTO.getIdCreator() == userBUS.getUserFromUsername(username).getId())
                    .collect(Collectors.toList());
        } else if(taskType== TaskType.ASSIGNED){
            tasks =  tasks.stream().filter(taskDTO -> taskDTO.getIdAssignee() == userBUS.getUserFromUsername(username).getId())
                    .collect(Collectors.toList());
        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCreatorCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(userBUS.getUserFromId(cellData.getValue().getIdCreator()).getFullName());
        });
        idAssigneeCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(userBUS.getUserFromId(cellData.getValue().getIdAssignee()).getFullName());
        });
        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        progressCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
        idTeamCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(teamBUS.getTeamDTObyId(cellData.getValue().getIdTeam()).getName());
        });
        idProjectCol.setCellValueFactory(cellData -> {
            int projectId = cellData.getValue().getIdProject();
            Optional<ProjectDTO> project = projectBUS.getFromId(projectId);
            if (project.isPresent()) {
                return new SimpleStringProperty(project.get().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });
        taskTable.setItems(FXCollections.observableArrayList(tasks));
    }

    public void loadTable(ProjectDTO projectDTO) {
        List<TaskDTO> tasks = taskBus.getAllTask();
        tasks =  tasks.stream().filter(taskDTO -> taskDTO.getIdProject() ==projectDTO.getId())
                .collect(Collectors.toList());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCreatorCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(userBUS.getUserFromId(cellData.getValue().getIdCreator()).getFullName());
        });
        idAssigneeCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(userBUS.getUserFromId(cellData.getValue().getIdAssignee()).getFullName());
        });
        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        progressCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
        idTeamCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(teamBUS.getTeamDTObyId(cellData.getValue().getIdTeam()).getName());
        });
        idProjectCol.setCellValueFactory(cellData -> {
            int projectId = cellData.getValue().getIdProject();
            Optional<ProjectDTO> project = projectBUS.getFromId(projectId);
            if (project.isPresent()) {
                return new SimpleStringProperty(project.get().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });
        taskTable.setItems(FXCollections.observableArrayList(tasks));
    }


    public void onDelete() {
        TaskDTO selectedTeam = (TaskDTO) taskTable.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected team?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean success = taskDAO.deleteTask(selectedTeam);
                    if (success) {
                        CustomToast.toast("deleted successfully. ", ToastStatus.SUCCESS);
                        loadTable();
                    } else {
                        CustomToast.toast("Failed to delete task with ID ", ToastStatus.FAIL);
                    }
                }
            });
        } else {
            CustomAlert.showAlertError("Team", "Please select a team to delete.");
        }
    }

    public void onAddTask() {
        routes.goToTask(username,TaskControl.this);
    }

    public void viewEditTask() {
        routes.viewOrEditTask(taskTable.getSelectionModel().getSelectedItem(), TaskControl.this);
    }

    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
