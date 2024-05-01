package com.example.project.BUS;

import com.example.project.DAO.EmployeeDAO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.Utils;
import com.example.project.controllers.UserProfileController;
import com.example.project.core.control.UserListControl;

import java.util.List;
import java.util.Optional;

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

    public UserDTO userListControlToDTO(UserListControl control) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(0);
        userDTO.setUsername(control.getUsernameTxt().getText());
        userDTO.setEmail(control.getEmailTxt().getText());
        userDTO.setFullName(control.getFullNameTxt().getText());
        userDTO.setPhoneNumber(control.getPhoneTxt().getText());
        userDTO.setAddress(control.getAddressTxt().getText());
        userDTO.setIdRole(Utils.getIdRoleFromComboBox(control.getRoleCmb().getSelectionModel().getSelectedItem()));
        userDTO.setIdPosition(Utils.getIdPositionFromCombobox(control.getPositionCmb().getSelectionModel().getSelectedItem()));
        userDTO.setAvatar("/public/images/avatar/default.jpg");
        userDTO.setPASSWORD("1234");
        return userDTO;
    }

    public UserDTO userListControlToDTO(UserListControl control, UserDTO selectedUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(selectedUser.getId());
        userDTO.setUsername(control.getUsernameTxt().getText());
        userDTO.setEmail(control.getEmailTxt().getText());
        userDTO.setFullName(control.getFullNameTxt().getText());
        userDTO.setPhoneNumber(control.getPhoneTxt().getText());
        userDTO.setAddress(control.getAddressTxt().getText());
        userDTO.setIdRole(Utils.getIdRoleFromComboBox(control.getRoleCmb().getSelectionModel().getSelectedItem()));
        userDTO.setIdPosition(Utils.getIdPositionFromCombobox(control.getPositionCmb().getSelectionModel().getSelectedItem()));
        userDTO.setAvatar(selectedUser.getAvatar());
        userDTO.setPASSWORD(selectedUser.getPASSWORD());
        return userDTO;
    }
    public boolean isExistUsername(String username) {
        EmployeeDAO employeeDAO1 = new EmployeeDAO();
        List<UserDTO> users = employeeDAO1.searchUser(username,1);
        if(users.isEmpty()) {
            return false;
        }
        return true;
    }

    public UserDTO getUserFromId(int userID) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<UserDTO> users = employeeDAO.getAll();
        Optional<UserDTO> userOptional = users.stream()
                .filter(user -> user.getId() == userID)
                .findFirst();
        return userOptional.orElse(null);
    }

    public UserDTO getUserFromUsername(String username) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<UserDTO> users = employeeDAO.getAll();
        Optional<UserDTO> userOptional = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
        return userOptional.orElse(null);
    }

    public int getIDFromUsername(String username) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<UserDTO> users = employeeDAO.getAll();
        Optional<UserDTO> userOptional = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
        if(userOptional.isPresent()) {
            return userOptional.get().getId();
        }
        return 0;
    }

}
