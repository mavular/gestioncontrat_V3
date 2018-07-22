package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Typecontrat.findAll",query = "select t from Typecontrat t"),
        @NamedQuery(name = "Typecontrat.findAllbylibelle",query = "select t from Typecontrat t where t.libelle=:libelle")
})
public class Typecontrat implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "libelle")
    private String libelle;
    @Column(name = "descriptif")
    private String descriptif;
    @Column(name = "modalites")
    private String modalites;
    @OneToMany(mappedBy = "typecontrat")
    private List<Contrat> listdecontrat;

    public Typecontrat()
    {

    }

    public Typecontrat(String libelle, String descriptif, String modalites, List<Contrat> listdecontrat) {
        this.libelle = libelle;
        this.descriptif = descriptif;
        this.modalites = modalites;
        this.listdecontrat = listdecontrat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public List<Contrat> getListdecontrat() {
        return listdecontrat;
    }

    public void setListdecontrat(List<Contrat> listdecontrat) {
        this.listdecontrat = listdecontrat;
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
}
