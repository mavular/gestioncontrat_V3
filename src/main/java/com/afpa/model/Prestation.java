package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "prestation")
@NamedQueries({
        @NamedQuery(name="Prestation.findAll", query = "SELECT p FROM Prestation p"),
        @NamedQuery(name="Prestation.findByactivite", query = "SELECT p FROM Prestation p WHERE p.activite = :activite"),
        @NamedQuery(name="Prestation.findBydescription", query = "SELECT p FROM Prestation p WHERE p.description = :description"),
        @NamedQuery(name="Prestation.findBydesignation", query = "SELECT p FROM Prestation p WHERE p.designation = :designation"),
        @NamedQuery(name="Prestation.findBytva", query = "SELECT p FROM Prestation p WHERE p.tva = :tva"),
        @NamedQuery(name="Prestation.findByprixh", query = "SELECT p FROM Prestation p WHERE p.prixh = :prixh"),
        @NamedQuery(name="Prestation.findByheuretotale", query = "SELECT p FROM Prestation p WHERE p.heuretotale = :heuretotale")

})
public class Prestation implements Serializable {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="activite")
    private String activite;
    @Column(name = "description")
    private String description;
    @Column(name = "designation")
    private String designation;
    @Column(name = "tva")
    private BigDecimal tva;
    @Column(name = "prixh")
    private BigDecimal prixh;
    @Column(name = "Heuretotale")
    private Integer heuretotale;

    public Prestation()
    {

    }

    public Prestation(Integer id, String activite, String description, String designation, BigDecimal tva, BigDecimal prixh, Integer heuretotale) {
        this.id = id;
        this.activite = activite;
        this.description = description;
        this.designation = designation;
        this.tva = tva;
        this.prixh = prixh;
        this.heuretotale = heuretotale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getTva() {
        return tva;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }

    public BigDecimal getPrixh() {
        return prixh;
    }

    public void setPrixh(BigDecimal prixh) {
        this.prixh = prixh;
    }

    public Integer getHeuretotale() {
        return heuretotale;
    }

    public void setHeuretotale(Integer heuretotale) {
        this.heuretotale = heuretotale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestation)) {
            return false;
        }
        Prestation other = (Prestation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return this.designation;
    }
}
