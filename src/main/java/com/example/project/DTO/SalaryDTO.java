package com.example.project.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class SalaryDTO {
    private int id;
    private int idUser;
    private BigDecimal basicSalary;
    private float totalHours;
    private float overtimeHours;
    private float leaveHours;
    private BigDecimal bonus;
    private BigDecimal allowance;
    private BigDecimal tax;
    private BigDecimal insurance;
    private BigDecimal finalSalary;
    private Date fromDate;
    private Date toDate;

    public SalaryDTO() {}

    public SalaryDTO(int id, int idUser, BigDecimal basicSalary, float totalHours, float overtimeHours, float leaveHours, BigDecimal bonus, BigDecimal allowance, BigDecimal tax, BigDecimal insurance, BigDecimal finalSalary, Date fromDate, Date toDate) {
        this.id = id;
        this.idUser = idUser;
        this.basicSalary = basicSalary;
        this.totalHours = totalHours;
        this.overtimeHours = overtimeHours;
        this.leaveHours = leaveHours;
        this.bonus = bonus;
        this.allowance = allowance;
        this.tax = tax;
        this.insurance = insurance;
        this.finalSalary = finalSalary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public BigDecimal calculateFinalSalary() {
        BigDecimal hourlyRate = basicSalary;
        BigDecimal hourlyPay = BigDecimal.valueOf(totalHours - overtimeHours).multiply(hourlyRate);
        BigDecimal overtimePay = BigDecimal.valueOf(overtimeHours).multiply(BigDecimal.valueOf(1.5)).multiply(hourlyRate);
        BigDecimal leavePay = BigDecimal.valueOf(leaveHours).multiply(hourlyRate);
        BigDecimal totalSalary = hourlyPay.add(overtimePay).add(bonus).add(allowance).subtract(leavePay).subtract(tax).subtract(insurance);
        return totalSalary;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public float getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(float overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public float getLeaveHours() {
        return leaveHours;
    }

    public void setLeaveHours(float leaveHours) {
        this.leaveHours = leaveHours;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getAllowance() {
        return allowance;
    }

    public void setAllowance(BigDecimal allowance) {
        this.allowance = allowance;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(BigDecimal finalSalary) {
        this.finalSalary = finalSalary;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
