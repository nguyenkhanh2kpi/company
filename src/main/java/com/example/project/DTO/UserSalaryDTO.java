package com.example.project.DTO;

import java.math.BigDecimal;

public class UserSalaryDTO {
    private int id;
    private int idUser;
    private BigDecimal basicSalary;
    private BigDecimal allowance;
    private BigDecimal tax;
    private BigDecimal insurance;

    public UserSalaryDTO() {}

    public UserSalaryDTO(int id, int idUser, BigDecimal basicSalary, BigDecimal allowance, BigDecimal tax, BigDecimal insurance) {
        this.id = id;
        this.idUser = idUser;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.tax = tax;
        this.insurance = insurance;
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
}
