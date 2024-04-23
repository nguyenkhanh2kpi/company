package com.example.project.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class TaskDTO {
    private int id;
    private int idCreator;
    private int idAssignee;
    private String taskName;
    private String description;
    private Date deadline;
    private int progress;
    private int idTeam;
    private BigDecimal bonus;
    private int idProject;

    public TaskDTO() {}

    public TaskDTO(int id, int idCreator, int idAssignee, String taskName, String description, Date deadline, int progress, int idTeam, BigDecimal bonus, int idProject) {
        this.id = id;
        this.idCreator = idCreator;
        this.idAssignee = idAssignee;
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.progress = progress;
        this.idTeam = idTeam;
        this.bonus = bonus;
        this.idProject = idProject;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }
}
