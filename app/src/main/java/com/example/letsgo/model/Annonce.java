package com.example.letsgo.model;

import java.io.Serializable;
import java.util.Date;

public class Annonce implements Serializable {
    private int id;
    private String titre;
    private String description;
    private Date date_evenement;
    private String duree;
    private int max_participants;
    private User chef;
    private double frais;
    private String images_url;
    private String contact;
    private String adresse;

    public Annonce() {
    }



    @Override
    public String toString() {
        return "Annonce{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", date_evenement=" + date_evenement +
                ", duree='" + duree + '\'' +
                ", max_participants='" + max_participants + '\'' +
                ", chef=" + chef +
                ", frais=" + frais +
                ", images_url='" + images_url + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Annonce(int id, String titre, String description, Date date_evenement, String duree, int max_participants, User chef, double frais, String images_url, String contact) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_evenement = date_evenement;
        this.duree = duree;
        this.max_participants = max_participants;
        this.chef = chef;
        this.frais = frais;
        this.images_url = images_url;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public int getMax_participants() {
        return max_participants;
    }

    public void setMax_participants(int max_participants) {
        this.max_participants = max_participants;
    }

    public User getChef() {
        return chef;
    }

    public void setChef(User chef) {
        this.chef = chef;
    }

    public double getFrais() {
        return this.frais;
    }

    public void setFrais(float frais) {
        this.frais = frais;
    }

    public String getImages_url() {
        return images_url;
    }

    public void setImages_url(String images_url) {
        this.images_url = images_url;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
