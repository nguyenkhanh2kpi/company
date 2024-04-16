package com.example.project.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Routes {
    private Stage stage;

    public Routes(Stage stage) {
        this.stage = stage;
    }

    public void goToLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 854, 503);
            scene.getStylesheets().add(getClass().getResource("/static/css/login.css").toExternalForm());
            stage.setTitle("Login!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToHome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project/home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1110, 710);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // chuyển đến trang home : truyền vào Stage mới và Stage hiện tại
    // chưa hoàn thiện sẽ bổ sung thêm các thuộc tính để xác định role,data ....
    public void goToMain(Stage prestage, String username) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project/main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1060, 600);
            scene.getStylesheets().add(getClass().getResource("/static/css/main.css").toExternalForm());
            stage.setTitle("Home");
            stage.setScene(scene);
            prestage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
