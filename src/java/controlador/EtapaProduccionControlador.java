/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.EtapaProduccion;
import facades.EtapaProduccionFacade;
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
@Named(value = "etapaProduccionControlador")
@SessionScoped
public class EtapaProduccionControlador implements Serializable {

    /**
     * Creates a new instance of EtapaProduccionControlador
     */
    public EtapaProduccionControlador() {
    }
    @EJB
    EtapaProduccionFacade etapaProduccionFacade;
    private EtapaProduccion etapaProduccion;

    public EtapaProduccion getEtapaProduccion() {
        return etapaProduccion;
    }

    public void setEtapaProduccion(EtapaProduccion etapaProduccion) {
        this.etapaProduccion = etapaProduccion;
    }
    @PostConstruct
     public void init() {
        this.etapaProduccion = new EtapaProduccion();
    }

    public String crear() {
      etapaProduccionFacade.create(etapaProduccion);
        etapaProduccion = new EtapaProduccion();
        return "listar";
    }

    public String eliminar(EtapaProduccion etapaProduccion) {
        etapaProduccionFacade.remove(etapaProduccion);
        etapaProduccion = new EtapaProduccion();
        return "listar";
    }

    public String preEditar(EtapaProduccion etapaProduccion) {
        this.etapaProduccion = etapaProduccion;
        return "editar";
    }

    public String editar() {
        etapaProduccionFacade.edit(etapaProduccion);
       etapaProduccion = new EtapaProduccion();
        return "listar";
    }

    public List<EtapaProduccion> consultar() {
        return etapaProduccionFacade.findAll();
    }

    public void consulatId() {
        etapaProduccion =etapaProduccionFacade .find(this.etapaProduccion);
    }

    public String volver() {
        this.etapaProduccion = new EtapaProduccion();
        return "listar";
    }
}
