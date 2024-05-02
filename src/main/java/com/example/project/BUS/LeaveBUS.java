package com.example.project.BUS;


import com.example.project.DAO.LeaveRequestDAO;
import com.example.project.DTO.LeaveRequestDTO;
import com.example.project.DTO.UserDTO;
import com.example.project.Untilities.CustomToast;
import com.example.project.controllers.LeaveRequestController;
import com.example.project.core.enums.LeaveStatus;
import com.example.project.core.enums.ToastStatus;
import java.sql.Date;
import java.time.temporal.ChronoUnit;


public class LeaveBUS {
    UserBUS userBUS = new UserBUS();
    LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAO();
    public LeaveRequestDTO toDTO(LeaveRequestController controller) {
        LeaveRequestDTO leaveRequestDTO = new LeaveRequestDTO();
        leaveRequestDTO.setIdUser(userBUS.getIDFromUsername(controller.getUsername()));
        leaveRequestDTO.setRequestDate(Date.valueOf(controller.getDateTime().getValue()));
        leaveRequestDTO.setStartDate(Date.valueOf(controller.getFromt().getValue()));
        leaveRequestDTO.setEndDate(Date.valueOf(controller.getTot().getValue()));
        long numberOfDays = ChronoUnit.DAYS.between(controller.getFromt().getValue(), controller.getTot().getValue());
        leaveRequestDTO.setNumberDay((int) numberOfDays);
        leaveRequestDTO.setStatus(LeaveStatus.CREATED.toString());
        leaveRequestDTO.setContent(controller.getTitletxt().getText());
        return leaveRequestDTO;
    }


    public void cancel(LeaveRequestDTO leaveRequestDTO, String username) {
        UserDTO user = userBUS.getUserFromUsername(username);
        if(leaveRequestDTO.getIdUser()==user.getId()) {
            leaveRequestDTO.setStatus(LeaveStatus.CANCEL.toString());
            if(leaveRequestDAO.updateStatus(leaveRequestDTO)) {
                CustomToast.toast("Success ", ToastStatus.SUCCESS);
            } else {
                CustomToast.toast("Some thing went wrong ", ToastStatus.FAIL);
            }
        } else {
            CustomToast.toast("You can not cancel ", ToastStatus.FAIL);
        }
    }

    public void update(LeaveRequestDTO leaveRequestDTO, String username,LeaveStatus status) {
        UserDTO user = userBUS.getUserFromUsername(username);
        leaveRequestDTO.setStatus(status.toString());
        if(user.getIdRole()==2 || user.getIdRole()==3) {
            if(leaveRequestDAO.updateStatus(leaveRequestDTO)) {
                CustomToast.toast("Success ", ToastStatus.SUCCESS);
            } else {
                CustomToast.toast("Some thing went wrong ", ToastStatus.FAIL);
            }
        }else {
            CustomToast.toast("You can not update ", ToastStatus.FAIL);
        }
    }


}
