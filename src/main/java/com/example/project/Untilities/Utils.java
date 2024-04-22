package com.example.project.Untilities;

import com.example.project.core.enums.Content;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
            case TIME ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/time-control.fxml")));
            case TEAM ->
                    FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/com/example/project/control/team-control.fxml")));
        };
        pane.getChildren().clear();
        if (children != null) {
            pane.getChildren().add(children);
        }
    }

}
