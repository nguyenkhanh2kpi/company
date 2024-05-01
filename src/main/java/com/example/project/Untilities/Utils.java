package com.example.project.Untilities;

import com.example.project.controllers.HomeController;
import com.example.project.core.control.LeaveRequestControl;
import com.example.project.core.control.TimeControl;
import com.example.project.core.enums.Content;
import com.example.project.core.enums.Position;
import com.example.project.core.enums.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class Utils {
    public static void getContent(BorderPane pane, Content content) throws IOException {
        Node children = switch (content) {
            case USER ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/user-list.fxml")));
            case PROJECT ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/project-control.fxml")));
            case LEAVE ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/Leave-control.fxml")));
            case SALARY ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/salary-control.fxml")));
            case TASK ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/task-control.fxml")));
            case HOME ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/home-control.fxml")));
//            case TIME ->{}
//                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/time-control.fxml")));
            case TEAM ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/team-control.fxml")));
        };
        pane.getChildren().clear();
        if (children != null) {
            pane.getChildren().add(children);
        }
    }

    public static void getContent(BorderPane pane, Button usernameButton) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Utils.class.getResource("/com/example/project/control/time-control.fxml"));
        Node children = loader.load();
        TimeControl control = loader.getController();
        control.setUserNameButton(usernameButton);
        pane.getChildren().clear();
        if (children != null) {
            pane.getChildren().add(children);
        }
    }

    public static void getContentLeave(BorderPane pane, String username) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Utils.class.getResource("/com/example/project/control/Leave-control.fxml"));
        Node children = loader.load();
        LeaveRequestControl control = loader.getController();
        control.setUsername(username);
        pane.getChildren().clear();
        if (children != null) {
            pane.getChildren().add(children);
        }
    }


    public static Role getRoleFromID(int id) {
        Role role = null;
        if (id == 1) {
            role = Role.Employee;
        } else if (id == 2) {
            role = Role.Manager;
        } else if (id == 3) {
            role = Role.Admin;
        }
        return role;
    }

    public static int getIdRoleFromComboBox(Role selectedRole) {
        if (selectedRole != null) {
            switch (selectedRole) {
                case Employee:
                    return 1;
                case Manager:
                    return 2;
                case Admin:
                    return 3;
                default:
                    return -1;
            }
        } else {
            return -1;
        }
    }

    public static Position getPositionFromID(int id) {
        Position position = null;
        if (id == 2) {
            position = Position.Manager;
        } else if (id == 1) {
            position = Position.Leader;
        } else if (id == 4) {
            position = Position.Hr;
        } else {
            position = Position.Employee;
        }
        return position;
    }

    public static int getIdPositionFromCombobox(Position position) {
        if (position != null) {
            switch (position) {
                case Manager:
                    return 2;
                case Leader:
                    return 1;
                case Employee:
                    return 3;
                case Hr:
                    return 4;
                default:
                    return -1;
            }
        } else {
            return -1;
        }
    }


}
