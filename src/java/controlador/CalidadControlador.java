/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Calidad;
import facades.CalidadFacade;
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
@Named(value = "calidadControlador")
@SessionScoped
public class CalidadControlador implements Serializable {

    /**
     * Creates a new instance of CalidadControlador
     */
    public CalidadControlador() {
    }
    @EJB
    private CalidadFacade calidadFacade;
    Calidad calidad;

    public Calidad getCalidad() {
        return calidad;
    }

    public void setCalidad(Calidad calidad) {
        this.calidad = calidad;
    }
    
    @PostConstruct
    public void init(){
        this.calidad= new Calidad();
    }
    
    public String crear(){
        calidadFacade.create(calidad);
        calidad= new Calidad();
        return "listar";
    }
    public String eliminar(Calidad calidad){
        calidadFacade.remove(calidad);
        calidad= new Calidad();
        return "listar";
    }
    public String preEditar(Calidad calidad){
        this.calidad= calidad;
        return "editar";
    }
     public String editar(){
        calidadFacade.edit(calidad);
        calidad=new Calidad();
        return "listar";
    }
    public List<Calidad> consultar(){
        return calidadFacade.findAll();
    }
    
    public void consultarId(){
        calidad=calidadFacade.find(this.calidad);
    }
}
