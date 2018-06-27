/*package com.afpa.control;

import com.afpa.access.AccessDb;
import com.afpa.model.Utilisateur;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.validator.ValidatorException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ControlUtilLogin implements Serializable{

    @Inject
    private AccessDb adb;

    private String messages;
    private String connectlabel;
    private Utilisateur u;

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
}
*/

