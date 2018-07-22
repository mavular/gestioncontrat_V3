package com.afpa.control;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
//@ManagedBean
public class PanelWiew implements Serializable{
    private String titre;
    private String bouton;
    private List<String> images;

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
        images.add("artisant" + i + ".png");
        }
    }
    public List<String> getImages() {
        return images;
    }

    public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fenêtre Fermée", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " Réduit", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }



}

