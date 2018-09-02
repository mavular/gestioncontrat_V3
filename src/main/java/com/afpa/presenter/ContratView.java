package com.afpa.presenter;

import com.afpa.access.AccessDb;
import com.afpa.control.ControlUtilLogin;
import com.afpa.model.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
//@ManagedBean
    public class ContratView implements Serializable{
    @Inject
    private AccessDb adb;
    @Inject
    private ControlUtilLogin controlUtilLogin;
    private float capital;
    private String villederegistre;
    private String numeroderegistre;
    private float capitalClient;
    private String villederegistreClient;
    private String numeroderegistreClient;
    private float sommeforfaitaire;
    private float fraisparjourderetard;
    private String devise;
    private String languecontrat;

    private Datee datededebut;
    private Datee datedefin;
    private Datee datedesignature;
    private List<Datee> listdates;

    private Utilisateur utilisateur;
    private Contrat contrat;
    private List<Contrat>listcontrats;


    public ContratView(){

    }
    @PostConstruct
    public void init() {
        contrat = new Contrat();

        this.datededebut = new Datee();
        this.datedefin = new Datee();
        this.datedesignature = new Datee();
        contrat.setTypecontrat(new Typecontrat());
        contrat.setPrestataire(new Entreprise());
        contrat.setClient(new Entreprise());
        contrat.setUtilisateur(controlUtilLogin.getU());
        listcontrats=new ArrayList<>();
        listdates=new ArrayList<>();

    }

    public void addContrat()
    {
        adb.persist(this.datededebut);
        adb.persist(this.datedefin);
        adb.persist(this.datedesignature);

        if (listcontrats.contains(this.contrat)) {
            adb.maj(this.contrat);

        }
        adb.persist(this.contrat);
        FacesMessage msg;
        if( adb.persist(this.contrat)){
            msg = new FacesMessage("Réussite", "Mr " + contrat.getUtilisateur().getPseudo() + " , vous avez créé un contrat");
        } else {
            msg = new FacesMessage("Erreur", "Erreur de création du contrat, VEUILLEZ REPRENDRE");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        listcontrats.add(this.contrat);
    }

    public List<Datee> getListdates() {
        listdates=adb.getAllDatee();
        return listdates;
    }

    public void setListdates(List<Datee> listdates) {
        this.listdates = listdates;
    }

    public List<Contrat> getListcontrats() {
        listcontrats=adb.getAllContrat();
        return listcontrats;
    }

    public void setListcontrats(List<Contrat> listcontrats) {
        this.listcontrats = listcontrats;
    }

    public AccessDb getAdb() {
        return adb;
    }

    public void setAdb(AccessDb adb) {
        this.adb = adb;
    }

    public float getCapital() {
        return capital;
    }

    public void setCapital(float capital) {
        this.capital = capital;
    }

    public String getVillederegistre() {
        return villederegistre;
    }

    public void setVillederegistre(String villederegistre) {
        this.villederegistre = villederegistre;
    }

    public String getNumeroderegistre() {
        return numeroderegistre;
    }

    public void setNumeroderegistre(String numeroderegistre) {
        this.numeroderegistre = numeroderegistre;
    }

    public float getCapitalClient() {
        return capitalClient;
    }

    public void setCapitalClient(float capitalClient) {
        this.capitalClient = capitalClient;
    }

    public String getVillederegistreClient() {
        return villederegistreClient;
    }

    public void setVillederegistreClient(String villederegistreClient) {
        this.villederegistreClient = villederegistreClient;
    }

    public String getNumeroderegistreClient() {
        return numeroderegistreClient;
    }

    public void setNumeroderegistreClient(String numeroderegistreClient) {
        this.numeroderegistreClient = numeroderegistreClient;
    }

    public float getSommeforfaitaire() {
        return sommeforfaitaire;
    }

    public void setSommeforfaitaire(float sommeforfaitaire) {
        this.sommeforfaitaire = sommeforfaitaire;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public float getFraisparjourderetard() {
        return fraisparjourderetard;
    }
    public void setFraisparjourderetard(float fraisparjourderetard) {
        this.fraisparjourderetard = fraisparjourderetard;
    }
    public String getDevise() {
        return devise;
    }
    public void setDevise(String devise) {
        this.devise = devise;
    }
    public String getLanguecontrat() {
        return languecontrat;
    }
    public void setLanguecontrat(String languecontrat) {
        this.languecontrat = languecontrat;
    }
    public Datee getDatededebut() {
        return datededebut;
    }
    public void setDatededebut(Datee datededebut) {
        this.datededebut = datededebut;
    }
    public Datee getDatedefin() {
        return datedefin;
    }
    public void setDatedefin(Datee datedefin) {
        this.datedefin = datedefin;
    }
    public Datee getDatedesignature() {
        return datedesignature;
    }
    public void setDatedesignature(Datee datedesignature) {
        this.datedesignature = datedesignature;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ControlUtilLogin getControlUtilLogin() {
        return controlUtilLogin;
    }

    public void setControlUtilLogin(ControlUtilLogin controlUtilLogin) {
        this.controlUtilLogin = controlUtilLogin;
    }

}