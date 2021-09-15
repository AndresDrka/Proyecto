/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cliente;
import entidades.Usuarios;
import facades.ClienteFacade;
import facades.UsuariosFacade;
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
@Named(value = "clienteControlador")
@SessionScoped
public class ClienteControlador implements Serializable {

    /**
     * Creates a new instance of ClienteControlador
     */
    public ClienteControlador() {
    }
     @EJB
    private UsuariosFacade usuarioFacade;
    Usuarios usuarios;
    @EJB
    private ClienteFacade clienteFacade;
    Cliente cliente;

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @PostConstruct
    public void init() {
        this.cliente = new Cliente();
        this.usuarios = new Usuarios();
    }

    public String crear() {
        cliente.setIdUsuarios(usuarioFacade.find(usuarios.getIdUsuarios()));
        clienteFacade.create(cliente);
        cliente = new Cliente();
        return "listar";
    }

    public String eliminar(Cliente cliente) {
        clienteFacade.remove(cliente);
        cliente = new Cliente();
        return "listar";
    }

    public String preEditar(Cliente cliente) {
        this.cliente = cliente;
        return "editar";
    }

    public String editar() {
        cliente.setIdUsuarios(usuarioFacade.find(usuarios.getIdUsuarios()));
        clienteFacade.edit(cliente);
        cliente = new Cliente();
        return "listar";
    }

    public List<Cliente> consultar() {
        return clienteFacade.findAll();
    }

    public void consultarId() {
        cliente = clienteFacade.find(this.cliente);
    }

    public String volver(){
        this.cliente = new Cliente();
        this.usuarios = new Usuarios();
        return "listar";
    }
}
