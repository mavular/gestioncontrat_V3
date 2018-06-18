package com.afpa.access;

public class AccessDb {
}
/*package com.afpa.access;

import com.afpa.model.DataColumn;
import com.afpa.model.Reservation;
import com.afpa.model.Salle;
import com.afpa.model.Utilisateur;
import com.afpa.model.interfaces.Reservable;
import com.afpa.model.interfaces.User;

import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.*;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;


@Stateless
public class AccessDb implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public <T,K> T recherche(Class<T> t,K key)
    {
        return em.find(t, key);
    }

    public <T> boolean maj(T t){
        em.merge(t);
        return em.contains(t);
    }

    public <T> boolean persist(T t) {
        if(!em.contains(t)){
            t = em.merge(t);
        }
        em.persist(t);

        return em.contains(t);
    }

    public <T> void delete(T t){
        if(!em.contains(t)){
            t = em.merge(t);
        }

        em.remove(t);
    }

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

    public List<Reservation> findAllForSalle(Reservable r){
        Query q= em.createNamedQuery("Reservation.findAllActiveForSalle");
        q.setParameter("salle", r);
        q.setParameter("etat", false);
        return q.getResultList();
    }

    public List<Reservation> findAllReservationsForUser(Utilisateur u){
        Query q = em.createNamedQuery("Utilisateur.findAllReservationsForUser");
        q.setParameter("utilisateur", u);
        return q.getResultList();
    }

    //création d'une liste de salle par une requête nomée
    public List<Salle> getAllSalle(){ return em.createNamedQuery("Salle.finAll",Salle.class).getResultList();}


    public List<Class> getAllClass(){
        List<Class> listCass = new ArrayList<>(); //Création d'une nouvelle liste de Class

        //on récupère la liste des classes qui sont annotées @Entity
        List<EntityType<?>> listType = new ArrayList<>(em.getMetamodel().getEntities());

        //on effectue un trie pour ne remonter que ce qui est interfacé User et Reservable
        for(EntityType et : listType){
            if( Arrays.asList(et.getJavaType().getInterfaces()).contains(User.class) || Arrays.asList(et.getJavaType()
                    .getInterfaces()).contains(Reservable.class) ){
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
}
*/