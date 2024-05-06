package com.example.project.DTO;

import java.sql.*;

public class UserDTO {
    private int id;
    private String username;
    private String PASSWORD;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private  String avatar;
    private int idRole;
    private int idPosition;

    public UserDTO() {}



    public UserDTO(int id, String username, String PASSWORD, String fullName, String email, String phoneNumber, String address, String avatar, int idRole, int idPosition) {
        this.id = id;
        this.username = username;
        this.PASSWORD = PASSWORD;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatar = avatar;
        this.idRole = idRole;
        this.idPosition = idPosition;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }

    @Override
    public String toString() {
//        return "UserDTO{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", PASSWORD='" + PASSWORD + '\'' +
//                ", fullName='" + fullName + '\'' +
//                ", email='" + email + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", address='" + address + '\'' +
//                ", avatar=" + avatar +
//                ", idRole=" + idRole +
//                ", idPosition=" + idPosition +
//                '}';
        return username;
    }
}
