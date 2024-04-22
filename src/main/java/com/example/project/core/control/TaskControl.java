package com.example.project.core.control;

import com.example.project.core.Routes;
import javafx.stage.Stage;

public class TaskControl {

    Routes routes = new Routes(new Stage());

    public void onAddTask() {
        routes.goToTask();
    }
}
