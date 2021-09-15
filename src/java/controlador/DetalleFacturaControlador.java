/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Detallefactura;
import entidades.Pedidos;
import entidades.Productos;
import facades.DetallefacturaFacade;
import facades.PedidosFacade;
import facades.ProductosFacade;
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
@Named(value = "detalleFacturaControlador")
@SessionScoped
public class DetalleFacturaControlador implements Serializable {

    /**
     * Creates a new instance of DetalleFacturaControlador
     */
    public DetalleFacturaControlador() {
    }
    
    @EJB
    ProductosFacade productosFacade;
    private Productos productos;
    
    @EJB
    PedidosFacade pedidosFacade;
    private Pedidos Pedidos;
    
    @EJB
    DetallefacturaFacade  detallefacturaFacade;
    private Detallefactura  detallefactura;

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Pedidos getPedidos() {
        return Pedidos;
    }

    public void setPedidos(Pedidos Pedidos) {
        this.Pedidos = Pedidos;
    }

    public Detallefactura getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(Detallefactura detallefactura) {
        this.detallefactura = detallefactura;
    }
    @PostConstruct
    public void inin(){
        
        this.productos  = new Productos() ;
        this.Pedidos  = new Pedidos();
        this.detallefactura = new Detallefactura() ;
    }
    
    public String crear() {
        
       detallefactura.setNumPedido( pedidosFacade.find(Pedidos.getNumPedido()));
       detallefactura.setIdProducto(productosFacade.find(productos.getIdProducto()));
       detallefacturaFacade.create(detallefactura);
         detallefactura = new Detallefactura();
         productos = new Productos();
         Pedidos = new Pedidos();
     
        return "listar";
    }
    public String eliminar (Detallefactura detalleFactura){
        
        detallefacturaFacade.remove(detalleFactura);
        detalleFactura = new Detallefactura();
        return"listar" ;   
        
    }
        public String preEditar (Detallefactura detalleFactura)  {
            this.detallefactura =detalleFactura;
            return "editarDetalle";
       
    }
        
     public String editar () {
        
       detallefactura.setNumPedido( pedidosFacade.find(Pedidos.getNumPedido()));
       detallefactura.setIdProducto(productosFacade.find(productos.getIdProducto()));
       detallefacturaFacade.create(detallefactura);
         detallefactura = new Detallefactura();
         productos = new Productos();
         Pedidos = new Pedidos();
     
        return "listar";
    }
    
      public List<Detallefactura> consultar() {
        return detallefacturaFacade.findAll();
    }

    public void consultarId() {
        detallefactura = detallefacturaFacade.find(this.detallefactura);
    }

    public String volver() {
        this.Pedidos = new Pedidos();
        this.productos = new Productos();
        this.detallefactura = new Detallefactura();
        return "detalle";
    }
    
    
}
