package com.example.project.BUS;

import com.example.project.DAO.CheckinCheckoutDAO;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DTO.CheckinCheckoutDTO;
import com.example.project.DTO.SalaryDTO;
import com.example.project.DTO.UserDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SalaryBUS {
    EmployeeDAO employeeDAO = new EmployeeDAO();
    CheckinCheckoutDAO checkinCheckoutDAO = new CheckinCheckoutDAO();
    public List<SalaryDTO> getAllSalary(int month) {
        List<SalaryDTO> salaryDTOS = new ArrayList<>();
        List<UserDTO> userDTOS = employeeDAO.getAll();
        userDTOS.forEach(user -> {
            SalaryDTO salaryDTO = new SalaryDTO();
            salaryDTO.setIdUser(user.getId());
            salaryDTO.setTotalHours(getTotalTimeByMonth(user.getId(), month));
            salaryDTO.setOvertimeHours(getOverTimeByMonth(user.getId(), month));
            salaryDTO.setBasicSalary(BigDecimal.valueOf(100000));
            salaryDTO.setFinalSalary(calculate(salaryDTO));
            LocalDate fromDate = LocalDate.of(LocalDate.now().getYear(), month, 1);
            LocalDate toDate = LocalDate.of(LocalDate.now().getYear(), month, fromDate.lengthOfMonth());
            salaryDTO.setFromDate(java.sql.Date.valueOf(fromDate));
            salaryDTO.setToDate(java.sql.Date.valueOf(toDate));
            salaryDTOS.add(salaryDTO);
        });
        return salaryDTOS;
    }

    public BigDecimal calculate(SalaryDTO salaryDTO) {
        BigDecimal regularPay = BigDecimal.valueOf(salaryDTO.getTotalHours())
                .multiply(salaryDTO.getBasicSalary());
        BigDecimal overtimePay = BigDecimal.valueOf(salaryDTO.getOvertimeHours())
                .multiply(salaryDTO.getBasicSalary())
                .multiply(BigDecimal.valueOf(1.5f));
        BigDecimal finalSalary = regularPay.add(overtimePay);
        return finalSalary;
    }

    public float getTotalTimeByMonth(int userId, int month) {
        UserDTO user = getUserById(userId);
        if (user != null) {
            List<CheckinCheckoutDTO> checkins = checkinCheckoutDAO.getAllCheckins();
            List<CheckinCheckoutDTO> checkinsInMonthForUser = checkins.stream()
                    .filter(checkin -> checkin.getIdUser() == userId &&
                            checkin.getCheckinTime().toLocalDate().getMonthValue() == month)
                    .collect(Collectors.toList());

            float total = 0;
            for (CheckinCheckoutDTO checkin : checkinsInMonthForUser) {
                total += Math.min(checkin.getTotalHours(), 8);
            }
            return total;
        }
        return 0;
    }

    public float getOverTimeByMonth(int userId, int month) {
        UserDTO user = getUserById(userId);
        if (user != null) {
            List<CheckinCheckoutDTO> checkins = checkinCheckoutDAO.getAllCheckins();
            List<CheckinCheckoutDTO> checkinsInMonthForUser = checkins.stream()
                    .filter(checkin -> checkin.getIdUser() == userId &&
                            checkin.getCheckinTime().toLocalDate().getMonthValue() == month)
                    .collect(Collectors.toList());

            float total = 0;
            for (CheckinCheckoutDTO checkin : checkinsInMonthForUser) {
                total += Math.max(checkin.getTotalHours() - 8, 0);
            }
            return total;
        }
        return 0;
    }
    private UserDTO getUserById(int userId) {
        List<UserDTO> userDTOS = employeeDAO.getAll();
        return userDTOS.stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElse(null);
    }

}
