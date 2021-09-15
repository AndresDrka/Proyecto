/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Roles;
import facades.RolesFacade;
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
@Named(value = "rolesControlador")
@SessionScoped
public class RolesControlador implements Serializable {

    /**
     * Creates a new instance of RolesControlador
     */
    public RolesControlador() {
    }
     @EJB
    RolesFacade rolesFacade;
    private Roles roles;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @PostConstruct
    public void init() {
        this.roles = new Roles();
    }

    public String crear() {
        rolesFacade.create(roles);
        roles = new Roles();
        return "listar";
    }

    public String eliminar(Roles roles){
        rolesFacade.remove(roles);
        roles= new Roles();
        return "listar";
    }

    public String preEditar(Roles roles){
        this.roles=roles;
        return "editar";
    }
    public String editar() {
        rolesFacade.edit(roles);
        roles = new Roles();
        return "listar";
    }

    public List<Roles> consultar() {
        return rolesFacade.findAll();
    }

    public void consultarid() {
        roles = rolesFacade.find(this.roles);
    }


}
