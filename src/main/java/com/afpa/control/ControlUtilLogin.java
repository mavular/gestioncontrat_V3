package com.afpa.control;

import com.afpa.access.AccessDb;
import com.afpa.model.Utilisateur;
import com.google.common.hash.Hashing;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Named
@SessionScoped
public class ControlUtilLogin implements Serializable
{

    @Inject
    private AccessDb adb;

    private String messages;
    private String connectlabel;
    private Utilisateur u;

    private String titre;
    private String bouton;

    public ControlUtilLogin()
    {
    }

    @PostConstruct
    public void init()
    {
        u=new Utilisateur();
        connectlabel = "Se connecter";
        messages = "Non Connecté";

    }

    public void ValidatePseudo(FacesContext context, UIComponent component,Object value) throws ValidatorException
    {
        if(adb.recherche(Utilisateur.class,value.toString())!=null)
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_INFO,"Erreur","Le pseudo est déjà utilisé"));
        }
    }
/*
    public void logIn()
    {
        FacesContext context=FacesContext.getCurrentInstance(); //envoi de message
        HttpServletRequest request=(HttpServletRequest) context.getExternalContext().getRequest(); //recuperation de la requete

        try
        {
            request.login(u.getPseudo(),u.getMotdepasse());
            context.addMessage(null, new FacesMessage("Bienvenue " + u.getPseudo()));
            u = adb.recherche(Utilisateur.class, u.getPseudo());

            u.setMotdepasse(""); //le mot de passe est remis à blanc une fois la connection faite

            messages="Connecté en tant que "+u.getPseudo(); //affiche le pseudo de l'utilisateur

            connectlabel="Se déconnecter"; //change le libellé du bouton lorsque l'utilisateur est connecter
        }
        catch (ServletException ex)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Le mot de passe n'est pas correct", ""));
        }
    }

    public void show()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request=(HttpServletRequest) context.getExternalContext().getRequest();
        if(request.getUserPrincipal()==null)
        {
            System.out.println("show");
            RequestContext.getCurrentInstance().execute("PF('dlg1').show()");
        }
        else
        {
            System.out.println("Deconnexion");
            try{
                request.logout();
            }
            catch (ServletException ex)
            {
                Logger.getLogger(ControlUtilLogin.class.getName()).log(Level.SEVERE, null, ex);
                context.addMessage(null, new FacesMessage("Erreur de Déconnexion"));
            }
            request.getSession().invalidate();
            try
            {
                context.getExternalContext().redirect("accueil.xhtml");
            }
            catch (IOException ex)
            {
                Logger.getLogger(ControlUtilLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
*/
   public void create() throws SystemException {
       FacesContext context=FacesContext.getCurrentInstance();

       if(!u.getPseudo().isEmpty()) {
           u.setMotdepasse(Hashing.sha256()
                   .hashString(u.getMotdepasse(), StandardCharsets.UTF_8)
                   .toString());
           adb.persist(u);
           context.addMessage("mygrowl", new FacesMessage("Succès", u.getPseudo() + " a bien été sauvegardé"));
       }



       else
           context.addMessage("mygrowl",new FacesMessage("Echec","Il y a des erreurs de validation"));
   }


    public AccessDb getAdb() {
        return adb;
    }

    public void setAdb(AccessDb adb) {
        this.adb = adb;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getConnectlabel() {
        return connectlabel;
    }

    public void setConnectlabel(String connectlabel) {
        this.connectlabel = connectlabel;
    }

    public Principal getCurrentUtilisateur(){
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getUserPrincipal();
    }


    public void save() {
        addMessage("Bienvenu"+u.getPseudo(), "Vous êtes enrégistré");
    }

    public void update() {
        addMessage("Success", "Donnée mise à jour");
    }

    public void delete() {
        addMessage("Success", "Donnée supprimée");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
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

/*
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

***********************
package org.primefaces.showcase.view.ajax;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class UserView {

    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + firstname + " " + lastname));
    }
}
 */