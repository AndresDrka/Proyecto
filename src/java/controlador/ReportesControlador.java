/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import facades.*;
import entidades.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author uribe
 */
@Named(value = "reportesControlador")
@RequestScoped
public class ReportesControlador {

    /**
     * Creates a new instance of ReportesControlador
     */
    public ReportesControlador() {
    }
    @EJB
    UsuariosFacade usuariofacade;
    private List<Usuarios> listaUsuarios;

    public List<Usuarios> listarUsuarios() {

        listaUsuarios = usuariofacade.findAll();
        return listaUsuarios;
    }

    public void generarPDF(ActionEvent actionEvent) throws JRException {
        //Genera un hashmap  para los parametros del reporte
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUsuario", "Wilmer");
        //Genera la lista para los fields del reporte
        listarUsuarios();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaUsuarios);

        //Traer la ruta del Jasper
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/");

        try {
            //Generar reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(ruta + "/ReportePrueba.jasper", parametros, beanCollectionDataSource);
            
            //con esta linea mi navegador puede leer el PDF  y lo puede descargar;
            HttpServletResponse httpServletResponse  = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=ReporteUsuarios.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

}
