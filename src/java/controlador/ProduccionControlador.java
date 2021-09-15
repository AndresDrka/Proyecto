/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Produccion;
import entidades.Productos;
import facades.ProduccionFacade;
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
@Named(value = "produccionControlador")
@SessionScoped
public class ProduccionControlador implements Serializable {

    /**
     * Creates a new instance of ProduccionControlador
     */
    public ProduccionControlador() {
    }
   
    @EJB
    ProductosFacade productosFacade;
    private Productos productos;
    
    @EJB
    ProduccionFacade  produccionFacade;
    private Produccion   produccion;

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }
    @PostConstruct 
     public void init() {
        this.productos = new Productos();
        this.produccion = new Produccion();
    }

    public String crear() {
        produccion.setIdProducto(productosFacade.find(productos.getIdProducto()));
        produccionFacade.create(produccion);
        produccion = new Produccion();
        productos = new Productos();
        return "listar";
    }

    public String eliminar(Produccion produccion) {
        produccionFacade.remove(produccion);
        produccion = new Produccion();
        return "listar";
    }

    public String preEditar(Produccion produccion) {
        this.produccion = produccion;
        return "editar";
    }

    public String editar() {
        produccion.setIdProducto(productosFacade.find(productos.getIdProducto()));
        produccionFacade.edit(produccion);
        produccion = new Produccion();
        productos = new Productos();
        return "listar";
    }

    public List<Produccion> consultar() {
        return produccionFacade.findAll();
    }

    public void consultarId() {
        produccion = produccionFacade.find(this.produccion);
    }

    public String volver() {
        this.productos = new Productos();
        this.produccion = new Produccion();
        return "listar";
    }
  //  public String detalle(Produccion produccion){
    //    this.produccion = produccion;
      //  return "detalle";
    
    
}
