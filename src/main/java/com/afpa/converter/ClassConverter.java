package com.afpa.converter;


import org.eclipse.persistence.jpa.config.ConverterClass;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ClassConverter implements Converter,Serializable {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            System.out.println("as object");
            return Class.forName(value); //reconstruit à partir du nom canonique l'object Class
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConverterClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        {
            return ((Class)value).getCanonicalName();
        }
    }
}