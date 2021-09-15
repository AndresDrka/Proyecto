/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Turnos;
import facades.TurnosFacade;
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
@Named(value = "turnoControlador")
@SessionScoped
public class TurnoControlador  implements Serializable {

    /**
     * Creates a new instance of TurnoControlador
     */
    public TurnoControlador() {
    }
      @EJB
    private TurnosFacade turnosFacade;
    Turnos turnos;
     

    public Turnos getTurnos() {
        return turnos;
    }

    public void setTurnos(Turnos turnos) {
        this.turnos = turnos;
    }
  
    @PostConstruct
    public void init(){
        this.turnos= new Turnos();
    }
    
    public String crear(){
        turnosFacade.create(turnos);
        turnos= new Turnos();
        return "listar";
    }
    
    public String eliminar(Turnos turnos){
        turnosFacade.remove(turnos);
        turnos= new Turnos();
        return "listar";
    }
    public String preEditar(Turnos turnos){
        this.turnos=turnos;
        return "editar";
    }
    public String editar(){
        turnosFacade.edit(turnos);
        turnos= new Turnos();        
        return "listar";
    }
    
    public List<Turnos> consultar(){
        return turnosFacade.findAll();
    }
    
    public void consultarId(){
       turnos= turnosFacade.find(this.turnos);
        
    }
}
