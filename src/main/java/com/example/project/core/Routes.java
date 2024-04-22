package com.example.project.core;

import com.example.project.controllers.HomeController;
import com.example.project.controllers.UserProfileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Routes {
    private final Stage stage;
    private final Setup setup = new Setup();

    public Routes(Stage stage) {
        this.stage = stage;
    }

    public void goToLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 854, 503);
            stage.setTitle("Login!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToHome(Stage prestage, String username) {
        prestage.close();
        newWindow("/com/example/project/home-view.fxml", 1110, 710, "Home",
                (HomeController controller) -> {
                    setup.setUpHomeController(controller, username);
                });
    }

    public void goToUserProfile(String username) {
        newWindow("/com/example/project/user-profile-view.fxml", 638, 540, "User Profile",
                (UserProfileController controller) -> {
                    setup.setUpUserProfileController(controller,username);
                });
    }

    public void goToProject() {
        newWindow("/com/example/project/project-view.fxml", 638, 440, "Project", null);
    }

    public void goToLeaveRequest() {
        newWindow("/com/example/project/Leave-view.fxml", 378, 544, "Leave Request", null);
    }

    public void goToTask() {
        newWindow("/com/example/project/task-view.fxml", 918, 540, "Task", null);
    }


    public <T> void newWindow(String resource, Integer w, Integer h, String title, ControllerSetUp<T> setup) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
            Stage newStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), w, h);
            newStage.setTitle(title);
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            T controller = fxmlLoader.getController();
            setup.setup(controller);
            newStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
