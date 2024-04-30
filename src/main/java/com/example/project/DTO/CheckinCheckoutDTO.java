package com.example.project.DTO;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CheckinCheckoutDTO {
    private int id;
    private int idUser;
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
    private float totalHours;
    private LocalDateTime date;

    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CheckinCheckoutDTO() {
    }

    public CheckinCheckoutDTO(int id, int idUser, LocalDateTime checkinTime, LocalDateTime checkoutTime, float totalHours, LocalDateTime date, String fullName) {
        this.id = id;
        this.idUser = idUser;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.totalHours = totalHours;
        this.date = date;
        this.fullName =fullName;
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

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CheckinCheckoutDTO{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", checkinTime=" + checkinTime +
                ", checkoutTime=" + checkoutTime +
                ", totalHours=" + totalHours +
                ", date=" + date +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}