/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author uribe
 */
@Named(value = "languageController")
@ManagedBean
@SessionScoped
public class LanguageController implements Serializable {

    public LanguageController() {
    }

    private String localidad;
    private static Map<String, Object> LstIdioma;

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Map<String, Object> getLstIdioma() {
        return LstIdioma;
    }

    public static void setLstIdioma(Map<String, Object> LstIdioma) {
        LanguageController.LstIdioma = LstIdioma;
    }

    static {
        LstIdioma = new LinkedHashMap<String, Object>();
        Locale espanol = new Locale("ES");
        LstIdioma.put("Español", espanol);
        LstIdioma.put("English", Locale.ENGLISH);

    }

    public void localidadChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        
        for (Map.Entry<String, Object> entry : LstIdioma.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }

    }

}
