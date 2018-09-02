package com.afpa.model;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "entreprise")
@NamedQueries({
        @NamedQuery(name="Entreprise.findAll", query = "SELECT e FROM Entreprise e"),
        @NamedQuery(name="Entreprise.findBynom", query = "SELECT e FROM Entreprise e WHERE e.nom = :nom"),
        @NamedQuery(name="Entreprise.findBysiret", query = "SELECT e FROM Entreprise e WHERE e.siret = :siret"),
        @NamedQuery(name="Entreprise.findByqualification", query = "SELECT e FROM Entreprise e WHERE e.qualification = :qualification"),
        @NamedQuery(name="Entreprise.findBytelephone", query = "SELECT e FROM Entreprise e WHERE e.telephone = :telephone"),
        @NamedQuery(name="Entreprise.findByemail", query = "SELECT e FROM Entreprise e WHERE e.email = :email"),
        @NamedQuery(name="Entreprise.findByrepresentant", query = "SELECT e FROM Entreprise e WHERE e.representant = :representant"),
        @NamedQuery(name="Entreprise.findBynumerorue", query = "SELECT e FROM Entreprise e WHERE e.numerorue = :numerorue"),
        @NamedQuery(name="Entreprise.findBycp", query = "SELECT e FROM Entreprise e WHERE e.cp = :cp"),
        @NamedQuery(name="Entreprise.findByville", query = "SELECT e FROM Entreprise e WHERE e.ville = :ville"),
        @NamedQuery(name="Entreprise.findBypays", query = "SELECT e FROM Entreprise e WHERE e.pays = :pays"),
        @NamedQuery(name="Entreprise.findAllContratForPrestataire", query = "select c FROM Contrat c WHERE c.prestataire =:prestataire"),
        @NamedQuery(name="Entreprise.findAllContratForClient", query = "select c FROM Contrat c WHERE c.client =:client"),
        @NamedQuery(name="Entreprise.findAllDevisForPrestataire", query = "select d FROM Devis d WHERE d.prestataire =:prestataire"),
        @NamedQuery(name="Entreprise.findAllDevisForClient", query = "select d FROM Devis d WHERE d.client =:client"),
        @NamedQuery(name="Entreprise.findAllFactureForPrestataire", query = "select f FROM Facture f WHERE f.prestataire =:prestataire"),
        @NamedQuery(name="Entreprise.findAllFactureForClient", query = "select f FROM Facture f WHERE f.client =:client")

})
public class Entreprise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "^([a-zA-Z'àâéèêôùûçÀÂÉÈÔÙÛÇ\\s-]{1,30})$", message = "Le nom doit comporter que des caractères")
    private String nom;
    @Column(name="siret")
    private Integer siret;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "telephone")
    private Integer telephone;
    @Column(name = "email")
    private String email;
    @Column(name = "representant")
    private String representant;
    @Column(name = "numerorue")
    private String numerorue;
    @Column(name = "cp")
    private String cp;
    @Column(name = "ville")
    private String ville;
    @Column(name = "pays")
    private String pays;
    @OneToMany (mappedBy = "prestataire")
    private List<Contrat> lescontratsduprestataire;
    @OneToMany (mappedBy = "client")
    private List<Contrat> lescontratsduclient;
    @OneToMany (mappedBy = "prestataire")
    private List<Devis> lesdevisduprestataire;
    @OneToMany (mappedBy = "client")
    private List<Devis> lesdevisduclient;
    @OneToMany (mappedBy = "prestataire")
    private List<Facture> lesfacturesduprestataire;
    @OneToMany (mappedBy = "client")
    private List<Facture> lesfacturesduclient;


    public Entreprise()
    {

    }

    public Entreprise(String nom, Integer siret, String qualification, Integer telephone, String email, String representant, String numerorue, String cp, String ville, String pays) {
        this.nom = nom;
        this.siret = siret;
        this.qualification = qualification;
        this.telephone = telephone;
        this.email = email;
        this.representant = representant;
        this.numerorue = numerorue;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.lescontratsduprestataire = lescontratsduprestataire;
        this.lescontratsduclient = lescontratsduclient;
        this.lesdevisduprestataire = lesdevisduprestataire;
        this.lesdevisduclient = lesdevisduclient;
        this.lesfacturesduprestataire = lesfacturesduprestataire;
        this.lesfacturesduclient = lesfacturesduclient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getSiret() {
        return siret;
    }

    public void setSiret(Integer siret) {
        this.siret = siret;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepresentant() {
        return representant;
    }

    public void setRepresentant(String representant) {
        this.representant = representant;
    }

    public String getNumerorue() {
        return numerorue;
    }

    public void setNumerorue(String numerorue) {
        this.numerorue = numerorue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public List<Contrat> getLescontratsduprestataire() {
        return lescontratsduprestataire;
    }

    public void setLescontratsduprestataire(List<Contrat> lescontratsduprestataire) {
        this.lescontratsduprestataire = lescontratsduprestataire;
    }

    public List<Contrat> getLescontratsduclient() {
        return lescontratsduclient;
    }

    public void setLescontratsduclient(List<Contrat> lescontratsduclient) {
        this.lescontratsduclient = lescontratsduclient;
    }

    public List<Devis> getLesdevisduprestataire() {
        return lesdevisduprestataire;
    }

    public void setLesdevisduprestataire(List<Devis> lesdevisduprestataire) {
        this.lesdevisduprestataire = lesdevisduprestataire;
    }

    public List<Devis> getLesdevisduclient() {
        return lesdevisduclient;
    }

    public void setLesdevisduclient(List<Devis> lesdevisduclient) {
        this.lesdevisduclient = lesdevisduclient;
    }

    public List<Facture> getLesfacturesduprestataire() {
        return lesfacturesduprestataire;
    }

    public void setLesfacturesduprestataire(List<Facture> lesfacturesduprestataire) {
        this.lesfacturesduprestataire = lesfacturesduprestataire;
    }

    public List<Facture> getLesfacturesduclient() {
        return lesfacturesduclient;
    }

    public void setLesfacturesduclient(List<Facture> lesfacturesduclient) {
        this.lesfacturesduclient = lesfacturesduclient;
    }

    @Override
    public String toString() {
        return nom;
    }
}
