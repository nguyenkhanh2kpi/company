package com.example.project.BUS;

import com.example.project.DTO.UserDTO;
import com.example.project.controllers.UserProfileController;

public class UserBUS {
    public UserDTO formToDTO(UserProfileController controller,UserDTO userDTO) {
        userDTO.setUsername(controller.getUsernameTxt().getText());
        userDTO.setEmail(controller.getEmailTxt().getText());
        userDTO.setFullName(controller.getFullNameTxt().getText());
        userDTO.setPhoneNumber(controller.getPhoneTxt().getText());
        userDTO.setAddress(controller.getAddressTxt().getText());
        userDTO.setIdRole(userDTO.getIdRole());
        return userDTO;
    }


}
