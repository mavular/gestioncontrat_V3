package com.afpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="date")
@NamedQueries({@NamedQuery(name = "Date.findAll", query = "SELECT d FROM Date d")
})
public class Date implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private java.util.Date date;

    public Date()
    {

    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }
}
