package com.example.project.DTO;

import java.sql.Date;

public class LeaveRequestDTO {
    private int id;
    private int idUser;
    private Date requestDate;
    private Date startDate;
    private Date endDate;
    private int numberDay;
    private  String content;
    private String status;
    private int idApprover;

    public LeaveRequestDTO() {}

    public LeaveRequestDTO(int id, int idUser, Date requestDate, Date startDate, Date endDate, int numberDay, String content, String status, int idApprover) {
        this.id = id;
        this.idUser = idUser;
        this.requestDate = requestDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberDay = numberDay;
        this.content = content;
        this.status = status;
        this.idApprover = idApprover;
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdApprover() {
        return idApprover;
    }

    public void setIdApprover(int idApprover) {
        this.idApprover = idApprover;
    }
}
