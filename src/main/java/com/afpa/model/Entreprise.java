package com.afpa.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "entreprise")
@NamedQueries({
        @NamedQuery(name="Entreprise.findAll", query = "SELECT d FROM Devis d"),
        @NamedQuery(name="Entreprise.findBynom", query = "SELECT d FROM Devis d WHERE d.numero = :numero"),
        @NamedQuery(name="Entreprise.findBysiret", query = "SELECT d FROM Devis d WHERE d.libelle = :libelle"),
        @NamedQuery(name="Entreprise.findByqualification", query = "SELECT d FROM Devis d WHERE d.emeteur = :emeteur"),
        @NamedQuery(name="Entreprise.findByadresse", query = "SELECT d FROM Devis d WHERE d.recepteur = :recepteur"),
        @NamedQuery(name="Entreprise.findBytelephone", query = "SELECT d FROM Devis d WHERE d.prestation = :prestation"),
        @NamedQuery(name="Entreprise.findByemail", query = "SELECT d FROM Devis d WHERE d.datedevis = :datedevis")


})
public class Entreprise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$", message = "Le nom doit comporter que des caractères")
    private String nom;
    @Column(name="siret")
    private Integer siret;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "telephone")
    private Integer telephone;
    @Column(name = "email")
    private String email;

    public Entreprise()
    {

    }

    public Entreprise(Integer id, String nom, Integer siret, String qualification, String adresse, Integer telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.siret = siret;
        this.qualification = qualification;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
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

    public Integer getSiret() {
        return siret;
    }

    public void setSiret(Integer siret) {
        this.siret = siret;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return nom;
    }
}
