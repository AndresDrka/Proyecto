/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cargo;
import entidades.Turnos;

import facades.CargoFacade;
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
@Named(value = "cargo")
@SessionScoped
public class CargoControlador  implements Serializable{

    /**
     * Creates a new instance of Cargo
     */
    public CargoControlador() {
    }
     @EJB
    private TurnosFacade turnoFacade;
    Turnos turno;

    @EJB
    private CargoFacade cargoFacade;
    Cargo cargo;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Turnos getTurno() {
        return turno;
    }

    public void setTurno(Turnos turno) {
        this.turno = turno;
    }

   
    @PostConstruct

    public void init() {
        this.cargo = new Cargo();
        this.turno = new Turnos();
    }

    public String crear() {
        cargo.setIdTurno(turnoFacade.find(turno.getIdTurno()));
        cargoFacade.create(cargo);
        cargo = new Cargo();
        return "listar";
    }

    public String eliminar(Cargo cargo) {
        cargoFacade.remove(cargo);
        cargo = new Cargo();
        return "listar";
    }

    public String preEditar(Cargo cargo) {
        this.cargo = cargo;
        return "editar";
    }

    public String editar() {
        cargo.setIdTurno(turnoFacade.find(turno.getIdTurno()));
        cargoFacade.edit(cargo);
        cargo = new Cargo();
        return "listar";
    }

    public List<Cargo> consultar() {
        return cargoFacade.findAll();
    }

    public void consultarId() {
        cargo = cargoFacade.find(this.cargo);
    }
    
    
    public String volver(){
        this.cargo = new Cargo();
        this.turno = new Turnos();
        return "listar";
    }

}
