package com.afpa.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "representant")
@NamedQueries({
        @NamedQuery(name="Representant.findAll", query = "SELECT r FROM Representant r"),
        @NamedQuery(name="Representant.findBynom", query = "SELECT r FROM Representant r WHERE r.nom = :nom"),
        @NamedQuery(name="Representant.findByprenom", query = "SELECT r FROM Representant r WHERE r.prenom = :prenom"),
        @NamedQuery(name="Representant.findBytitre", query = "SELECT r FROM Representant r WHERE r.titre = :titre"),

})
public class Representant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$", message = "Le nom doit comporter que des caractères")
    private String nom;
    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$", message = "Le prénom doit comporter que des caractères")
    private String prenom;
    @Column(name = "titre")
    private String titre;

    public Representant()
    {

    }

    public Representant(String nom, String prenom, String titre) {
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
