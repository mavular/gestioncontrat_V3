package com.afpa.presenter;

import com.afpa.access.AccessDb;
import com.afpa.model.Utilisateur;
import org.primefaces.event.FlowEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class ServiceUtilisateur implements Serializable
{
    private Utilisateur u = new Utilisateur();
    private String pseudo;
    private String mdp;
    private boolean passer;

    @Inject
    private AccessDb adb;

    public ServiceUtilisateur()
    {

    }

    public void addUtilisateur()
    {
        FacesMessage msg;
        if( adb.persist(u)){
            msg = new FacesMessage("Successful", "Utilisateur " + u.getPseudo() + " créé correctement");
        } else {
            msg = new FacesMessage("Erreur", "Erreur lors de la création de l'utilisateur");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public String onFlowProcess(FlowEvent event)
    {
        if(passer) {
            passer = false;   //fait un reset dans le cas ou l'utilisateur fait un retour
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public Utilisateur getUfromDatabase()
    {
        return adb.recherche(Utilisateur.class,((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getUserPrincipal().getName());
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public boolean isPasser() {
        return passer;
    }

    public void setPasser(boolean passer) {
        this.passer = passer;
    }

    public AccessDb getAdb() {
        return adb;
    }

    public void setAdb(AccessDb adb) {
        this.adb = adb;
    }

    public Principal getCurrentUtilisateur()
    {
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getUserPrincipal();
    }
}

