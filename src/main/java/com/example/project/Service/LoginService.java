package com.example.project.Service;
import com.example.project.DAO.LoginDAO;

public class LoginService {
    private final LoginDAO loginDAO = new LoginDAO();

    public LoginService() {
    }
    public boolean authenticateUser(String username, String password) {
        return loginDAO.authenticateUser(username, password);
    }

}
