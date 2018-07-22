package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "produit")
@NamedQueries({
        @NamedQuery(name="Produit.findAll", query = "SELECT p FROM Produit p"),
        @NamedQuery(name="Produit.findBynom", query = "SELECT p FROM Produit p WHERE p.nom = :nom"),
        @NamedQuery(name="Produit.findBydescription", query = "SELECT p FROM Produit p WHERE p.description = :description"),
        @NamedQuery(name="Produit.findByquantite", query = "SELECT p FROM Produit p WHERE p.quantite = :quantite"),
        @NamedQuery(name="Produit.findBydevise", query = "SELECT p FROM Produit p WHERE p.devise = :devise"),
        @NamedQuery(name="Produit.findByprixunitaire", query = "SELECT p FROM Produit p WHERE p.prixunitaire = :prixunitaire"),
        @NamedQuery(name="Produit.findBytva", query = "SELECT p FROM Produit p WHERE p.tva = :tva")

})
public class Produit implements Serializable{
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="nom")
    private String nom;
    @Column(name = "description")
    private String description;
    @Column(name = "quantite")
    private Integer quantite;
    @Column(name = "devise")
    private String devise;
    @Column(name = "prixunitaire")
    private BigDecimal prixunitaire;
    @Column(name = "tva")
    private BigDecimal tva;

    public Produit()
    {

    }

    public Produit(Integer id, String nom, String description, Integer quantite, String devise, BigDecimal prixunitaire, BigDecimal tva) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.devise = devise;
        this.prixunitaire = prixunitaire;
        this.tva = tva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public BigDecimal getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(BigDecimal prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public BigDecimal getTva() {
        return tva;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }
}
