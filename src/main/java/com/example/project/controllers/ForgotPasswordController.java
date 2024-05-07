package com.example.project.controllers;

import com.example.project.BUS.UserBUS;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.Untilities.CustomToast;
import com.example.project.Untilities.EmailSender;
import com.example.project.Untilities.PasswordGenerator;
import com.example.project.core.enums.ToastStatus;
import jakarta.mail.MessagingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {
    public EmployeeDAO employeeDAO = new EmployeeDAO();

    @FXML
    private TextField emailtxt;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void send() throws MessagingException {
        try {
            String newPassword = PasswordGenerator.generateRandomPassword(10);
            EmailSender.sendEmail(emailtxt.getText(),"Reset password email","Your new password is: " + newPassword, "123");
            UserBUS userBUS = new UserBUS();
            var user = userBUS.getUserByEmail(emailtxt.getText());
            user.setPASSWORD(newPassword);
            CustomToast.toast("Open your email to receive the new password!!", ToastStatus.SUCCESS);
            employeeDAO.updateUser(user);
        } catch (Exception e) {
            CustomToast.toast("Something went wrong!!", ToastStatus.FAIL);
        }
    }
}
