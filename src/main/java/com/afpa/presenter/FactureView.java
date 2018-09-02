package com.afpa.presenter;
import com.afpa.access.AccessDb;
import com.afpa.control.ControlUtilLogin;
import com.afpa.model.Datee;
import com.afpa.model.Entreprise;
import com.afpa.model.Facture;
import com.afpa.model.Produit;
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
public class FactureView implements Serializable{
    @Inject
    private AccessDb adb;
    @Inject
    private ControlUtilLogin controlUtilLogin;
    private Facture facture;
    private List<Facture> listfactures;
    private Produit produit;
    private List<Produit> listproduits;
    private Datee datefacture;
    private String devise;
    private float total;
    private float totalhorstaxe;
    private float pourcentage;
    private float remise;
    private float tva;
    private float totalttc;
    public FactureView()
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
        facture = new Facture();

        facture.setClient(new Entreprise());
        facture.setPrestataire(new Entreprise());
        facture.setUtilisateur(controlUtilLogin.getU());
        listfactures = new ArrayList<>();
        this.datefacture = new Datee();

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
        listfactures=adb.getAllFacture();
        return listfactures;
    }

    public void setListfactures(List<Facture> listfactures) {
        this.listfactures = listfactures;
    }

    public List<Facture> getListfacturesByUser() {
        listfactures= adb.getAllFactureForUser(controlUtilLogin.getU());
        return listfactures;
    }

    public void addFacture()
    {
        adb.persist(this.datefacture);
        if (listfactures.contains(this.facture)) {
            adb.maj(this.facture);
        }
        adb.persist(this.facture);
        FacesMessage msg;
        if( adb.persist(this.facture)){
            msg = new FacesMessage("Réussite", "Mr " + facture.getUtilisateur().getPseudo() + " , vous avez créé une facture");
        } else {
            msg = new FacesMessage("Erreur", "Erreur de création du devis, VEUILLEZ REPRENDRE");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public Datee getDatefacture() {
        return datefacture;
    }

    public void setDatefacture(Datee datefacture) {
        this.datefacture = datefacture;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
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