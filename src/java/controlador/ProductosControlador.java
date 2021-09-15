/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Productos;
import facades.ProductosFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Myriam
 */
@Named(value = "productosControlador")
@SessionScoped
public class ProductosControlador implements Serializable {

    /**
     * Creates a new instance of ProductosControlador
     */
    public ProductosControlador() {
    }
    @EJB
    ProductosFacade productosFacade;
    private Productos productos;

    private Part file;
    private String nombre;
    private String pathReal;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    @PostConstruct
    public void init() {
        this.productos = new Productos();
    }

    public String upload() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("archivo");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\archivo\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/Proyecto/archivo/" + nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.productos.setIamgenesp(pathReal);
        productosFacade.create(productos);
        productos = new Productos();
        return "listar";
    }

    public String eliminar(Productos productos) {
        productosFacade.remove(productos);
        productos = new Productos();
        return "listar";
    }

    public String preEditar(Productos productos) {
        this.productos = productos;
        return "editar";
    }

    public String editUpload() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("archivo");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\archivo\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/Proyecto/archivo/" + nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.productos.setIamgenesp(pathReal);

        productosFacade.edit(productos);
        productos = new Productos();
        return "listar";
    }

    public List<Productos> consultar() {
        return productosFacade.findAll();
    }

    public void consultarId() {
        productos = productosFacade.find(this.productos);
    }
    
    public String volver(){
        
        this.productos=new Productos();
        return "listar";
    }
}
