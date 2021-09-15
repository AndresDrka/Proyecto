/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Insumos;
import facades.InsumosFacade;
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
@Named(value = "insumoControlador")
@SessionScoped
public class InsumoControlador implements Serializable {

    /**
     * Creates a new instance of InsumoControlador
     */
    public InsumoControlador() {
    }
     @EJB
    InsumosFacade insumosFacade;
    private Insumos insumos;

    public Insumos getInsumos() {
        return insumos;
    }

    public void setInsumos(Insumos insumos) {
        this.insumos = insumos;
    }

    @PostConstruct
    public void init() {
        this.insumos = new Insumos();
    }

    public String crear() {
        insumosFacade.create(insumos);
        insumos = new Insumos();
        return "listar";
    }

    public String eliminar(Insumos insumos) {
        insumosFacade.remove(insumos);
        insumos = new Insumos();
        return "listar";
    }

    public String preEditar(Insumos insumos) {
        this.insumos = insumos;
        return "editar";
    }

    public String editar() {
        insumosFacade.edit(insumos);
        insumos = new Insumos();
        return "listar";
    }

    public List<Insumos> consultar() {
        return insumosFacade.findAll();
    }

    public void consulatId() {
        insumos = insumosFacade.find(this.insumos);
    }

    public String volver() {
        this.insumos = new Insumos();
        return "listar";
    }

}
