package com.afpa.presenter;

import com.afpa.access.AccessDb;
import com.afpa.model.Utilisateur;
import com.google.common.hash.Hashing;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
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
        this.u.setMotdepasse(this.mdp);

        if( adb.persist(u)){
            msg = new FacesMessage("BRAVO", " L'Utilisateur " + u.getPseudo() + " est inscrit");
        } else {
            msg = new FacesMessage("ERREUR", "Erreur lors de la création de l'utilisateur");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public Utilisateur getUfromDatabase()
    {
        return adb.recherche(Utilisateur.class,((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getUserPrincipal().getName());
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
        this.mdp = Hashing.sha256()
                .hashString(mdp, StandardCharsets.UTF_8)
                .toString();
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

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }
}

