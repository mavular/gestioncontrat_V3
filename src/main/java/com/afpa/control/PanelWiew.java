package com.afpa.control;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
//@ManagedBean
public class PanelWiew implements Serializable{
    private String titre;
    private String bouton;


        public void onClose(CloseEvent event) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        public void onToggle(ToggleEvent event) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }



}

