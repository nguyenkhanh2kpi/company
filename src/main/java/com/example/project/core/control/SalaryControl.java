package com.example.project.core.control;

import com.example.project.BUS.SalaryBUS;
import com.example.project.DTO.SalaryDTO;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.ExcelExporter;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SalaryControl implements Initializable {
    @FXML
    private TableColumn<SalaryDTO, BigDecimal> bsc;

    @FXML
    private TableColumn<SalaryDTO, BigDecimal> fc;

    @FXML
    private TableColumn<SalaryDTO, Date> fromc;

    @FXML
    private TableColumn<SalaryDTO, BigDecimal> hc;

    @FXML
    private TableColumn<SalaryDTO, Integer> idc;

    @FXML
    private TableColumn<SalaryDTO, BigDecimal> otc;

    @FXML
    private TableView<SalaryDTO> tableview;

    @FXML
    private TableColumn<SalaryDTO, Date> toc;

    @FXML
    private TableColumn<SalaryDTO, String> userc;

    @FXML
    private TableColumn<SalaryDTO, BigDecimal> allowance;
    @FXML
    private TableColumn<SalaryDTO, BigDecimal> tax;
    @FXML
    private TableColumn<SalaryDTO, BigDecimal> insurance;

    @FXML
    private TableColumn<SalaryDTO, BigDecimal> bonus;
    @FXML
    private TableColumn<SalaryDTO, Float> leave;

    @FXML
    private ComboBox<String> combobox;
    SalaryBUS salaryBUS = new SalaryBUS();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 1; i <= 12; i++) {
            combobox.getItems().add(String.valueOf(i));
        }
    }

    private int getSelectedMonth() {
        String selectedMonthStr = combobox.getValue();
        return Integer.parseInt(selectedMonthStr);
    }

    @FXML
    void onAddSalary(ActionEvent event) {
        int selectedMonth = getSelectedMonth();
        userc.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        bsc.setCellValueFactory(new PropertyValueFactory<>("basicSalary"));
        fc.setCellValueFactory(new PropertyValueFactory<>("finalSalary"));
        fromc.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        hc.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
        otc.setCellValueFactory(new PropertyValueFactory<>("overtimeHours"));
        toc.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        insurance.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        tax.setCellValueFactory(new PropertyValueFactory<>("tax"));
        allowance.setCellValueFactory(new PropertyValueFactory<>("allowance"));
        leave.setCellValueFactory(new PropertyValueFactory<>("leaveHours"));
        bonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));


        List<SalaryDTO> salaryDTOS = salaryBUS.getAllSalary(selectedMonth);
        ObservableList<SalaryDTO> data = FXCollections.observableArrayList(salaryDTOS);
        tableview.setItems(data);
    }

    @FXML
    void onDown(ActionEvent event) {
        try {
            ExcelExporter.exportToExcel(tableview, new Stage());
            CustomToast.toast("Success export", ToastStatus.SUCCESS);
        } catch (Exception e) {
            CustomToast.toast("Something went wrong", ToastStatus.FAIL);
        }
    }


}
