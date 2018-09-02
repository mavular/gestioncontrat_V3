package com.afpa.model;

import com.afpa.model.interfaces.User;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="utilisateur")
@NamedQueries({
        @NamedQuery(name="Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
        @NamedQuery(name="Utilisateur.findByPseudo", query = "SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo"),
        @NamedQuery(name="Utilisateur.findByMotdepasse", query = "SELECT u FROM Utilisateur u WHERE u.motdepasse = :motdepasse"),
        @NamedQuery(name="Utilisateur.findAllContratForUser", query = "select c FROM Contrat c WHERE c.utilisateur = :utilisateur"),
        @NamedQuery(name="Utilisateur.findAllFactureForUser", query = "select f FROM Facture f WHERE f.utilisateur = :utilisateur"),
        @NamedQuery(name="Utilisateur.findAllDevisForUser", query = "select d FROM Devis d WHERE d.utilisateur = :utilisateur")
})

public class Utilisateur implements User, Serializable
{
    @Id
    private String pseudo;

    @Column(name="motdepasse")
    private String motdepasse;
    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$", message = "Le nom doit comporter que des caractères")
    private String nom;
    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$", message = "Le nom doit comporter que des caractères")
    private String prenom;
    @Column(name = "telephone")
    private Integer telephone;
    @Column(name = "email")
    private String email;
    @Column(name = "datedenaissance")
    private Date datedenaissance;
    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$",
            message = "Le profil doit comporter que des caractères")
    @Column(name = "role")
    private String role;
    @Column(name = "numerorue")
    private String numerorue;
    @Column(name = "cp")
    private String cp;
    @Column(name = "ville")
    private String ville;
    @Column(name = "pays")
    private String pays;
    @OneToMany (mappedBy = "utilisateur")
    private List<Contrat> listcontrats;
    @OneToMany (mappedBy = "utilisateur")
    private List<Devis> listdevis;
    @OneToMany (mappedBy = "utilisateur")
    private List<Facture> listfactures;

    public Utilisateur()
    {

    }

    public Utilisateur(String pseudo, String motdepasse, String nom)
    {
        this.pseudo=pseudo;
        this.motdepasse=motdepasse;
        this.nom=nom;

    }

    public Utilisateur(String pseudo, String motdepasse, String nom, String prenom, Integer telephone, String email, Date datedenaissance, String role, String numerorue, String cp, String ville, String pays) {
        this.pseudo = pseudo;
        this.motdepasse = motdepasse;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.datedenaissance = datedenaissance;
        this.role = role;
        this.numerorue = numerorue;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNumerorue() {
        return numerorue;
    }

    public void setNumerorue(String numerorue) {
        this.numerorue = numerorue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse=motdepasse;
    }

    public List<Contrat> getListcontrats() {
        return listcontrats;
    }

    public void setListcontrats(List<Contrat> listcontrats) {
        this.listcontrats = listcontrats;
    }

    public List<Devis> getListdevis() {
        return listdevis;
    }

    public void setListdevis(List<Devis> listdevis) {
        this.listdevis = listdevis;
    }

    public List<Facture> getListfactures() {
        return listfactures;
    }

    public void setListfactures(List<Facture> listfactures) {
        this.listfactures = listfactures;
    }

    @Override
    public String toString() {
        return pseudo;
    }
}
