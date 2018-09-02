package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "avenant")
@NamedQueries({
        @NamedQuery(name = "Avenant.findAll",query = "select a from Avenant a"),
        @NamedQuery(name = "Avenant.findBylibelle",query = "select a from Avenant a where a.modalites=:modalites"),
        @NamedQuery(name = "Avenant.findBydateavenant",query = "select a from Avenant a where a.dateavenant=:dateavenant"),
        @NamedQuery(name = "Avenant.findBycontrat",query = "select a from Avenant a where a.contrat=:contrat")
})
public class Avenant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "modalites")
    private String modalites;
    @Column(name = "dateavenant")
    private Date dateavenant;
    @ManyToOne
    @JoinColumn(name="numero")
    private Contrat contrat;

    public Avenant()
    {

    }

    public Avenant(Integer id, String modalites, Date dateavenant, Contrat contrat) {
        this.id = id;
        this.modalites = modalites;
        this.dateavenant = dateavenant;
        this.contrat = contrat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModalites() {
        return modalites;
    }

    public void setModalites(String modalites) {
        this.modalites = modalites;
    }

    public Date getDateavenant() {
        return dateavenant;
    }

    public void setDateavenant(Date dateavenant) {
        this.dateavenant = dateavenant;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
