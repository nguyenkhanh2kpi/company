package com.example.project.core.control;

import com.example.project.core.Routes;
import javafx.stage.Stage;

public class ProjectControl {

    Routes routes = new Routes(new Stage());

    public void onAddProjectClick() {
        routes.goToProject();
    }
}
