package com.example.project.Untilities;

import com.example.project.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {


    // chuyển đến trang home : truyền vào Stage mới và Stage hiện tại
    // chưa hoàn thiện sẽ bổ sung thêm các thuộc tính để xác định role,data ....
    public static void getHomePage(Stage stage, Stage prestage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        stage.setTitle("Home");
        stage.setScene(scene);
        prestage.close();
        stage.show();
    }


}
