package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "date")
@NamedQueries({
        @NamedQuery(name = "Datee.findAll",query = "select d from Datee d"),
        @NamedQuery(name="Datee.findAllContratForDatededebut", query = "select c FROM Contrat c WHERE c.datededebut =:datededebut"),
        @NamedQuery(name="Datee.findAllContratForDatedefin", query = "select c FROM Contrat c WHERE c.datedefin =:datedefin"),
        @NamedQuery(name="Datee.findAllContratForDatedesignature", query = "select c FROM Contrat c WHERE c.datedesignature =:datedesignature"),
        @NamedQuery(name="Datee.findAllDevisForDatedevis", query = "select d FROM Devis d WHERE d.datedevis =:datedevis"),
        @NamedQuery(name="Datee.findAllFactureForDatefacture", query = "select f FROM Facture f WHERE f.datefacture =:datefacture")

})


public class Datee implements Serializable {
    @Id
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "datededebut")
    private List<Contrat> listcontratsdebut;
    @OneToMany(mappedBy = "datedefin")
    private List<Contrat> listcontratsfinir ;
    @OneToMany(mappedBy = "datedesignature")
    private List<Contrat> listcontratssigner;
    @OneToMany(mappedBy = "datedevis")
    private List<Devis> listdevis ;
    @OneToMany(mappedBy = "datefacture")
    private List<Facture> listfactures;

    public Datee() {
    }

    public Datee(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Contrat> getListcontratsdebut() {
        return listcontratsdebut;
    }

    public void setListcontratsdebut(List<Contrat> listcontratsdebut) {
        this.listcontratsdebut = listcontratsdebut;
    }

    public List<Contrat> getListcontratsfinir() {
        return listcontratsfinir;
    }

    public void setListcontratsfinir(List<Contrat> listcontratsfinir) {
        this.listcontratsfinir = listcontratsfinir;
    }

    public List<Contrat> getListcontratssigner() {
        return listcontratssigner;
    }

    public void setListcontratssigner(List<Contrat> listcontratssigner) {
        this.listcontratssigner = listcontratssigner;
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
}
