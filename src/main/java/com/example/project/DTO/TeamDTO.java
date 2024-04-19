package com.example.project.DTO;

public class TeamDTO {
    private int id;
    private int idUser;
    private int idTeam;

    public TeamDTO() {}

    public TeamDTO(int id, int idUser, int idTeam) {
        this.id = id;
        this.idUser = idUser;
        this.idTeam = idTeam;
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

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }
}
