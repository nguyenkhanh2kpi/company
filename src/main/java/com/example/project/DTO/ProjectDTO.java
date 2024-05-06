package com.example.project.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class ProjectDTO {
    private int id;
    private int idCreator;
    private int idAssignee;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int progress;
    private int idTeam;
    private BigDecimal bonus;

    public ProjectDTO() {}

    public ProjectDTO(int id, int idCreator, int idAssignee, String name, String description, Date startDate, Date endDate, int progress, int idTeam, BigDecimal bonus) {
        this.id = id;
        this.idCreator = idCreator;
        this.idAssignee = idAssignee;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.idTeam = idTeam;
        this.bonus = bonus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }

    public int getIdAssignee() {
        return idAssignee;
    }

    public void setIdAssignee(int idAssignee) {
        this.idAssignee = idAssignee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return name;
    }
}
