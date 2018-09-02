package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "typecontrat")
@NamedQueries({
        @NamedQuery(name = "Typecontrat.findAll",query = "select t from Typecontrat t"),
        @NamedQuery(name = "Typecontrat.findAllbylibelle",query = "select t from Typecontrat t where t.libelle=:libelle"),
        @NamedQuery(name = "Typecontrat.findAllContratForType", query = "select c FROM Contrat c WHERE c.typecontrat =:typecontrat")
})
public class Typecontrat implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(mappedBy = "typecontrat")
    private List<Contrat> listcontrats;

    public Typecontrat()
    {

    }

    public Typecontrat(String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Contrat> getListcontrats() {
        return listcontrats;
    }

    public void setListcontrats(List<Contrat> listcontrats) {
        this.listcontrats = listcontrats;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
