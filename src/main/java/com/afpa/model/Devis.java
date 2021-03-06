package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "devis")
@NamedQueries({
        @NamedQuery(name="Devis.findAll", query = "SELECT d FROM Devis d"),
        @NamedQuery(name="Devis.findBynumero", query = "SELECT d FROM Devis d WHERE d.numero = :numero"),
        @NamedQuery(name="Devis.findBylibelle", query = "SELECT d FROM Devis d WHERE d.libelle = :libelle"),
        @NamedQuery(name="Devis.findBydescription", query = "SELECT d FROM Devis d WHERE d.description = :description"),
        @NamedQuery(name="Devis.findBydatedevis", query = "SELECT d FROM Devis d WHERE d.datedevis = :datedevis"),
        @NamedQuery(name="Devis.findByUser", query = "SELECT d FROM Devis d WHERE d.utilisateur = :utilisateur"),
        @NamedQuery(name="Devis.findByPrestataire", query = "SELECT d FROM Devis d WHERE d.prestataire = :prestataire")

})
public class Devis implements Serializable {
    @Id
    @ GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer numero;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "date")
    private Datee datedevis;
    @ManyToOne
    @JoinColumn(name = "pseudo")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id")
    private Entreprise prestataire;
    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise client;
    @ManyToMany(mappedBy = "lesdevis")
    private List<Produit> lesprduits;
    public Devis()
    {
    }

    public Devis(String libelle, String description) {
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

    public Datee getDatedevis() {
        return datedevis;
    }

    public void setDatedevis(Datee datedevis) {
        this.datedevis = datedevis;
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
}
