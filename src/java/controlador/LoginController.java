package controlador;

import entidades.Permisos;
import entidades.Roles;
import entidades.Usuarios;
import facades.RolesFacade;
import facades.UsuariosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    UsuariosFacade usuarioFacade;
    @EJB
    RolesFacade rolFacade;
    private Usuarios usuario;
    private Roles rolUsuario;

    @PostConstruct
    public void init() {
        usuario = new Usuarios();
        rolUsuario = new Roles();
    }

    public Roles getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Roles rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String iniciarSesion() {
        Usuarios u;
        String url = "";
        try {
            u = usuarioFacade.login(usuario);
            if (u != null) {
                this.rolUsuario = u.getIdRol();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioSesion", u);
//                return "menu?faces-redirect=true";

                String rol = u.getIdRol().getRoles();
                switch (rol) {
                    case "Cliente":
                        url = "dashboard-cliente?faces-redirect=true";
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
                        break;
                    case "Administrador":
                        url = "dashboard-admin?faces-redirect=true";
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);

                        break;
                    case "Jefe de Producción":
                        url = "dashboard-jefe?faces-redirect=true";
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);

                        break;
                    default:
                        break;
                }
                return url;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "AVISO", "Credenciales Incorrectas"));
                System.out.println("ERRRROOOOORRR ====" + u);
                return "error?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Se ha producido un error en el log"));

        }
        return "";
    }

    public void verificarSesion() {
        try {
            Usuarios u = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            if (u == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
            }
        } catch (Exception e) {
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Permisos> obtenerMenu() {
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSesion");
        Roles rolPermiso = us.getIdRol();
        return rolPermiso.getPermisosList();
    }
}
