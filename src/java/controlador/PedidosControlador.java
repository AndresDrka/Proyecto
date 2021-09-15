/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cliente;
import entidades.Detallefactura;
import entidades.Empleado;
import entidades.Pedidos;
import entidades.Productos;
import facades.ClienteFacade;
import facades.DetallefacturaFacade;
import facades.EmpleadoFacade;
import facades.PedidosFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Myriam
 */
@Named(value = "pedidosControlador")
@SessionScoped
public class PedidosControlador implements Serializable {

    /**
     * Creates a new instance of PedidosControlador
     */
    public PedidosControlador() {

    }

    @EJB
    private ClienteFacade clienteFacade;
    Cliente cliente;
    @EJB
    private PedidosFacade pedidosFacade;
    Pedidos pedidos;
    @EJB
    private EmpleadoFacade empleadoFacade;
    Empleado empleado;
    @EJB
    private DetallefacturaFacade detalleFacade;
    Detallefactura detallefactura;
    Productos productos;
    int cantidad;
    List<Detallefactura> listaDetalle = new ArrayList<>();

    public List<Detallefactura> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<Detallefactura> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
    public Detallefactura getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(Detallefactura detallefactura) {
        this.detallefactura = detallefactura;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    @PostConstruct
    public void init() {
        this.cliente = new Cliente();
        this.pedidos = new Pedidos();
        this.empleado = new Empleado();
        this.detallefactura = new Detallefactura();
        this.productos = new Productos();
    }

    public String crear() {
        pedidos.setIdCliente(clienteFacade.find(cliente.getIdCliente()));
        pedidos.setIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        pedidosFacade.create(pedidos);
        pedidos = new Pedidos();
        cliente = new Cliente();
        empleado = new Empleado();
        return "listar";
    }

    public String eliminar(Pedidos pedidos) {
        pedidosFacade.remove(pedidos);
        pedidos = new Pedidos();
        return "listar";
    }

    public String preEditar(Pedidos pedidos) {
        this.pedidos = pedidos;
        return "editar";
    }

    public String editar() {
        pedidos.setIdCliente(clienteFacade.find(cliente.getIdCliente()));
        pedidos.setIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        pedidosFacade.edit(pedidos);
        pedidos = new Pedidos();
        cliente = new Cliente();
        empleado = new Empleado();
        return "listar";
    }

    public List<Pedidos> consultar() {
        return pedidosFacade.findAll();
    }

    public void consultarId() {
        pedidos = pedidosFacade.find(this.pedidos);
    }

    public String volver() {
        this.cliente = new Cliente();
        this.pedidos = new Pedidos();
        this.empleado = new Empleado();
        return "listar";
    }

    public void agregarProducto(Productos pro) {
        System.out.println("ID PRODUCTO ======" + pro.getIdProducto());
        System.out.println("descripcion ======" + pro.getDescripcion());
        System.out.println("img ======" + pro.getIamgenesp());
        System.out.println("img ======" + pro.getCodBarras());
        System.out.println("img ======" + pro.getValorunitario());
        
        System.out.println("img ======" + pro.getDetallefacturaList());
        System.out.println("img ======" + pro.getInsumosList());
        setProductos(pro);
        this.detallefactura.setIdProducto(getProductos());
        this.detallefactura.setCantidad(this.cantidad);
        this.detallefactura.setPorcentajeIva(19);
        double precioTotal = this.cantidad * pro.getValorunitario() * 0.19;
        int precio = (int) Math.round(precioTotal);
        this.detallefactura.setPrecioTotal(precio);
        this.listaDetalle.add(getDetallefactura());
    }

}
