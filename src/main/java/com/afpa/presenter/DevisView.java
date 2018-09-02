package com.afpa.presenter;

import com.afpa.access.AccessDb;
import com.afpa.control.ControlUtilLogin;
import com.afpa.model.*;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

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
public class DevisView implements Serializable{

    @Inject
    private AccessDb adb;
    @Inject
    private ControlUtilLogin controlUtilLogin;
    private Devis devis;
    private List<Devis> listdevis;
    private Facture facture;
    private List<Facture> listfactures;
    private Produit produit;
    private List<Produit> listproduits;
    private Datee datedevis;
    private String devise;

    private float total;
    private float totalhorstaxe;
    private float pourcentage;
    private float remise;
    private float tva;
    private float totalttc;


    public DevisView()
    {
    }

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
        listproduits = new ArrayList<>();
        produit = new Produit();
        devis=new Devis();
        devis.setClient(new Entreprise());
        devis.setPrestataire(new Entreprise());
        devis.setUtilisateur(controlUtilLogin.getU());
        listdevis=new ArrayList<>();
        facture = new Facture();
        facture.setClient(new Entreprise());
        this.datedevis = new Datee();
        facture.setPrestataire(new Entreprise());
        facture.setUtilisateur(controlUtilLogin.getU());
        listfactures = new ArrayList<>();

        tva=this.calculTva();
        total=this.calculTotal();
        remise=this.calculRemise();
        totalhorstaxe=this.calculTotalHt();
        totalttc=this.calculTotalttc();


    }

    public void remplirProduit(){
        adb.persist(produit);
        listproduits.add(produit);
        produit = new Produit();
    }

    public void effacerList(){
        listproduits=new ArrayList<Produit>();
        listproduits.clear();
    }

   /* public void remplirDevis(){
        adb.persist(devis);
        listdevis.add(devis);
        devis = new Devis();
    }*/

    public Produit addProduit(Produit p){
        if (listproduits.contains(p)) {
            adb.maj(p);
            return p;
        }
        adb.persist(p);
        listproduits.add(p);
        return p;
    }

    public List<Facture> getListfactures() {
        listfactures= adb.getAllFactureForUser(controlUtilLogin.getU());
        return listfactures;
    }

    public void setListfactures(List<Facture> listfactures) {
        this.listfactures = listfactures;
    }

    public void addDevis()
    {
        adb.persist(this.datedevis);
       if (listdevis.contains(this.devis)) {
            adb.maj(this.devis);
           adb.maj(this.facture);

        }
        adb.persist(this.devis);
        adb.persist(this.facture);
        FacesMessage msg;
        if( adb.persist(this.devis)){
            msg = new FacesMessage("Réussite", "Mr " + devis.getUtilisateur().getPseudo() + " , vous avez créé un devis");
        } else {
            msg = new FacesMessage("Erreur", "Erreur de création du devis, VEUILLEZ REPRENDRE");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        listdevis.add(this.devis);
        listfactures.add(this.facture);

    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public ControlUtilLogin getControlUtilLogin() {
        return controlUtilLogin;
    }

    public void setControlUtilLogin(ControlUtilLogin controlUtilLogin) {
        this.controlUtilLogin = controlUtilLogin;
    }

    public Datee getDatedevis() {
        return datedevis;
    }

    public void setDatedevis(Datee datedevis) {
        this.datedevis = datedevis;
    }

    public List<Devis> getListcontrats() {
        listdevis= adb.getAllDevisForUser(controlUtilLogin.getU());
        return listdevis;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Devis getDevis() { return devis; }

    public void setDevis(Devis devis) { this.devis = devis; }

    public List<Devis> getListdevis() {
        listdevis=adb.getAllDevis();
        return listdevis;
    }

    public void setListdevis(List<Devis> listdevis) {
        this.listdevis = listdevis;
    }

   public List<Produit> getListproduits() {
        listproduits=adb.getAllProduit();
        return listproduits;
    }

    public void setListproduits(List<Produit> listproduits) {
        this.listproduits = listproduits;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public AccessDb getAdb() {
        return adb;
    }

    public void setAdb(AccessDb adb) {
        this.adb = adb;
    }

    public float getTotalhorstaxe() {
        return totalhorstaxe;
    }

    public void setTotalhorstaxe(float totalhorstaxe) {
        this.totalhorstaxe = totalhorstaxe;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public float getTotalttc() {
        return totalttc;
    }

    public void setTotalttc(float totalttc) {
        this.totalttc = totalttc;
    }

    public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fenêtre Fermée", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " Réduit", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float calculTotal()
    {
        total=0;
        for(Produit p:listproduits)
            total = total + p.getPrixunitaire() * p.getQuantite();
        return total;
    }

    public float calculTotalHt()
    {
        total=0;
        for(Produit p:listproduits)
            total = total + p.getPrixunitaire() * p.getQuantite();
        totalhorstaxe=total-remise;
        return totalhorstaxe;
    }

    public float calculRemise()
    {
        total=0;
        remise=0;
        for(Produit p:listproduits)
        total = total + p.getPrixunitaire() * p.getQuantite();
        if (pourcentage>0) {
            remise = total * pourcentage / 100;
        }else {

        }
        return remise;
    }

    public float calculTva(){
        tva=0;
        for (Produit p:listproduits)
        tva = totalhorstaxe * p.getTva() /100;
        return tva;
    }

    public float calculTotalttc()
    {
        total=0;
        for(Produit p:listproduits)
            total = total + p.getPrixunitaire() * p.getQuantite();
        totalhorstaxe=total-remise;
        totalttc=totalhorstaxe+tva;
        return totalttc;
    }

}
/*
    public void handleKeyEvent() {
        text = text.toUpperCase();
    }
*/



