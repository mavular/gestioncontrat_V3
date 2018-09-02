package com.afpa.model;

import com.afpa.model.interfaces.Contratinterf;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="contrat")
@NamedQueries({
        @NamedQuery(name="Contrat.findAll", query = "SELECT c FROM Contrat c"),
        @NamedQuery(name="Contrat.findBynumero", query = "SELECT c FROM Contrat c WHERE c.numero = :numero"),
        @NamedQuery(name="Contrat.findBylibelle", query = "SELECT c FROM Contrat c WHERE c.libelle = :libelle"),
        @NamedQuery(name="Contrat.findBydescription", query = "SELECT c FROM Contrat c WHERE c.description = :description"),
        @NamedQuery(name="Contrat.findBydatededebut", query = "SELECT c FROM Contrat c WHERE c.datededebut = :datededebut"),
        @NamedQuery(name="Contrat.findBydatedefin", query = "SELECT c FROM Contrat c WHERE c.datedefin = :datedefin"),
        @NamedQuery(name="Contrat.findBydatedesignature", query = "SELECT c FROM Contrat c WHERE c.datedesignature = :datedesignature"),
        @NamedQuery(name="Contrat.findAllContratByUser", query = "SELECT c FROM Contrat c WHERE c.utilisateur = :utilisateur"),
        @NamedQuery(name="Contrat.findAllContratByPrestataire", query = "SELECT c FROM Contrat c WHERE c.prestataire = :prestataire"),
        @NamedQuery(name="Contrat.findAllContratByClient", query = "SELECT c FROM Contrat c WHERE c.client = :client"),
        @NamedQuery(name="Contrat.findAllContratByTypecontrat", query = "SELECT c FROM Contrat c WHERE c.typecontrat = :typecontrat"),
        @NamedQuery(name="Contrat.findAllAvenantForContrat", query = "select a FROM Avenant a WHERE a.contrat =:contrat")
})

public class Contrat implements Contratinterf, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "date")
    private Datee datededebut;
    @ManyToOne
    @JoinColumn(name = "date_finir")
    private Datee datedefin;
    @ManyToOne
    @JoinColumn(name = "date_approuver")
    private Datee datedesignature;
    @ManyToOne
    @JoinColumn(name = "id")
    private Typecontrat typecontrat;
    @ManyToOne
    @JoinColumn(name = "pseudo")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise prestataire;
    @ManyToOne
    @JoinColumn(name = "id_entreprise_concerner")
    private Entreprise client;
    @OneToMany(mappedBy = "contrat")
    private List<Avenant> listavenant;


    public Contrat() {

    }

    public Contrat(String libelle, String description, Datee datededebut, Datee datedefin, Datee datedesignature, Entreprise prestataire, Entreprise client) {
        this.libelle = libelle;
        this.description = description;
        this.datededebut = datededebut;
        this.datedefin = datedefin;
        this.datedesignature = datedesignature;
        this.prestataire = prestataire;
        this.client = client;
    }

    @Override
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
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

    public Datee getDatededebut() {
        return datededebut;
    }

    public void setDatededebut(Datee datededebut) {
        this.datededebut = datededebut;
    }

    public Datee getDatedefin() {
        return datedefin;
    }

    public void setDatedefin(Datee datedefin) {
        this.datedefin = datedefin;
    }

    public Datee getDatedesignature() {
        return datedesignature;
    }

    public void setDatedesignature(Datee datedesignature) {
        this.datedesignature = datedesignature;
    }

    public Typecontrat getTypecontrat() {
        return typecontrat;
    }

    public void setTypecontrat(Typecontrat typecontrat) {
        this.typecontrat = typecontrat;
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

    public List<Avenant> getListavenant() {
        return listavenant;
    }

    public void setListavenant(List<Avenant> listavenant) {
        this.listavenant = listavenant;
    }

}
