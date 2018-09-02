package com.afpa.control;

import com.afpa.access.AccessDb;
import com.afpa.model.*;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class TopControl implements Serializable {

    @Inject ControlUtilLogin controlUtilLogin;
    @Inject
    private AccessDb adb;
    private Produit produit;
    private List<Produit> listproduits;
    private Devis devis;
    private List<Devis> listdevis;
    private Contrat contrat;
    private List<Contrat> listcontrats;
    private Facture facture;
    private List<Facture> listfactures;
    private Utilisateur u;
    private List<Utilisateur> listutilisateurs;
    private Entreprise entreprise;
    private List<Entreprise> listentreprises;
    private String connectlabel; //label du bouton connect
    private String message; //le message de connexion ou de deconnexion


    public TopControl() {
        this.adb = adb;
        u = new Utilisateur();
        produit=new Produit();
        listproduits=new ArrayList<>();
        contrat = new Contrat();
        listcontrats=new ArrayList<>();
        devis = new Devis();
        listdevis=new ArrayList<>();
        facture = new Facture();
        listfactures=new ArrayList<>();
        connectlabel = "Se connecter";
        message = "Non Connecté";
    }
    @PostConstruct
    public void init() {

    }

    // vers la page utilisateur
    public String toCreateUtilisateur() {
        FacesContext context = FacesContext.getCurrentInstance(); //gestion des messages
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.getUserPrincipal() == null) {
            context.addMessage(null, new FacesMessage("Vous devez vous connecter"));
            return "seconnecter";
        }
        return "sinscrire";
    }

    /*le code de test des données de login*/
    public void logIn() {
        FacesContext context = FacesContext.getCurrentInstance(); //envoi de message
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); //recuperation de la requete


        try {
            //on va marquée la requête avec le login et le motdepasse
            //et va tester leur validité dans le realm jdbc
            //ne pas cryper le  mot de passe login le fera pour vous
            request.login(u.getPseudo(), u.getMotdepasse()); //test si login et mot de passe sont ok
            context.addMessage(null, new FacesMessage("You are welcome")); //le log est ok pas d'exceptions
            u.setMotdepasse(""); //je ne garde pas le mot de passe en clair dans la mémoire du serveur
            message = "connecté en tant que " + u.getPseudo();
            connectlabel = "Se déconnecter"; // le bouton connecter devient déconecter

        } catch (ServletException ex) {

            context.addMessage(null, new FacesMessage("Wrong Credentials")); //si exception le log n'est pas ok
        }
    }


    //*faire apparaitre la p dialog de login ou de deconnecter un utilisateur
    public void show() {
        FacesContext context = FacesContext.getCurrentInstance(); //recuperation en vue de l'envoi de message

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); //recuperation de la requete http sous forme d'objet
        if (request.getUserPrincipal() == null) //test si l'utilisateur n'est pas authentifié
        {

            System.out.println("Show");
            RequestContext.getCurrentInstance().execute("PF('dlg1').show()"); //affiche la pop up de login

        } else //authentifié
        {
            System.out.println("Deconnexion");
            try {
                request.logout(); //deconnection on enlève les informations dans la request
            } catch (ServletException ex) {
                Logger.getLogger(TopControl.class.getName()).log(Level.SEVERE, null, ex);
                context.addMessage(null, new FacesMessage("Erreur Logout"));

            }

            request.getSession().invalidate();//invalider la session dans ce cas fin de vie du bean
            try {
                //but rafraichir la page complete et retrouver un nouveau bean
                context.getExternalContext().redirect("accueil.xhtml"); //redirection avec un requête http pour la génération d'un nouveau bean
            } catch (IOException ex) {
                Logger.getLogger(TopControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Produit> getListproduits() {
        listproduits=adb.getAllProduit();
        return listproduits;
    }

    public void setListproduits(List<Produit> listproduits) {
        this.listproduits = listproduits;
    }

    public List<Devis> getListdevis() {
       // produit=adb.recherche(Produit.class ,"pseudo");
        listdevis=adb.getAllDevis();
        return listdevis;
    }

    public void setListdevis(List<Devis> listdevis) {
        this.listdevis = listdevis;
    }

    public List<Contrat> getListcontrats() {
        listcontrats =  adb.getAllContrat();
        return listcontrats;

    }

    public void setListcontrats(List<Contrat> listcontrats) {
        this.listcontrats = listcontrats;
    }

    public List<Facture> getListfactures() {
        adb.getAllFacture();
        return listfactures;
    }

    public void setListfactures(List<Facture> listfactures) {
        this.listfactures = listfactures;
    }

    public List<Utilisateur> getListutilisateurs() {
        adb.getAllUtilisateur();
        return listutilisateurs;
    }

    public void setListutilisateurs(List<Utilisateur> listutilisateurs) {
        this.listutilisateurs = listutilisateurs;
    }

    public List<Entreprise> getListentreprises() {
        adb.getAllEntreprise();
        return listentreprises;
    }

    public void setListentreprises(List<Entreprise> listentreprises) {
        this.listentreprises = listentreprises;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public String getConnectlabel() {
        return connectlabel;
    }

    public void setConnectlabel(String connectlabel) {
        this.connectlabel = connectlabel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AccessDb getAdb() {
        return adb;
    }

    public void setAdb(AccessDb adb) {
        this.adb = adb;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
}

/*
//passage de commande
public String sauverpanier()
{
    FacesContext context=FacesContext.getCurrentInstance(); //gestion des 		messages
    HttpServletRequest request=(HttpServletRequest) 			context.getExternalContext().getRequest();
    if(request.getUserPrincipal()!=null) //test si l'utilisateur est loggé
    {
        if(!panier.isEmpty()) //si le panier n'est pas vide
        {
            //sauvegarde en base de commande

            //sauvegarde la date en base
            Date d=new Date(); //instanciation de l'entité bean
            java.util.Date datecommande=new java.util.Date(); //date de commande 		affectation de la date systeme
            d.setDate(datecommande); //set la date de l'entite avec la date du 		jour
            mda.maj(d); //mise à jour de la date(d) en base car relation 1 à n


            //Sauvegarde Commande

            commande.setDatecde(d); //set la date notion de clé étrangère
            commande.setPseudou(u);
            try {
                mda.persist(commande); //persist en base +car 						autoincrement
            } catch (SystemException e) {
                e.printStackTrace();
            }

            //gestion des lignes commandes

            for(Lignecommande lc:panier) //parcours le panier
            {
                lc.setIdcommande(commande);

                //si persist ok on ajoute dans la 				listeString.valueOf(u.getPseudo().hashCode())
                try {
                    mda.persist(lc); //si le persist en base est ok  renvoi 				true
                } catch (SystemException e) {
                    e.printStackTrace();
                }
                {
                    commande.getLignecommandeList().add(lc); //on ajoute les lignes dans 		la commande
                }
                try {
                    mda.persist(lc);
                } catch (SystemException e) {
                    e.printStackTrace();
                }
            }
            return "/protected/commande"; // redirige vers commande.xhtml
        }
        else
        {
            context.addMessage(null, new FacesMessage("Validation impossible le panier est vide"));
            return "index";
        }
    }
    else
    {
        context.addMessage(null, new FacesMessage("Validation impossible vous n'êtes pas connecté"));
        return "index";
    }
}
*/