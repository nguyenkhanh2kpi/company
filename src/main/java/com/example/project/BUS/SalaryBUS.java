package com.example.project.BUS;

import com.example.project.DAO.CheckinCheckoutDAO;
import com.example.project.DAO.EmployeeDAO;
import com.example.project.DAO.SalaryDAO;
import com.example.project.DAO.TaskDAO;
import com.example.project.DTO.*;
import org.apache.poi.ss.formula.functions.T;

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

    TaskDAO taskDAO = new TaskDAO();

    SalaryDAO salaryDAO = new SalaryDAO();
    public List<SalaryDTO> getAllSalary(int month) {
        List<SalaryDTO> salaryDTOS = new ArrayList<>();
        List<UserDTO> userDTOS = employeeDAO.getAll();
        userDTOS.forEach(user -> {
            SalaryDTO salaryDTO = salaryDAO.getSalaryById(user.getId());
            salaryDTO.setIdUser(user.getId());
            salaryDTO.setTotalHours(getTotalTimeByMonth(user.getId(), month));
            salaryDTO.setOvertimeHours(getOverTimeByMonth(user.getId(), month));
            salaryDTO.setLeaveHours(getLeaveTimeByMonth(user.getId(), month));
            salaryDTO.setBonus(getTotalBonus(user.getId()));
            LocalDate fromDate = LocalDate.of(LocalDate.now().getYear(), month, 1);
            LocalDate toDate = LocalDate.of(LocalDate.now().getYear(), month, fromDate.lengthOfMonth());
            salaryDTO.setFromDate(java.sql.Date.valueOf(fromDate));
            salaryDTO.setToDate(java.sql.Date.valueOf(toDate));
            salaryDTO.setFinalSalary(salaryDTO.calculateFinalSalary());
            salaryDTOS.add(salaryDTO);
        });
        return salaryDTOS;
    }


    public BigDecimal getTotalBonus(Integer userId) {
        BigDecimal total = BigDecimal.ZERO;
        List<TaskDTO> taskDTOS = taskDAO.getAllTasks();
        for (TaskDTO taskDTO : taskDTOS) {
            if (taskDTO.getIdAssignee()==(userId)) {
                total = total.add(taskDTO.getBonus());
            }
        }
        return total;
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
    public float getLeaveTimeByMonth(int userId, int month) {
        UserDTO user = getUserById(userId);
        if (user != null) {
            List<CheckinCheckoutDTO> checkins = checkinCheckoutDAO.getAllCheckins();
            List<CheckinCheckoutDTO> checkinsInMonthForUser = checkins.stream()
                    .filter(checkin -> checkin.getIdUser() == userId &&
                            checkin.getCheckinTime().toLocalDate().getMonthValue() == month)
                    .collect(Collectors.toList());

            float totalLeaveTime = 0;
            for (CheckinCheckoutDTO checkin : checkinsInMonthForUser) {
                float workedHours = checkin.getTotalHours();
                if (workedHours < 8) {
                    totalLeaveTime += 8 - workedHours;
                }
            }
            return totalLeaveTime;
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
