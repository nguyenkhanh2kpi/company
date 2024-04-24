package com.example.project.core.control;

import com.example.project.core.Routes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectControl implements Initializable {
    Routes routes = new Routes(new Stage());

    @FXML
    Button viewProjectBtn;

    @FXML
    Button editProjectBtn;


    //cach1
    public void onAddProjectClick() {
        routes.goToProject();
    }



    //cach2
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewProjectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                routes.viewOrEditProject();
            }
        });

        editProjectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                routes.viewOrEditProject();
            }
        });
    }
}
