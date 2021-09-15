/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Novedadesempleado;
import facades.NovedadesempleadoFacade;
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
@Named(value = "novedadesControlador")
@SessionScoped
public class NovedadesEmpleadosControlador implements Serializable{

    /**
     * Creates a new instance of NovedadesControlador
     */
    public NovedadesEmpleadosControlador() {
    }
    @EJB
    NovedadesempleadoFacade novedadesempleadoFacadeFacade;
    private Novedadesempleado novedadesempleado;

    public Novedadesempleado getNovedades() {
        return novedadesempleado;
    }

    public void setNovedades(Novedadesempleado novedadesempleado) {
        this.novedadesempleado = novedadesempleado;
    }

    @PostConstruct
    public void init() {
        this.novedadesempleado = new Novedadesempleado();
    }

    public String crear() {
        novedadesempleadoFacadeFacade.create(novedadesempleado);
        novedadesempleado = new Novedadesempleado();
        return "listar";
    }

    public String eliminar(Novedadesempleado novedadesempleado) {
        novedadesempleadoFacadeFacade.remove(novedadesempleado);
        novedadesempleado = new Novedadesempleado();
        return "listar";
    }

    public String preEditar(Novedadesempleado novedadesempleado) {
        this.novedadesempleado = novedadesempleado;
        return "editar";
    }

    public String editar() {
        novedadesempleadoFacadeFacade.edit(novedadesempleado);
        novedadesempleado = new Novedadesempleado();
        return "listar";
    }

    public List<Novedadesempleado> consultar() {
        return novedadesempleadoFacadeFacade.findAll();
    }

    public void consultarId() {
        novedadesempleado = novedadesempleadoFacadeFacade.find(this.novedadesempleado);
    }

    public String volver() {
        this.novedadesempleado = new Novedadesempleado();
        return "listar";
    }
 
}
