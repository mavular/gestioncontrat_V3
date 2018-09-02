package com.afpa;

import com.afpa.access.AccessDb;
import com.afpa.model.Utilisateur;
import com.afpa.presenter.ServiceUtilisateur;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public Utilisateur u;
    public List<Utilisateur> list;
    public AccessDb adb;
    public ServiceUtilisateur su;


    public static void main( String[] args )
    {

        Utilisateur u=new Utilisateur("yves","lkyhafpa1","lokossou");
        String test;
        System.out.println( );

    }
}
