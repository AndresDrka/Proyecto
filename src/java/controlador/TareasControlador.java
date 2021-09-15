package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.Tarea;
import facades.TareaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Myriam
 */
@Named(value = "tareasControlador")
@SessionScoped
public class TareasControlador implements Serializable {

    /**
     * Creates a new instance of TareasControlador
     */
    public TareasControlador() {
    }
      @EJB
    TareaFacade tareaFacade;
    private Tarea tarea;

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @PostConstruct
    public void init() {
        this.tarea = new Tarea();
    }

    public String crear() {
        tareaFacade.create(tarea);
        tarea = new Tarea();
        return "listar";
    }

    public String eliminar(Tarea tarea ) {
        tareaFacade.remove(tarea);
        tarea = new Tarea();
        return "listar";
    }
    public String preEditar(Tarea tarea){
        this.tarea= tarea;
        return "editar";
    }
    public String editar() {
        tareaFacade.edit(tarea);
        this.tarea = new Tarea();
        return "listar";
    }

    public List<Tarea> consultar() {
        return tareaFacade.findAll();
    }

    public void consultarId() {
        tarea = tareaFacade.find(this.tarea);
    }
}
