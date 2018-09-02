package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "facture")
@NamedQueries({
        @NamedQuery(name="Facture.findAll", query = "SELECT f FROM Facture f"),
        @NamedQuery(name="Facture.findbynumero", query = "SELECT f FROM Facture f WHERE f.numero = :numero"),
        @NamedQuery(name="Facture.findBylibelle", query = "SELECT f FROM Facture f WHERE f.libelle = :libelle"),
        @NamedQuery(name="Facture.findBydescription", query = "SELECT f FROM Facture f WHERE f.description = :description"),
        @NamedQuery(name="Facture.findbydatefacture", query = "SELECT f FROM Facture f WHERE f.datefacture = :datefacture")

})

public class Facture implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer numero;
    @Column (name = "libelle")
    private String libelle;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "date")
    private Datee datefacture;
    @ManyToOne
    @JoinColumn(name = "pseudo")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id")
    private Entreprise prestataire;
    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise client;
    @ManyToMany(mappedBy = "lesfactures")
    private List<Produit> lesprduits;

    public Facture()
    {

    }

    public Facture(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Datee getDatefacture() {
        return datefacture;
    }

    public void setDatefacture(Datee datefacture) {
        this.datefacture = datefacture;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Entreprise getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Entreprise prestataire) {
        this.prestataire = prestataire;
    }

    public Entreprise getClient() {
        return client;
    }

    public void setClient(Entreprise client) {
        this.client = client;
    }

    public List<Produit> getLesprduits() {
        return lesprduits;
    }

    public void setLesprduits(List<Produit> lesprduits) {
        this.lesprduits = lesprduits;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
