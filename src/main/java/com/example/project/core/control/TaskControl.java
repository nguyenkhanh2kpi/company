package com.example.project.core.control;

import com.example.project.core.Routes;
import javafx.stage.Stage;

public class TaskControl {

    Routes routes = new Routes(new Stage());

    public void onAddTask() {
        routes.goToTask(username);
    }

    public void viewEditTask() {
        routes.viewOrEditTask();
    }

    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
