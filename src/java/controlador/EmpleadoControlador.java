
package controlador;

import entidades.Cargo;
import entidades.Empleado;
import entidades.Usuarios;
import facades.CargoFacade;
import facades.EmpleadoFacade;
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
@Named(value = "empleadoControlador")
@SessionScoped
public class EmpleadoControlador implements Serializable {

    /**
     * Creates a new instance of EmpleadoControlador
     */
    public EmpleadoControlador() {
    }
    @EJB
    UsuariosFacade usuarioFacade;
    private Usuarios usuario;
    @EJB
    CargoFacade cargoFacade;
    private Cargo cargo;
    @EJB
    EmpleadoFacade empleadoFacade;
    private Empleado empleado;

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @PostConstruct
    public void init() {
        this.cargo = new Cargo();
        this.empleado = new Empleado();
        this.usuario = new Usuarios();
    }

    public String crear() {
        empleado.setIdCargo(cargoFacade.find(cargo.getIdCargo()));
        empleado.setIdUsuarios(usuarioFacade.find(usuario.getIdUsuarios()));
        empleadoFacade.create(empleado);
        empleado = new Empleado();
        cargo = new Cargo();
        usuario = new Usuarios();
        return "listar";
    }

    public String eliminar(Empleado empleado) {
        empleadoFacade.remove(empleado);
        empleado = new Empleado();
        return "listar";
    }

    public String preEditar(Empleado empleado) {
        this.empleado = empleado;
        return "editar";
    }

    public String editar() {
        empleado.setIdCargo(cargoFacade.find(cargo.getIdCargo()));
        empleado.setIdUsuarios(usuarioFacade.find(usuario.getIdUsuarios()));
        empleadoFacade.edit(empleado);
        empleado = new Empleado();
        cargo = new Cargo();
        usuario = new Usuarios();
        return "listar";
    }

    public List<Empleado> consultar() {
        return empleadoFacade.findAll();
    }

    public void consultarId() {
        empleado = empleadoFacade.find(this.empleado);
    }

    public String volver() {
        this.cargo = new Cargo();
        this.empleado = new Empleado();
        this.usuario = new Usuarios();

        return "listar";
    }

    public String detalle(Empleado empleado) {
        this.empleado = empleado;
        return "detalle";
    }

    
    
}
