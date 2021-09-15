/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.TipoIdentificacion;
import facades.TipoIdentificacionFacade;
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
@Named(value = "tipoIdentificacionControlador")
@SessionScoped
public class TipoIdentificacionControlador  implements Serializable {

    /**
     * Creates a new instance of TipoIdentificacionControlador
     */
    public TipoIdentificacionControlador() {
    }
     @EJB
     private TipoIdentificacionFacade  tipoIdentificacionFacade;
     TipoIdentificacion tipoIdentificacion;

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
     
     
     
        @PostConstruct
    public void init() {
        this.tipoIdentificacion = new TipoIdentificacion();
    }

    public String crear() {
        tipoIdentificacionFacade.create(tipoIdentificacion);
        tipoIdentificacion = new TipoIdentificacion();
        return "listar";
    }

    public String eliminar(TipoIdentificacion tipoIdentificacion){
     tipoIdentificacionFacade.remove(tipoIdentificacion);
        tipoIdentificacion= new TipoIdentificacion();
        return "listar";
    }

    public String preEditar(TipoIdentificacion tipoIdentificacion){
        this.tipoIdentificacion=tipoIdentificacion;
        return "editar";
    }
    public String editar() {
       tipoIdentificacionFacade.remove(tipoIdentificacion);
        tipoIdentificacion= new TipoIdentificacion();
        return "listar";
    }

    public List<TipoIdentificacion> consultar() {
       return tipoIdentificacionFacade.findAll();
    }

    public void consultarid() {
       tipoIdentificacion = tipoIdentificacionFacade.find(this.tipoIdentificacion);
    }
  
             
}
