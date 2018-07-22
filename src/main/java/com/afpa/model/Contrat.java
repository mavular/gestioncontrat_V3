package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="contrat")
@NamedQueries({
        @NamedQuery(name="Contrat.findAll", query = "SELECT c FROM Contrat c"),
        @NamedQuery(name="Contrat.findBynumero", query = "SELECT c FROM Contrat c WHERE c.numero = :numero"),
        @NamedQuery(name="Contrat.findBylibelle", query = "SELECT c FROM Contrat c WHERE c.libelle = :libelle"),
        @NamedQuery(name="Contrat.findByprestataire", query = "SELECT c FROM Contrat c WHERE c.prestataire = :prestataire"),
        @NamedQuery(name="Contrat.findByclient", query = "SELECT c FROM Contrat c WHERE c.client = :client"),
        @NamedQuery(name="Contrat.findBytypecontrat", query = "SELECT c FROM Contrat c WHERE c.numero = :numero"),
        @NamedQuery(name="Contrat.findBydatededebut", query = "SELECT c FROM Contrat c WHERE c.datededebut = :datededebut"),
        @NamedQuery(name="Contrat.findBydatedefin", query = "SELECT c FROM Contrat c WHERE c.datedefin = :datedefin"),
        @NamedQuery(name="Contrat.findBydatedesignature", query = "SELECT c FROM Contrat c WHERE c.datedesignature = :datedesignature"),
        //@NamedQuery(name= "Utilisateur.findAllContratForUser", query = "select c FROM Contrat c WHERE c.utilisateur = :utilisateur")
        //@NamedQuery(name= "Utilisateur.findAllFactureForUser", query = "select f FROM Contrat f WHERE f.utilisateur = :utilisateur")
        //@NamedQuery(name= "Utilisateur.findAllDevisForUser", query = "select d FROM Contrat d WHERE d.utilisateur = :utilisateur")
})

public class Contrat implements Serializable{
    
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="numero")
    private Integer numero;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "prestataire")
    private String prestataire;
    @Column(name = "client")
    private String client;
    @Column(name = "descriptif")
    private String descriptif;
    @Column(name = "modalites")
    private String modalites;
    //@Column(name = "")
    //private String ;
    @ManyToOne
    @JoinColumn(name="id_typecontrat")
    private Typecontrat typecontrat;
    @ManyToOne
    @JoinColumn(name="date")
    private Date datededebut;
    @ManyToOne
    @JoinColumn(name="date_finir")
    private Date datedefin;
    @ManyToOne
    @JoinColumn(name="date_approuver")
    private Date datedesignature;
    @OneToMany(mappedBy = "contrat")
    private List<Avenant> listdesavenants;

    public Contrat() {

    }

    public Contrat(Integer id, Integer numero, String libelle, String prestataire, String client, String descriptif, String modalites, Typecontrat typecontrat, Date datededebut, Date datedefin, Date datedesignature) {
        this.id = id;
        this.numero = numero;
        this.libelle = libelle;
        this.prestataire = prestataire;
        this.client = client;
        this.descriptif = descriptif;
        this.modalites = modalites;
        this.typecontrat = typecontrat;
        this.datededebut = datededebut;
        this.datedefin = datedefin;
        this.datedesignature = datedesignature;
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

    public String getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(String prestataire) {
        this.prestataire = prestataire;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getModalites() {
        return modalites;
    }

    public void setModalites(String modalites) {
        this.modalites = modalites;
    }

    public Typecontrat getTypecontrat() {
        return typecontrat;
    }

    public void setTypecontrat(Typecontrat typecontrat) {
        this.typecontrat = typecontrat;
    }

    public Date getDatededebut() {
        return datededebut;
    }

    public void setDatededebut(Date datededebut) {
        this.datededebut = datededebut;
    }

    public Date getDatedefin() {
        return datedefin;
    }

    public void setDatedefin(Date datedefin) {
        this.datedefin = datedefin;
    }

    public Date getDatedesignature() {
        return datedesignature;
    }

    public void setDatedesignature(Date datedesignature) {
        this.datedesignature = datedesignature;
    }
}
