package com.demo.api.modele;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Spectacle {

    private Integer id;
    private String titre;
    private String horaire;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public LocalDate getDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(horaire, formatter);
    }

}
