package com.example.project.DTO;

import java.sql.Blob;

public class TeamDTO {
    private int id;
    private String name;
    private String description;
    private int idLeader;
    private Blob avatar;

    public TeamDTO() {}

    public TeamDTO(int id, String name, String description, int idLeader, Blob avatar) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idLeader = idLeader;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdLeader() {
        return idLeader;
    }

    public void setIdLeader(int idLeader) {
        this.idLeader = idLeader;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return name;
    }
}
