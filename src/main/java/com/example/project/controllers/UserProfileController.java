package com.example.project.controllers;

import com.example.project.BUS.UserBUS;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.CustomToast;
import com.example.project.core.enums.Role;
import com.example.project.core.enums.ToastStatus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField fullNameTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private TextField rePasswordTxt;

    @FXML
    private ComboBox<Role> roleCmb;

    @FXML
    private TextField AddressTxt;

    @FXML
    private Button updateAvatarBtn;

    @FXML
    private Button saveProfileButton;

    @FXML
    private ImageView imageView;

    private UserDTO userDTO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleCmb.setItems(FXCollections.observableArrayList(
                Role.Admin,
                Role.Manager,
                Role.Employee
        ));
        saveProfileButton.setOnAction(this::handleSaveProfile);
        updateAvatarBtn.setOnAction(this::handleUpdateAvatar);
    }

    private void handleSaveProfile(ActionEvent actionEvent) {
        EmployeeDAO dao = new EmployeeDAO();
        UserBUS bus = new UserBUS();
        dao.updateUser(bus.formToDTO(this, userDTO));
    }

    private void handleUpdateAvatar(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn hình ảnh đại diện");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Ảnh", "*.jpg", "*.jpeg", "*.png", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String destinationPath = Paths.get(getClass().getResource("/public/images/avatar").toURI()).toString();
                File destinationFolder = new File(destinationPath);
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdirs();
                }

                String newFileName = "avatar_" + System.currentTimeMillis() + ".jpg";
                Path destinationFilePath = Paths.get(destinationPath, newFileName);
                Files.copy(selectedFile.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                String newImagePath = "/public/images/avatar/" + newFileName;
                userDTO.setAvatar(newImagePath);
                imageView.setImage(new Image(newImagePath));
                CustomToast.toast("Success!", ToastStatus.SUCCESS);
            } catch (IOException | URISyntaxException e) {
                CustomToast.toast("Some thing wrong!!!", ToastStatus.FAIL);
                e.printStackTrace();
            }
        }
    }



    public TextField getUsernameTxt() {
        return usernameTxt;
    }

    public void setUsernameTxt(TextField usernameTxt) {
        this.usernameTxt = usernameTxt;
    }

    public TextField getEmailTxt() {
        return emailTxt;
    }

    public void setEmailTxt(TextField emailTxt) {
        this.emailTxt = emailTxt;
    }

    public TextField getFullNameTxt() {
        return fullNameTxt;
    }

    public void setFullNameTxt(TextField fullNameTxt) {
        this.fullNameTxt = fullNameTxt;
    }

    public TextField getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(TextField phoneTxt) {
        this.phoneTxt = phoneTxt;
    }

    public TextField getPasswordTxt() {
        return passwordTxt;
    }

    public void setPasswordTxt(TextField passwordTxt) {
        this.passwordTxt = passwordTxt;
    }

    public TextField getRePasswordTxt() {
        return rePasswordTxt;
    }

    public void setRePasswordTxt(TextField rePasswordTxt) {
        this.rePasswordTxt = rePasswordTxt;
    }

    public ComboBox<Role> getRoleCmb() {
        return roleCmb;
    }

    public void setRoleCmb(ComboBox<Role> roleCmb) {
        this.roleCmb = roleCmb;
    }

    public TextField getAddressTxt() {
        return AddressTxt;
    }

    public void setAddressTxt(TextField addressTxt) {
        AddressTxt = addressTxt;
    }

    public Button getUpdateAvatarBtn() {
        return updateAvatarBtn;
    }

    public void setUpdateAvatarBtn(Button updateAvatarBtn) {
        this.updateAvatarBtn = updateAvatarBtn;
    }

    public Button getSaveProfileButton() {
        return saveProfileButton;
    }

    public void setSaveProfileButton(Button saveProfileButton) {
        this.saveProfileButton = saveProfileButton;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
