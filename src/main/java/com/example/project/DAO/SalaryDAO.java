package com.example.project.DAO;

import com.example.project.DBConnection;
import com.example.project.DTO.SalaryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {
    // SQL queries
    private static final String INSERT_SALARY = "INSERT INTO company.user_salary (idUser, basicSalary, allowance, tax, insurance) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SALARY = "UPDATE company.user_salary SET basicSalary = ?, allowance = ?, tax = ?, insurance = ? WHERE id = ?";
    private static final String DELETE_SALARY = "DELETE FROM company.user_salary WHERE id = ?";
    private static final String SELECT_SALARY_BY_ID = "SELECT * FROM company.user_salary where idUser=?;";
    private static final String SELECT_ALL_SALARIES = "SELECT * FROM company.user_salary";

    // Insert new salary
    public boolean insertSalary(SalaryDTO salary) {
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SALARY)) {
            statement.setInt(1, salary.getIdUser());
            statement.setBigDecimal(2, salary.getBasicSalary());
            statement.setBigDecimal(3, salary.getAllowance());
            statement.setBigDecimal(4, salary.getTax());
            statement.setBigDecimal(5, salary.getInsurance());

            statement.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update salary
    public boolean updateSalary(SalaryDTO salary) {
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SALARY)) {
            statement.setBigDecimal(1, salary.getBasicSalary());
            statement.setBigDecimal(2, salary.getAllowance());
            statement.setBigDecimal(3, salary.getTax());
            statement.setBigDecimal(4, salary.getInsurance());
            statement.setInt(5, salary.getId());

            statement.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete salary by ID
    public boolean deleteSalary(int id) {
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SALARY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get salary by useID
    public SalaryDTO getSalaryById(int id) {
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SALARY_BY_ID)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    SalaryDTO salary = new SalaryDTO();
                    salary.setId(resultSet.getInt("id"));
                    salary.setIdUser(resultSet.getInt("idUser"));
                    salary.setBasicSalary(resultSet.getBigDecimal("basicSalary"));
                    salary.setAllowance(resultSet.getBigDecimal("allowance"));
                    salary.setTax(resultSet.getBigDecimal("tax"));
                    salary.setInsurance(resultSet.getBigDecimal("insurance"));
                    return salary;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all salaries
    public List<SalaryDTO> getAllSalaries() {
        List<SalaryDTO> salaries = new ArrayList<>();
        try (Connection connection = new DBConnection().createConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SALARIES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                SalaryDTO salary = new SalaryDTO();
                salary.setId(resultSet.getInt("id"));
                salary.setIdUser(resultSet.getInt("idUser"));
                salary.setBasicSalary(resultSet.getBigDecimal("basicSalary"));
                salary.setAllowance(resultSet.getBigDecimal("allowance"));
                salary.setTax(resultSet.getBigDecimal("tax"));
                salary.setInsurance(resultSet.getBigDecimal("insurance"));
                salaries.add(salary);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return salaries;
    }
}
