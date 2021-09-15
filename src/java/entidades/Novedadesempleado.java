/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APRENDIZ
 */
@Entity
@Table(name = "novedadesempleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Novedadesempleado.findAll", query = "SELECT n FROM Novedadesempleado n")
    , @NamedQuery(name = "Novedadesempleado.findByIdNovedad", query = "SELECT n FROM Novedadesempleado n WHERE n.idNovedad = :idNovedad")
    , @NamedQuery(name = "Novedadesempleado.findByBpm", query = "SELECT n FROM Novedadesempleado n WHERE n.bpm = :bpm")
    , @NamedQuery(name = "Novedadesempleado.findByObservaciones", query = "SELECT n FROM Novedadesempleado n WHERE n.observaciones = :observaciones")})
public class Novedadesempleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_novedad")
    private Long idNovedad;
    @Size(max = 100)
    @Column(name = "BPM")
    private String bpm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinTable(name = "empleados_novedades", joinColumns = {
        @JoinColumn(name = "id_novedad", referencedColumnName = "id_novedad")}, inverseJoinColumns = {
        @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Empleado> empleadoList;

    public Novedadesempleado() {
    }

    public Novedadesempleado(Long idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Novedadesempleado(Long idNovedad, String observaciones) {
        this.idNovedad = idNovedad;
        this.observaciones = observaciones;
    }

    public Long getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Long idNovedad) {
        this.idNovedad = idNovedad;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNovedad != null ? idNovedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Novedadesempleado)) {
            return false;
        }
        Novedadesempleado other = (Novedadesempleado) object;
        if ((this.idNovedad == null && other.idNovedad != null) || (this.idNovedad != null && !this.idNovedad.equals(other.idNovedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Novedadesempleado[ idNovedad=" + idNovedad + " ]";
    }
    
}
