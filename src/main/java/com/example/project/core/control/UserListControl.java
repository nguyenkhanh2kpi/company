package com.example.project.core.control;

import com.example.project.BUS.UserBUS;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DAO.SalaryDAO;
import com.example.project.DTO.SalaryDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.CustomAlert;
import com.example.project.Untilities.CustomToast;
import com.example.project.Untilities.CustomValidate;
import com.example.project.Untilities.Utils;
import com.example.project.core.enums.Position;
import com.example.project.core.enums.Role;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UserListControl implements Initializable {
    @FXML
    Button addUserBtn;
    @FXML
    Button deleteUserBtn;
    @FXML
    private TableView<UserDTO> dataTable;
    @FXML
    TableColumn<UserDTO, String> column1;
    @FXML
    TableColumn<UserDTO, String> column2;
    @FXML
    TableColumn<UserDTO, String> column3;
    @FXML
    TableColumn<UserDTO, String> column4;
    @FXML
    TableColumn<UserDTO, String> column5;
    @FXML
    TableColumn<UserDTO, String> column6;
    @FXML
    TextField usernameTxt;
    @FXML
    TextField fullNameTxt;
    @FXML
    TextField emailTxt;
    @FXML
    TextField phoneTxt;
    @FXML
    TextField addressTxt;
    @FXML
    ComboBox<Role> roleCmb;
    @FXML
    ComboBox<Position> positionCmb;
    @FXML
    Button saveBtn;


    @FXML
    TextField searchText;
    private List<UserDTO> users;
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static UserBUS userBUS = new UserBUS();

    private UserDTO selectedUser;

    public void handleTableRowClick() {
        TablePosition<UserDTO, ?> selectedRow = dataTable.getSelectionModel().getSelectedCells().get(0);
        int rowIndex = selectedRow.getRow();
        TableColumn<UserDTO, ?> firstColumn = dataTable.getColumns().get(0);
        Object valueFirstColumn = firstColumn.getCellData(rowIndex);
        Optional<UserDTO> firstUserOptional = users.stream()
                .filter(user -> user.getId() == Integer.parseInt(valueFirstColumn.toString()))
                .findFirst();
        selectedUser = firstUserOptional.get();
        usernameTxt.setText(selectedUser.getUsername());
        fullNameTxt.setText(selectedUser.getFullName());
        emailTxt.setText(selectedUser.getEmail());
        phoneTxt.setText(selectedUser.getPhoneNumber());
        addressTxt.setText(selectedUser.getAddress());
        roleCmb.getSelectionModel().select(Utils.getRoleFromID(selectedUser.getIdRole()));
        positionCmb.getSelectionModel().select(Utils.getPositionFromID(selectedUser.getIdPosition()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadTable();
        roleCmb.setItems(FXCollections.observableArrayList(
                Role.Employee,
                Role.Manager,
                Role.Admin
        ));
        positionCmb.setItems(FXCollections.observableArrayList(
                Position.Leader,
                Position.Manager,
                Position.Employee,
                Position.Hr));
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    if(selectedUser!=null) {
                        setSalary(selectedUser);
                        if(validate(selectedUser)) {
                            if(updateUser()) {
                                CustomToast.toast("update success!", ToastStatus.SUCCESS);
                                clearTextFields();
                                LoadTable();
                            }
                        }
                    } else {
                        if (validate()) {
                            if (addUser()) {
                                CustomToast.toast("Success!", ToastStatus.SUCCESS);
                                clearTextFields();
                                LoadTable();
                            } else {
                                CustomToast.toast("Some thing wrong!!!", ToastStatus.FAIL);
                            }
                        }
                    }
                } catch (Exception e) {
                    CustomToast.toast("Some thing wrong!!!", ToastStatus.FAIL);
                }

            }
        });

        addUserBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearTextFields();
                selectedUser = null;
            }
        });

        deleteUserBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                onDelete();
            }
        });
        dataTable.setOnMouseClicked(event -> handleTableRowClick());

        searchText.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                List<UserDTO> users = employeeDAO.getAll();
                List<UserDTO>  filterUser =  users.stream().filter(userDTO -> userDTO.getFullName().toLowerCase().contains(searchText.getText())).collect(Collectors.toList());

                if(searchText.getText()!="") {
                    dataTable.getItems().clear();
                    SetupDataTable(dataTable);
                    filterUser.forEach(userDTO -> {
                        dataTable.getItems().add(userDTO);
                    });
                } else {
                    LoadTable();
                }
            }
        });
    }

    public void SetupDataTable(TableView<UserDTO> dataTable) {
        dataTable.getColumns().clear();
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("username"));
        column3.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        column4.setCellValueFactory(new PropertyValueFactory<>("email"));
        column5.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        column6.setCellValueFactory(new PropertyValueFactory<>("address"));
        dataTable.getColumns().add(column1);
        dataTable.getColumns().add(column2);
        dataTable.getColumns().add(column3);
        dataTable.getColumns().add(column4);
        dataTable.getColumns().add(column5);
        dataTable.getColumns().add(column6);
    }

    public void LoadTable() {
        users = employeeDAO.getAll();
        dataTable.getItems().clear();
        SetupDataTable(dataTable);
        users.forEach(userDTO -> {
            dataTable.getItems().add(userDTO);
        });
    }

    public boolean validate() {
        if (userBUS.isExistUsername(usernameTxt.getText())) {
            CustomAlert.showAlertError("Username", "username is exist");
            return false;
        }
        if (!CustomValidate.validateText(usernameTxt.getText())) {
            CustomAlert.showAlertError("Username", "username must not be null");
            return false;
        }
        if (!CustomValidate.validateText(fullNameTxt.getText())) {
            CustomAlert.showAlertError("Fullname", "fullname must not be null");
            return false;
        }
        if (!CustomValidate.validateEmail(emailTxt.getText())) {
            CustomAlert.showAlertError("Email", "email must be valid");
            return false;
        }
        if (!CustomValidate.validateNumber(phoneTxt.getText(), "Phone")) {
            CustomAlert.showAlertError("Phone", "phone must be valid");
            return false;
        }
        if (!CustomValidate.validateText(addressTxt.getText())) {
            CustomAlert.showAlertError("Address", "address must be null");
            return false;
        }
        return true;
    }

    public boolean validate(UserDTO selectedUser) {
        if (userBUS.isExistUsername(usernameTxt.getText()) && usernameTxt.getText()!=selectedUser.getUsername()) {
            CustomAlert.showAlertError("Username", "username is exist");
            return false;
        }
        if (!CustomValidate.validateText(usernameTxt.getText())) {
            CustomAlert.showAlertError("Username", "username must not be null");
            return false;
        }
        if (!CustomValidate.validateText(fullNameTxt.getText())) {
            CustomAlert.showAlertError("Fullname", "fullname must not be null");
            return false;
        }
        if (!CustomValidate.validateEmail(emailTxt.getText())) {
            CustomAlert.showAlertError("Email", "email must be valid");
            return false;
        }
        if (!CustomValidate.validateNumber(phoneTxt.getText(), "Phone")) {
            CustomAlert.showAlertError("Phone", "phone must be valid");
            return false;
        }
        if (!CustomValidate.validateText(addressTxt.getText())) {
            CustomAlert.showAlertError("Address", "address must be null");
            return false;
        }
        return true;
    }




    private void clearTextFields() {
        usernameTxt.clear();
        fullNameTxt.clear();
        emailTxt.clear();
        phoneTxt.clear();
        addressTxt.clear();
    }

    public void onDelete() {
        try {
            if(selectedUser!=null) {
                System.out.println(userBUS.userListControlToDTO(UserListControl.this));
                if(deleteUser()) {
                    CustomToast.toast("delete success!", ToastStatus.SUCCESS);
                    clearTextFields();
                    LoadTable();
                }
            } else {
                CustomToast.toast("Please select user!", ToastStatus.INFO);
            }
        } catch (Exception e) {
            CustomToast.toast("Some thing went wrong!", ToastStatus.FAIL);
        }
    }


    public boolean addUser() {
        return employeeDAO.insertUser(userBUS.userListControlToDTO(this));
    }

    public void setSalary(UserDTO userDTO) {
        SalaryDAO salaryDAO = new SalaryDAO();
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setIdUser(userDTO.getId());
        SalaryDTO existingSalary = salaryDAO.getSalaryById(userDTO.getId());

        if (existingSalary != null) {
            return;
        }



        switch (userDTO.getIdPosition()) {
            case 1:
                salaryDTO.setBasicSalary(BigDecimal.valueOf(100000));
                salaryDTO.setAllowance(BigDecimal.valueOf(500000.00));
                salaryDTO.setTax(BigDecimal.valueOf(1500000.00));
                salaryDTO.setInsurance(BigDecimal.valueOf(700000.00));
                break;
            case 2:
                salaryDTO.setBasicSalary(BigDecimal.valueOf(120000));
                salaryDTO.setAllowance(BigDecimal.valueOf(600000.00));
                salaryDTO.setTax(BigDecimal.valueOf(1600000.00));
                salaryDTO.setInsurance(BigDecimal.valueOf(750000.00));
                break;
            case 3:
                salaryDTO.setBasicSalary(BigDecimal.valueOf(70000));
                salaryDTO.setAllowance(BigDecimal.valueOf(700000.00));
                salaryDTO.setTax(BigDecimal.valueOf(1000000.00));
                salaryDTO.setInsurance(BigDecimal.valueOf(600000.00));
                break;
            case 4:
                salaryDTO.setBasicSalary(BigDecimal.valueOf(160000));
                salaryDTO.setAllowance(BigDecimal.valueOf(800000.00));
                salaryDTO.setTax(BigDecimal.valueOf(1800000.00));
                salaryDTO.setInsurance(BigDecimal.valueOf(850000.00));
                break;
            default:
                break;
        }
        salaryDAO.insertSalary(salaryDTO);
    }


    public boolean updateUser() {
        return employeeDAO.updateUser(userBUS.userListControlToDTO(this,selectedUser));
    }

    public boolean deleteUser() {
        return employeeDAO.deleteUser(userBUS.userListControlToDTO(this,selectedUser));
    }

    public TextField getUsernameTxt() {
        return usernameTxt;
    }

    public void setUsernameTxt(TextField usernameTxt) {
        this.usernameTxt = usernameTxt;
    }

    public TextField getFullNameTxt() {
        return fullNameTxt;
    }

    public void setFullNameTxt(TextField fullNameTxt) {
        this.fullNameTxt = fullNameTxt;
    }

    public TextField getEmailTxt() {
        return emailTxt;
    }

    public void setEmailTxt(TextField emailTxt) {
        this.emailTxt = emailTxt;
    }

    public TextField getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(TextField phoneTxt) {
        this.phoneTxt = phoneTxt;
    }

    public TextField getAddressTxt() {
        return addressTxt;
    }

    public void setAddressTxt(TextField addressTxt) {
        this.addressTxt = addressTxt;
    }

    public ComboBox<Role> getRoleCmb() {
        return roleCmb;
    }

    public void setRoleCmb(ComboBox<Role> roleCmb) {
        this.roleCmb = roleCmb;
    }

    public ComboBox<Position> getPositionCmb() {
        return positionCmb;
    }

    public void setPositionCmb(ComboBox<Position> positionCmb) {
        this.positionCmb = positionCmb;
    }
}
