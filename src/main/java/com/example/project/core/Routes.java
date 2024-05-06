package com.example.project.core;

import com.example.project.DTO.LeaveRequestDTO;
import com.example.project.DTO.ProjectDTO;
import com.example.project.DTO.TaskDTO;
import com.example.project.DTO.TeamDTO;
import com.example.project.controllers.*;
import com.example.project.core.control.ProjectControl;
import com.example.project.core.control.TeamControl;
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

    public void goToProject(String username, ProjectControl projectControl) {
        newWindow("/com/example/project/project-view.fxml"
                , "Project", (ProjectController controller) -> {
                    setup.setProjectController(controller, username, projectControl);
                });
    }

    public void goToLeaveRequest(String username) {
        newWindow("/com/example/project/Leave-view.fxml",
                "Leave Request", (LeaveRequestController controller) -> {
                    setup.setUpLeaveAddController(controller, username);
                });
    }

    public void goToTask(String username) {
        newWindow("/com/example/project/task-view.fxml",
                "Task", (AddTaskController controller) -> {
                    setup.setUpTaskController(controller, username);
                });
    }

    public void viewOrEditTask(TaskDTO selectedItem) {
        newWindow("/com/example/project/view-update-task-view.fxml",
                "View or edit task", (ViewOrUpdateTaskController controller) -> {
                    setup.setUpViewEditTaskController(controller, selectedItem);
                });
    }

    public void viewOrEditLeave(LeaveRequestDTO leaveRequestDTO,String username) {
        newWindow("/com/example/project/view-update-leave-view.fxml",
                "View or edit leave", (ViewOrUpdateLeaveController controller) -> {
                    setup.setUpViewEditLeaveController(controller,leaveRequestDTO,username);
                });
    }

    public void viewOrEditProject(ProjectDTO projectDTO, ProjectControl projectControl) {
        newWindow("/com/example/project/view-update-project-view.fxml",
                "View or edit leave", (ViewOrUpdateProjectController controller) -> {
                    setup.setUpViewEditProjectController(controller,projectDTO,projectControl );
                });
    }

    public void addOrUpdateTeam(boolean isUpdate, TeamControl control, TeamDTO teamDTO) {
        newWindow("/com/example/project/add-team.fxml",
                "Team manage", (AddTeamController controller) -> {
                    setup.setUpAddTeamController(isUpdate, controller,control,teamDTO );
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
