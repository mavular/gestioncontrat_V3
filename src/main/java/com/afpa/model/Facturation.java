package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "facturation")
@NamedQueries({
        @NamedQuery(name="Facturation.findAll", query = "SELECT f FROM Facturation f"),
        @NamedQuery(name="Facturation.findbynumero", query = "SELECT f FROM Facturation f WHERE f.numero = :numero"),
        @NamedQuery(name="Facturation.findBylibelle", query = "SELECT f FROM Facturation f WHERE f.libelle = :libelle"),
        @NamedQuery(name="Facturation.findbyemeteur", query = "SELECT f FROM Facturation f WHERE f.emeteur = :emeteur"),
        @NamedQuery(name="Facturation.findbyrecepteur", query = "SELECT f FROM Facturation f WHERE f.recepteur = :recepteur"),
        @NamedQuery(name="Facturation.findbydatefacture", query = "SELECT f FROM Facturation f WHERE f.datefacture = :datefacture"),
        @NamedQuery(name="Facturation.findByecheance", query = "SELECT f FROM Facturation f WHERE f.echeance = :echeance"),
        @NamedQuery(name="Facturation.findbymodalites", query = "SELECT f FROM Facturation f WHERE f.modalites = :modalites"),
        @NamedQuery(name="Facturation.findbyencaissement", query = "SELECT f FROM Facturation f WHERE f.encaissement = :encaissement")

})

public class Facturation implements Serializable{
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="numero")
    private Integer numero;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "emeteur")
    private String emeteur;
    @Column(name = "recepteur")
    private String recepteur;
    @Column(name = "datefacture")
    private Date datefacture;
    @Column(name = "echeance")
    private Date echeance;
    @Column(name = "modalites")
    private String modalites;
    @Column(name = "encaissement")
    private String encaissement;

    public Facturation()
    {

    }

    public Facturation(Integer id, Integer numero, String libelle, String emeteur, String recepteur, Date datefacture, Date echeance, String modalites, String encaissement) {
        this.id = id;
        this.numero = numero;
        this.libelle = libelle;
        this.emeteur = emeteur;
        this.recepteur = recepteur;
        this.datefacture = datefacture;
        this.echeance = echeance;
        this.modalites = modalites;
        this.encaissement = encaissement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEmeteur() {
        return emeteur;
    }

    public void setEmeteur(String emeteur) {
        this.emeteur = emeteur;
    }

    public String getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(String recepteur) {
        this.recepteur = recepteur;
    }

    public Date getDatefacture() {
        return datefacture;
    }

    public void setDatefacture(Date datefacture) {
        this.datefacture = datefacture;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public String getModalites() {
        return modalites;
    }

    public void setModalites(String modalites) {
        this.modalites = modalites;
    }

    public String getEncaissement() {
        return encaissement;
    }

    public void setEncaissement(String encaissement) {
        this.encaissement = encaissement;
    }
}
