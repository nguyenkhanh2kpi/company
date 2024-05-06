package com.example.project.core.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeControl implements Initializable {

    @FXML
    public Text projectNumber;
    @FXML
    public Text taskNumber;
    @FXML
    public Text teamNumber;
    @FXML
    public Text timeNumber;
    @FXML
    public Text salaryNumber;
    @FXML
    public Text leaveNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
    }

    public void LoadData(){

    }
}
