package com.afpa.access;

import com.afpa.model.*;
import com.afpa.model.interfaces.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Stateless
public class AccessDb implements Serializable {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public <T,K> T recherche(Class<T> t,K key)
    {
        return em.find(t, key);
    }

    /*@Transactional
    public <T> boolean persist(T t) {
        if (!em.contains(t)) {
            t = em.merge(t);
        }
        em.persist(t);

        return em.contains(t);
    }*/

    @Transactional
    public <T> boolean maj(T t){
        em.merge(t);
        return em.contains(t);
    }
    @Transactional
    public <T> void delete(T t){
        if(!em.contains(t)){
            t = em.merge(t);
        }

        em.remove(t);
    }
    @Transactional
    public <T> List<T> findAll(Class<T> t){
        List<T> listt=em.createQuery("select t from "+t.getSimpleName()+" t").getResultList();
        if(t.getSimpleName().equals("Utilisateur"))
        {
            //si c'est un utilisateur je ne remonte pas le mot de passe de la base
            List<User> listu= (List<User>) listt;
            for(User u:listu)
            {
                //em.merge(u);
                //u.setMotdepasse("");
            }
            listt= (List<T>) listu;
        }
        return listt;
       // return em.createQuery("SELECT a from " + t.getSimpleName() + " a").getResultList();

    }

    /*
    @Transactional
    public boolean persist(Facture f) {
        if (!em.contains(f)) {
            f = em.merge(f);
        }
        em.persist(f);

        return em.contains(f);
    }

    @Transactional
    public boolean persist(Contrat c) {
       if(!em.contains(c)){
           c = em.merge(c);
       }
       em.persist(c);

       return em.contains(c);
    }
    @Transactional
    public boolean persist(Produit p) {
        if (!em.contains(p)) {
            p = em.merge(p);
        }
        em.persist(p);

        return em.contains(p);
    }
    @Transactional
    public boolean persist(Entreprise e) {
        if (!em.contains(e)) {
            e = em.merge(e);
        }
        em.persist(e);

        return em.contains(e);
    }

    @Transactional
    public boolean persist(Devis d) {
        if (!em.contains(d)) {
            d = em.merge(d);
        }
        em.persist(d);

        return em.contains(d);
    }
    */

    @Transactional
    public boolean persist(Object o) {
        if (!em.contains(o)) {
            o = em.merge(o);
        }
        em.persist(o);
        return em.contains(o);
    }

    //Recherche d'un utilisateur par son pseudo avec une requête nomée
    public Utilisateur readUtlitisateurByPseudo (String pseudo)
    { return em.createNamedQuery("Utilisateur.findByPseudo",Utilisateur.class).setParameter("pseudo",pseudo).getSingleResult();

    }


    /*public Utilisateur readUtlitisateurByUtilisateur (Utilisateur utilisateur){
       Query q = em.createNamedQuery(SELECT * FROM Utilisateur u WHERE u.pseudo=?1,Utilisateur.class );
       q.setParameter(1,utilisateur.getPseudo());
       return (com.afpa.model.Utilisateur) q.getSingleResult();
    }*/

    //création d'une liste d'utilisateur par une requête nomée par l'Administrateur
    public List<Utilisateur> getAllUtilisateur()
    { return em.createNamedQuery("Utilisateur.findAll",Utilisateur.class).getResultList();}

    //création d'une liste de dates par une requête nomée
    public List<Datee> getAllDatee()
    { return em.createNamedQuery("Datee.findAll",Datee.class).getResultList();}


    //création d'une liste de devis par une requête nomée
    public List<Devis>  getAllDevis()
    { return em.createNamedQuery("Devis.findAll",Devis.class).getResultList();}

    //création d'une liste de devis par une requête nomée
    public List<Facture>  getAllFacture()
    { return em.createNamedQuery("Facture.findAll",Facture.class).getResultList();}

    //création d'une liste de produits par une requête nomée
    public List<Produit>  getAllProduit()
    { return em.createNamedQuery("Produit.findAll",Produit.class).getResultList();}

    //création d'une liste d'entreprises par une requête nomée
    public List<Entreprise>  getAllEntreprise()
    { return em.createNamedQuery("Entreprise.findAll",Entreprise.class).getResultList();}

    //création d'une liste de types de contrat par une requête nomée
    public List<Typecontrat>  getAllTypecontrat()
    { return em.createNamedQuery("Typecontrat.findAll",Typecontrat.class).getResultList();}

    //création d'une liste d'avenants par une requête nomée
    public List<Avenant> getAllAvenant()
    { return em.createNamedQuery("Avenant.findAll",Avenant.class).getResultList();}

    //création d'une liste de contrats par une requête nomée
    public List<Contrat> getAllContrat()
    { return em.createNamedQuery("Contrat.findAll",Contrat.class).getResultList();}

    //création d'une liste de contrats selon le Type de contrat par une requête nomée
    public List<Typecontrat>  getAllContratForType(Contrat c) {
        Query q = em.createNamedQuery("Typecontrat.findAllContratForType");
        q.setParameter("typecontrat", c);
        return q.getResultList();
    }

    public List<Class> getAllClass(){
        List<Class> listCass = new ArrayList<>(); //Création d'une nouvelle liste de Class

        //on récupère la liste des classes qui sont annotées @Entity
        List<EntityType<?>> listType = new ArrayList<>(em.getMetamodel().getEntities());

        //on effectue un trie pour ne remonter que ce qui est interfacé User et ...
        for(EntityType et : listType){
            if( Arrays.asList(et.getJavaType().getInterfaces()).contains(User.class)){
                listCass.add(et.getBindableJavaType());
            }
        }

        return listCass;
    }

    public List<DataColumn> getAllColumnModel(Class c)
    {
        List<DataColumn> listcm=new ArrayList<>();

        for(Field f:c.getDeclaredFields())
        {
            //filtrage sur les attributs indésirable
            if(!f.getType().isPrimitive()&&!f.getName().contains("persistence")&&!f.getType().isInterface())
                listcm.add(new DataColumn(f.getName())); //on ajoute dans la liste le nom de l'attribut filtré
        }
        return listcm;
    }

    public List<Contrat>  getAllContratForUser(Utilisateur u)
    {
        Query q = em.createNamedQuery("Utilisateur.findAllContratForUser");
        q.setParameter("utilisateur", u);
        return q.getResultList();
    }

    public List<Devis>  getAllDevisForUser(Utilisateur u)
    {
        Query q = em.createNamedQuery("Utilisateur.findAllDevisForUser");
        q.setParameter("utilisateur", u);
        return q.getResultList();
    }

    public List<Facture>  getAllFactureForUser(Utilisateur u)
    {
        Query q = em.createNamedQuery("Utilisateur.findAllFactureForUser");
        q.setParameter("utilisateur", u);
        return q.getResultList();

    }

}

