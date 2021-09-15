/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APRENDIZ
 */
@Entity
@Table(name = "calidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calidad.findAll", query = "SELECT c FROM Calidad c")
    , @NamedQuery(name = "Calidad.findByIdCalidad", query = "SELECT c FROM Calidad c WHERE c.idCalidad = :idCalidad")
    , @NamedQuery(name = "Calidad.findByTipocalidad", query = "SELECT c FROM Calidad c WHERE c.tipocalidad = :tipocalidad")})
public class Calidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calidad")
    private Long idCalidad;
    @Size(max = 100)
    @Column(name = "Tipocalidad")
    private String tipocalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCalidad", fetch = FetchType.LAZY)
    private List<CalidadProduccion> calidadProduccionList;

    public Calidad() {
    }

    public Calidad(Long idCalidad) {
        this.idCalidad = idCalidad;
    }

    public Long getIdCalidad() {
        return idCalidad;
    }

    public void setIdCalidad(Long idCalidad) {
        this.idCalidad = idCalidad;
    }

    public String getTipocalidad() {
        return tipocalidad;
    }

    public void setTipocalidad(String tipocalidad) {
        this.tipocalidad = tipocalidad;
    }

    @XmlTransient
    public List<CalidadProduccion> getCalidadProduccionList() {
        return calidadProduccionList;
    }

    public void setCalidadProduccionList(List<CalidadProduccion> calidadProduccionList) {
        this.calidadProduccionList = calidadProduccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalidad != null ? idCalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calidad)) {
            return false;
        }
        Calidad other = (Calidad) object;
        if ((this.idCalidad == null && other.idCalidad != null) || (this.idCalidad != null && !this.idCalidad.equals(other.idCalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Calidad[ idCalidad=" + idCalidad + " ]";
    }
    
}
