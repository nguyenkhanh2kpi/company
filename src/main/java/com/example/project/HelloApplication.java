package com.example.project;

import com.example.project.core.Routes;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Routes routes = new Routes(stage);
            routes.goToLogin();
//            Routes routes = new Routes(new Stage());
//            routes.goToHome((Stage) new Stage(),"john_doe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}