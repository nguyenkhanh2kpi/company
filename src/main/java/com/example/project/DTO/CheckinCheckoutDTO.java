package com.example.project.DTO;

import java.sql.Date;

public class CheckinCheckoutDTO {
    private int id;
    private int idUser;
    private Date checkinTime;
    private Date checkoutTime;
    private float totalHours;
    private Date date;

    public CheckinCheckoutDTO() {}

    public CheckinCheckoutDTO(int id, int idUser, Date checkinTime, Date checkoutTime, float totalHours, Date date) {
        this.id = id;
        this.idUser = idUser;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.totalHours = totalHours;
        this.date = date;
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

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Date getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Date checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
