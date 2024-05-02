package com.example.project.core.control;

import com.example.project.DAO.ProjectDAO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.DTO.TeamDTO;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.Routes;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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




    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //cach1
    public void onAddProjectClick() {
        routes.goToProject(username);
        System.out.println("asd"+ username);
    }

    //cach2
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectDAO projectDAO = new ProjectDAO();
        List<ProjectDTO> projectDTOS = projectDAO.getAllProjects();
        table.getItems().clear();
        ObservableList<ProjectDTO> data = FXCollections.observableArrayList(projectDTOS);
        table.setItems(data);
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        startcol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("description"));


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
                    routes.viewOrEditProject(projectDTO);
                } catch (Exception e) {
                    CustomToast.toast("Select a row", ToastStatus.FAIL);
                }

            }
        });
    }
}
