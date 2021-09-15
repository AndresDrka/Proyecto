/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Ciudades;
import entidades.Roles;
import entidades.TipoIdentificacion;
import entidades.Usuarios;
import facades.CiudadesFacade;
import facades.RolesFacade;
import facades.UsuariosFacade;
import facades.TipoIdentificacionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Myriam
 */
@Named(value = "usuariosControlador")
@SessionScoped
public class UsuariosControlador implements Serializable {

    /**
     * Creates a new instance of UsuariosControlador
     */
    public UsuariosControlador() {
    }
    @EJB
    CiudadesFacade ciudadesFAcade;
    private Ciudades ciudades;
    @EJB
    UsuariosFacade usuariosFacade;
    private Usuarios usuarios;

    @EJB

    TipoIdentificacionFacade tipoIdentificacionFacade;
    private TipoIdentificacion tipoIdentificacion;

    @EJB
    RolesFacade rolesFacade;
    private Roles roles;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @PostConstruct
    public void init() {
        this.roles = new Roles();
        this.ciudades = new Ciudades();
        this.tipoIdentificacion = new TipoIdentificacion();
        this.usuarios = new Usuarios();

    }

    public String crear() {
        try {
            usuarios.setIdRol(rolesFacade.find(roles.getIdRol()));
            usuarios.setIdCiudades(ciudadesFAcade.find(ciudades.getIdciudades()));
            usuarios.setIdTpidentificacion(tipoIdentificacionFacade.find(tipoIdentificacion.getIdTpidentificacion()));
            usuariosFacade.create(usuarios);
            emailControlador mail = new emailControlador();

            mail.setAsunto(" Registro exitoso");
            mail.setPara(usuarios.getCorreo());
            mail.setTexto("Bienvenido a nuestro equipo");

            mail.correo();
            roles = new Roles();
            usuarios = new Usuarios();
            ciudades = new Ciudades();
            tipoIdentificacion = new TipoIdentificacion();
            return "listar";
        } catch (Exception e) {
            System.out.println("ERRORRROROOROR =" + e);
            return "";
        }
    }

    public void registrarCliente() {
        try {
            this.roles.setIdRol(3);
            this.usuarios.setIdRol(roles);
            usuarios.setIdCiudades(ciudadesFAcade.find(ciudades.getIdciudades()));
            usuarios.setIdTpidentificacion(tipoIdentificacionFacade.find(tipoIdentificacion.getIdTpidentificacion()));
            usuariosFacade.create(usuarios);
            
            emailControlador mail = new emailControlador();

            mail.setAsunto(" Registro exitoso");
            mail.setPara(usuarios.getCorreo());
            mail.setTexto("Bienvenido a nuestro equipo");

            mail.correo();
            roles = new Roles();
            usuarios = new Usuarios();
            ciudades = new Ciudades();
            tipoIdentificacion = new TipoIdentificacion();

        } catch (Exception e) {
            System.out.println("ERRORRROROOROR =" + e);

        }
    }

//    public void registrarEmpleado() {
//        try {
//            this.roles.setIdRol(3);
//            this.usuarios.setIdRol(getRoles());
//            usuarios.setIdCiudades(ciudadesFAcade.find(ciudades.getIdciudades()));
//            usuarios.setIdTpidentificacion(tipoIdentificacionFacade.find(tipoIdentificacion.getIdTpidentificacion()));
//            usuariosFacade.create(usuarios);
//            emailControlador mail = new emailControlador();
//
//            mail.setAsunto(" Registro exitoso");
//            mail.setPara(usuarios.getCorreo());
//            mail.setTexto("Bienvenido a nuestro equipo");
//
//            mail.correo();
//            roles = new Roles();
//            usuarios = new Usuarios();
//            ciudades = new Ciudades();
//            tipoIdentificacion = new TipoIdentificacion();
//
//        } catch (Exception e) {
//            System.out.println("ERRORRROROOROR =" + e);
//
//        }
//    }

    public String eliminar(Usuarios usuarios) {
        usuariosFacade.remove(usuarios);
        usuarios = new Usuarios();
        return "listar";
    }

    public String preEditar(Usuarios usuarios) {
        this.usuarios = usuarios;
        return "editar";
    }

    public String editar() {
        usuarios.setIdRol(rolesFacade.find(roles.getIdRol()));
        usuarios.setIdCiudades(ciudadesFAcade.find(ciudades.getIdciudades()));
        usuarios.setIdTpidentificacion(tipoIdentificacionFacade.find(tipoIdentificacion.getIdTpidentificacion()));
        usuariosFacade.create(usuarios);
        roles = new Roles();
        usuarios = new Usuarios();
        ciudades = new Ciudades();
        tipoIdentificacion = new TipoIdentificacion();
        return "listar";
    }

    public List<Usuarios> consultar() {
        return usuariosFacade.findAll();
    }

    public void consultarId() {
        usuarios = usuariosFacade.find(this.usuarios);
    }

    public String volver() {

        this.roles = new Roles();
        this.ciudades = new Ciudades();
        this.tipoIdentificacion = new TipoIdentificacion();
        this.usuarios = new Usuarios();
        return "listar";
    }

    public String vistaConfigurarCuenta() {
        this.usuarios = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSesion");
        return "vista/usuarios/configurarcuenta.xhtml?faces-redirect=true";
    }

    public String guardarCambios() {
        usuarios.setIdRol(getRoles());
        usuarios.setIdCiudades(getCiudades());
        usuarios.setIdTpidentificacion(getTipoIdentificacion());
        usuariosFacade.edit(getUsuarios());
        roles = new Roles();
        usuarios = new Usuarios();
        ciudades = new Ciudades();
        tipoIdentificacion = new TipoIdentificacion();
        return "../../index.xhtml";
    }

    public List<Usuarios> listarRol(int IdRol) {
        List<Usuarios> lista = null;

        try {
            lista = usuariosFacade.findRol(IdRol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
