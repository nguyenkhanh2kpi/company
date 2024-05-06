package com.example.project.core.control;

import com.example.project.BUS.ProjectBUS;
import com.example.project.DAO.ProjectDAO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.Routes;
import com.example.project.core.enums.ProjectStatus;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProjectControl implements Initializable {
    Routes routes = new Routes(new Stage());

    @FXML
    Button viewProjectBtn;

    @FXML
    Button editProjectBtn;

    @FXML
    private TableColumn<?, ?> descol;
    @FXML
    private TableColumn<?, ?> endcol;

    @FXML
    private TableColumn<?, ?> idcol;

    @FXML
    private TableColumn<?, ?> namecol;

    @FXML
    private TableColumn<?, ?> progresscol;

    @FXML
    private TableColumn<?, ?> startcol;

    @FXML
    private TableView<ProjectDTO> table;


    @FXML
    private ComboBox<String> projectFilter;

    public String username;

    public ProjectBUS projectBUS = new ProjectBUS();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //cach1
    public void onAddProjectClick() {
        routes.goToProject(username, this);

    }

    public void loadTable() {
        ProjectDAO projectDAO = new ProjectDAO();
        List<ProjectDTO> projectDTOS = projectDAO.getAllProjects();
        table.getItems().clear();
        ObservableList<ProjectDTO> data = FXCollections.observableArrayList(projectDTOS);
        table.setItems(data);
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        startcol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        descol.setCellValueFactory(new PropertyValueFactory<>("description"));
        progresscol.setCellValueFactory(new PropertyValueFactory<>("progress"));
    }

    public void loadTable(ProjectStatus projectStatus) {
        ProjectDAO projectDAO = new ProjectDAO();
        List<ProjectDTO> projectDTOS = projectDAO.getAllProjects();
        switch (projectStatus) {
            case DONE:
                projectDTOS = projectDTOS.stream()
                        .filter(projectDTO -> Date.valueOf(projectDTO.getEndDate().toLocalDate()).before(new Date(System.currentTimeMillis())))
                        .collect(Collectors.toList());
                break;
            case TODO:
                projectDTOS = projectDTOS.stream()
                        .filter(projectDTO -> Date.valueOf(projectDTO.getStartDate().toLocalDate()).after(new Date(System.currentTimeMillis())))
                        .collect(Collectors.toList());
                break;
            case PROCESS:
                projectDTOS = projectDTOS.stream()
                        .filter(projectDTO -> Date.valueOf(projectDTO.getStartDate().toLocalDate()).before(new Date(System.currentTimeMillis())) &&
                                        Date.valueOf(projectDTO.getEndDate().toLocalDate()).after(new Date(System.currentTimeMillis()))
                                )
                        .collect(Collectors.toList());
                break;
            case ALL:
                break;
        }

        table.getItems().clear();
        ObservableList<ProjectDTO> data = FXCollections.observableArrayList(projectDTOS);
        table.setItems(data);
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        startcol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        descol.setCellValueFactory(new PropertyValueFactory<>("description"));
        progresscol.setCellValueFactory(new PropertyValueFactory<>("progress"));
    }

    //cach2
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> projectStatusList = Arrays.stream(ProjectStatus.values())
                .map(ProjectStatus::name)
                .collect(Collectors.toList());

        ObservableList<String> statusItems = FXCollections.observableArrayList(projectStatusList);
        projectFilter.setItems(statusItems);
        projectFilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadTable(ProjectStatus.valueOf(projectFilter.getSelectionModel().getSelectedItem().toString()));
            }
        });

        loadTable();
        viewProjectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                routes.viewOrEditProject();
            }
        });

        editProjectBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ProjectDTO projectDTO = table.getSelectionModel().getSelectedItem();
                    routes.viewOrEditProject(projectDTO, ProjectControl.this);
                } catch (Exception e) {
                    CustomToast.toast("Select a row", ToastStatus.FAIL);
                }

            }
        });


    }

    public void deleteProject() {
        try {
            if (projectBUS.deleteProject(table.getSelectionModel().getSelectedItem())) {
                loadTable();
                CustomToast.toast("Success", ToastStatus.SUCCESS);
            } else {
                CustomToast.toast("Some thing went wrong", ToastStatus.FAIL);
            }
        } catch (Exception e) {
            CustomToast.toast("Please select project", ToastStatus.FAIL);
        }
    }

}
