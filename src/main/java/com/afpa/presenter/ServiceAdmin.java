package com.afpa.presenter;

import com.afpa.access.AccessDb;
import com.afpa.model.Contrat;
import com.afpa.model.DataColumn;
import org.primefaces.event.RowEditEvent;

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
public class ServiceAdmin implements Serializable {

    @Inject
    //private DbAcces dba;
    private AccessDb dba;

    private List<Class> listClass;
    private Class selection;
    private List<DataColumn> columns;
    private List mydata;
    private List<Contrat> listContrat;

    private boolean testObject;

    public ServiceAdmin(){
        listClass = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        testObject = false;
    }

    public void dataRemplir(){
        columns = dba.getAllColumnModel(selection);
        mydata = dba.findAll(selection);
        //test();
    }

    public AccessDb getDba() {
        return dba;
    }

    public void setDba(AccessDb dba) {
        this.dba = dba;
    }

    public List<Class> getListClass() {
        return dba.getAllClass();
    }

    public void setListClass(List<Class> listClass) {
        this.listClass = listClass;
    }

    public Class getSelection() {
        return selection;
    }

    public void setSelection(Class selection) {
        this.selection = selection;
    }

    public List<DataColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataColumn> columns) {
        this.columns = columns;
    }

    public List getMydata() {
        return mydata;
    }

    public void setMydata(List mydata) {
        this.mydata = mydata;
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        dba.maj(event.getObject());
    }

    /*public <T> void deleteRow(T t){

        //****** DELETE + TEST RESERVATION SUR SALLE AVANT DELETE ************
        if(t.getClass().getSimpleName().equals("Salle")){

                if(dba.findAllForSalle((Salle) t).stream().anyMatch(r -> r.getDateTimeFin().after(new Date()) && !r.isEtat())) {
                FacesMessage msg = new FacesMessage("Erreur","Il existe au moins une réservation sur cette Salle");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                else {
                FacesMessage msg = new FacesMessage("OK","Salle delete");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                dba.delete(t);
                dataRemplir();
                }

                }

                else if(t.getClass().getSimpleName().equals("Utilisateur")){

                if(dba.findAllReservationsForUser((Utilisateur) t).stream().anyMatch(k -> k.getDateTimeFin().after(new Date()) && !k.isEtat())){
                FacesMessage msg = new FacesMessage("Erreur","Il existe au moins une réservation sur cet Utilisateur");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                else {
                FacesMessage msg = new FacesMessage("OK","Utilisateur delete");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                dba.delete(t);
                dataRemplir();
                }
                }

                else {
                dba.delete(t);
                dataRemplir();
                }

                }

public void ajouterLigne(){
        Salle s = new Salle();
        mydata.add(s);
        }

public  void test(){
        if(selection.getSimpleName().equals("Salle")){
        testObject = true;
        }
        else {
        testObject = false;
        }
        }
*/
public boolean getTestObject() {
        return testObject;
        }

public void setTestObject(boolean testObject) {
        this.testObject = testObject;
        }

    public List<Contrat> getListContrat() {
        return listContrat;
    }

    public void setListContrat(List<Contrat> listContrat) {
        this.listContrat = listContrat;
    }
}
        