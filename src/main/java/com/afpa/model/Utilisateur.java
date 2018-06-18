package com.afpa.model;

public class Utilisateur {
}
/* package com.afpa.model;

import com.afpa.model.interfaces.User;
import com.google.common.hash.Hashing;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Entity
@Table(name="utilisateur")
@NamedQueries({
        @NamedQuery(name="Utilisateur.findbyPseudo", query = "SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo"),
        @NamedQuery(name = "Utilisateur.findByMotdepasse", query = "SELECT u FROM Utilisateur u WHERE u.motdepasse = :motdepasse"),
        @NamedQuery(name= "Utilisateur.findAllReservationsForUser", query = "select r FROM Reservation r WHERE r.utilisateur = :utilisateur")
})

public class Utilisateur implements User, Serializable
{
    @Id
    private String pseudo;

    //@OneToMany(mappedBy = "pseudo", cascade = CascadeType.ALL)
    //private List<Reservation> lesreservation;

    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$", message = "Le nom doit comporter que des caractères")
    private String nom;

    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$", message = "Le nom doit comporter que des caractères")
    private String prenom;

//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$",
//            message ="Le mot de passe doit comporter au minimum une majuscule, un chiffre et un caractère spécial")
    @Column(name="motdepasse")
    private String motdepasse;

    @Column(name="groupeSocial")
    private String groupeSocial;

    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$",
            message = "Le profil doit comporter que des caractères")
    private String profil;

    private String email;

    public Utilisateur()
    {}

    public Utilisateur(String pseudo, String nom, String prenom, String groupesocial, String profil, String email,String motdepasse)
    {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.profil = profil;
        this.email = email;
        this.groupeSocial = groupesocial;
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

    public String getMotdepasse() {
        return motdepasse;
    }

    public String getMdp1(){ return this.motdepasse;}

    public void setMdp1(String mdp1) {
        this.motdepasse = Hashing.sha256()
                .hashString(mdp1, StandardCharsets.UTF_8)
                .toString();
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupeSocial() {
        return groupeSocial;
    }

    public void setGroupeSocial(String groupesocial) {
        this.groupeSocial = groupesocial;
    }

    @Override
    public String toString() {
        return pseudo;
    }
}
 */