package com.example.project.BUS;

import com.example.project.DTO.CheckinCheckoutDTO;
import com.example.project.core.control.TimeControl;

import java.time.LocalDateTime;

public class CheckInBus {

    public static CheckinCheckoutDTO checkinDTO(int userID) {
        CheckinCheckoutDTO request = new CheckinCheckoutDTO();
        request.setDate(LocalDateTime.now());
        request.setCheckinTime(LocalDateTime.now());
        request.setIdUser(userID);
        return request;
    }

    public static CheckinCheckoutDTO checkOutDTO(CheckinCheckoutDTO checkoutDTO) {
        LocalDateTime currentTime = LocalDateTime.now();
        checkoutDTO.setCheckoutTime(currentTime);
        LocalDateTime checkinTime = checkoutDTO.getCheckinTime();
        if (checkinTime != null) {
            long hours = java.time.Duration.between(checkinTime, currentTime).toHours();
            checkoutDTO.setTotalHours(hours);
        }
        return checkoutDTO;
    }
}
