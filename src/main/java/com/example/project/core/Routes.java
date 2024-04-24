package com.example.project.core;

import com.example.project.controllers.*;
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
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Login!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToHome(Stage prestage, String username) {
        prestage.close();
        newWindow("/com/example/project/home-view.fxml"
                , "Home",
                (HomeController controller) -> {
                    setup.setUpHomeController(controller, username);
                });
    }

    public void goToUserProfile(String username) {
        newWindow("/com/example/project/user-profile-view.fxml"
                , "User Profile",
                (UserProfileController controller) -> {
                    setup.setUpUserProfileController(controller, username);
                });
    }

    public void goToProject() {
        newWindow("/com/example/project/project-view.fxml"
                , "Project", (ProjectController controller) -> {
                    setup.setProjectController();
                });
    }

    public void goToLeaveRequest() {
        newWindow("/com/example/project/Leave-view.fxml",
                "Leave Request", (LeaveRequestController controller) -> {
                    setup.setUpLeaveAddController();
                });
    }

    public void goToTask() {
        newWindow("/com/example/project/task-view.fxml",
                "Task", (AddTaskController controller) -> {
                    setup.setUpTaskController();
                });
    }

    public void viewOrEditTask() {
        newWindow("/com/example/project/view-update-task-view.fxml",
                "View or edit task", (ViewOrUpdateTaskController controller) -> {
                    setup.setUpViewEditTaskController();
                });
    }

    public void viewOrEditLeave() {
        newWindow("/com/example/project/view-update-leave-view.fxml",
                "View or edit leave", (ViewOrUpdateLeaveController controller) -> {
                    setup.setUpViewEditLeaveController();
                });
    }

    public void viewOrEditProject() {
        newWindow("/com/example/project/view-update-project-view.fxml",
                "View or edit leave", (ViewOrUpdateProjectController controller) -> {
                    setup.setUpViewEditProjectController();
                });
    }


    public <T> void newWindow(String resource, String title, ControllerSetUp<T> setup) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
            Stage newStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
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
