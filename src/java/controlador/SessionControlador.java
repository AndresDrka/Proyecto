package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "sessionControlador")
@SessionScoped
public class SessionControlador implements Serializable {

    public SessionControlador() {
    }

    private Locale languageSelected;
    private Locale spanishLanguage = new Locale("es");
    private Locale defaultLanguage = new Locale("es");

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Locale idiomaUsuario = ec.getRequestLocale();
        boolean support = false;
        for (Locale l : getSupportLanguages()) {
            if (l.getLanguage().equals(idiomaUsuario.getLanguage())) {
                support = true;
                break;
            }
        }
        languageSelected = (support) ? idiomaUsuario : new Locale("es");
    }

    public Locale getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(Locale defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public Locale getLanguageSelected() {
        return languageSelected;
    }

    public void setLanguageSelected(Locale languageSelected) {
        this.languageSelected = languageSelected;
    }

    public Locale getSpanishLanguage() {
        return spanishLanguage;
    }

    public void setSpanishLanguage(Locale spanishLanguage) {
        this.spanishLanguage = spanishLanguage;
    }

    //METODOS
    public Locale getLanguagueToChange() {
        Locale language = spanishLanguage;
        Locale currentLanguague = (getLanguageSelected());

        if (currentLanguague.equals(Locale.ENGLISH)) {
            language = spanishLanguage;

        } else {
            language = Locale.ENGLISH;
        }
        return language/* = Locale.ENGLISH*/;
    }

    public List<Locale> getSupportLanguages() {
        List<Locale> idiomas = new ArrayList<>();
        Iterator<Locale> it = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
        while (it.hasNext()) {
            idiomas.add(it.next());
        }
        return idiomas;
    }

    public String cambiarIdioma(Locale idioma) {
        if (idioma != null) {
            this.languageSelected = idioma;
            FacesContext.getCurrentInstance().getViewRoot().setLocale(languageSelected);
            defaultLanguage = languageSelected;
        }
        return "/ManSyKreP";
    }
}
