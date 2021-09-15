/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APRENDIZ
 */
@Entity
@Table(name = "calidad_produccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalidadProduccion.findAll", query = "SELECT c FROM CalidadProduccion c")
    , @NamedQuery(name = "CalidadProduccion.findByObservaciones", query = "SELECT c FROM CalidadProduccion c WHERE c.observaciones = :observaciones")
    , @NamedQuery(name = "CalidadProduccion.findByIdCaliPro", query = "SELECT c FROM CalidadProduccion c WHERE c.idCaliPro = :idCaliPro")})
public class CalidadProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Imagenes")
    private String imagenes;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cali_pro")
    private Long idCaliPro;
    @JoinColumn(name = "id_calidad", referencedColumnName = "id_calidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Calidad idCalidad;
    @JoinColumn(name = "id_produccion", referencedColumnName = "id_produccion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produccion idProduccion;
    @JoinColumn(name = "id_etapa", referencedColumnName = "id_etapa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EtapaProduccion idEtapa;

    public CalidadProduccion() {
    }

    public CalidadProduccion(Long idCaliPro) {
        this.idCaliPro = idCaliPro;
    }

    public CalidadProduccion(Long idCaliPro, String observaciones, String imagenes) {
        this.idCaliPro = idCaliPro;
        this.observaciones = observaciones;
        this.imagenes = imagenes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public Long getIdCaliPro() {
        return idCaliPro;
    }

    public void setIdCaliPro(Long idCaliPro) {
        this.idCaliPro = idCaliPro;
    }

    public Calidad getIdCalidad() {
        return idCalidad;
    }

    public void setIdCalidad(Calidad idCalidad) {
        this.idCalidad = idCalidad;
    }

    public Produccion getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(Produccion idProduccion) {
        this.idProduccion = idProduccion;
    }

    public EtapaProduccion getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(EtapaProduccion idEtapa) {
        this.idEtapa = idEtapa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaliPro != null ? idCaliPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalidadProduccion)) {
            return false;
        }
        CalidadProduccion other = (CalidadProduccion) object;
        if ((this.idCaliPro == null && other.idCaliPro != null) || (this.idCaliPro != null && !this.idCaliPro.equals(other.idCaliPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CalidadProduccion[ idCaliPro=" + idCaliPro + " ]";
    }
    
}
