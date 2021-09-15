/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Calidad;
import entidades.CalidadProduccion;
import entidades.EtapaProduccion;
import entidades.Produccion;
import facades.CalidadFacade;
import facades.CalidadProduccionFacade;
import facades.EtapaProduccionFacade;
import facades.ProduccionFacade;
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
@Named(value = "calidadProduccionControlador")
@SessionScoped
public class CalidadProduccionControlador implements Serializable {

    /**
     * Creates a new instance of CalidadProduccionControlador
     */
    public CalidadProduccionControlador() {
    }
    @EJB
    private ProduccionFacade produccionFacade;
    Produccion produccion;
    @EJB
    private CalidadFacade calidadFacade;
    Calidad calidad;
    @EJB
    private CalidadProduccionFacade caliProFacade;
    CalidadProduccion calidadPro;
    @EJB
    private EtapaProduccionFacade etapaProduccionFacade;
    EtapaProduccion etapaProduccion;

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

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public Calidad getCalidad() {
        return calidad;
    }

    public void setCalidad(Calidad calidad) {
        this.calidad = calidad;
    }

    public CalidadProduccion getCalidadPro() {
        return calidadPro;
    }

    public void setCalidadPro(CalidadProduccion calidadPro) {
        this.calidadPro = calidadPro;
    }

    public EtapaProduccion getEtapaProduccion() {
        return etapaProduccion;
    }

    public void setEtapaProduccion(EtapaProduccion etapaProduccion) {
        this.etapaProduccion = etapaProduccion;
    }

    @PostConstruct
    public void init() {

        this.produccion = new Produccion();
        this.calidad = new Calidad();
        this.calidadPro = new CalidadProduccion();
        this.etapaProduccion = new EtapaProduccion();

    }

    public String upload() {

        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/Proyecto/Archivos/" + nombre;
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
        this.calidadPro.setImagenes(pathReal);
        calidadPro.setIdProduccion(produccionFacade.find(produccion.getIdProduccion()));
        calidadPro.setIdEtapa(etapaProduccionFacade.find(etapaProduccion.getIdEtapa()));
        calidadPro.setIdCalidad(calidadFacade.find(calidad.getIdCalidad()));
        caliProFacade.create(calidadPro);
        produccion = new Produccion();
        etapaProduccion = new EtapaProduccion();
        this.calidadPro = new CalidadProduccion();
        return "listar";
    }

    public String eliminar(CalidadProduccion calidadPro) {
        caliProFacade.remove(calidadPro);
        calidadPro = new CalidadProduccion();
        return "listar";
    }

    public String preEditar(CalidadProduccion calidadPro) {
        this.calidadPro = calidadPro;
        return "editardetalle";
    }

    public String editUpload() {

        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/Proyecto/Archivos/" + nombre;
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
        this.calidadPro.setImagenes(pathReal);

        calidadPro.setIdProduccion(produccionFacade.find(produccion.getIdProduccion()));
        calidadPro.setIdEtapa(etapaProduccionFacade.find(etapaProduccion.getIdEtapa()));
        calidadPro.setIdProduccion(produccionFacade.find(produccion.getIdProducto()));
        calidadPro.setIdCalidad(calidadFacade.find(calidad.getIdCalidad()));
        caliProFacade.edit(calidadPro);
        this.calidadPro = new CalidadProduccion();
        return "listar";
    }

    public List<CalidadProduccion> consultar() {
        return caliProFacade.findAll();
    }

    public void consultarId() {
        calidadPro = caliProFacade.find(this.calidadPro);
    }

    public String volver() {
        this.calidad = new Calidad();
        this.produccion = new Produccion();
        this.calidadPro = new CalidadProduccion();
        return "detalle";
    }
}
