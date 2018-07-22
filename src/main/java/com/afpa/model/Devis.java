package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "devis")
@NamedQueries({
        @NamedQuery(name="Devis.findAll", query = "SELECT d FROM Devis d"),
        @NamedQuery(name="Devis.findBynumero", query = "SELECT d FROM Devis d WHERE d.numero = :numero"),
        @NamedQuery(name="Devis.findBylibelle", query = "SELECT d FROM Devis d WHERE d.libelle = :libelle"),
        @NamedQuery(name="Devis.findByemeteur", query = "SELECT d FROM Devis d WHERE d.emeteur = :emeteur"),
        @NamedQuery(name="Devis.findByrecepteur", query = "SELECT d FROM Devis d WHERE d.recepteur = :recepteur"),
        @NamedQuery(name="Devis.findByprestation", query = "SELECT d FROM Devis d WHERE d.prestation = :prestation"),
        @NamedQuery(name="Devis.findBydatedevis", query = "SELECT d FROM Devis d WHERE d.datedevis = :datedevis")

})
public class Devis implements Serializable {
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
    @Column(name = "prestation")
    private String prestation;
    @Column(name = "datedevis")
    private Date datedevis;

    public Devis()
    {
    }

    public Devis(Integer id, Integer numero, String libelle, String emeteur, String recepteur, String prestation, Date datedevis) {
        this.id = id;
        this.numero = numero;
        this.libelle = libelle;
        this.emeteur = emeteur;
        this.recepteur = recepteur;
        this.prestation = prestation;
        this.datedevis = datedevis;
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

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }

    public Date getDatedevis() {
        return datedevis;
    }

    public void setDatedevis(Date datedevis) {
        this.datedevis = datedevis;
    }
}
