/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Ciudades;
import facades.CiudadesFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Myriam
 */
@Named(value = "ciudadesControladores")
@SessionScoped
public class CiudadesControladores implements Serializable{

    /**
     * Creates a new instance of CiudadesControladores
     */
    public CiudadesControladores() {
    }
     @EJB
    CiudadesFacade ciudadesFacade;
    private Ciudades ciudades;

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    @PostConstruct
    public void init() {
        this.ciudades = new Ciudades();
    }

    public String crear() {
        ciudadesFacade.create(ciudades);
        ciudades = new Ciudades();
        return "listar";
    }

    public String eliminar(Ciudades ciudades) {
        ciudadesFacade.remove(ciudades);
        ciudades = new Ciudades();
        return "listar";
    }

    public String preEditar(Ciudades ciudades) {
        this.ciudades = ciudades;
        return "editar";
    }

    public String editar() {
        ciudadesFacade.edit(ciudades);
        ciudades = new Ciudades();
        return "listar";
    }

    public List<Ciudades> consultar() {
        return ciudadesFacade.findAll();
    }

    public void consultarId() {
        ciudades = ciudadesFacade.find(this.ciudades);
    }
}
