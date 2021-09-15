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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APRENDIZ
 */
@Entity
@Table(name = "etapa_produccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtapaProduccion.findAll", query = "SELECT e FROM EtapaProduccion e")
    , @NamedQuery(name = "EtapaProduccion.findByIdEtapa", query = "SELECT e FROM EtapaProduccion e WHERE e.idEtapa = :idEtapa")
    , @NamedQuery(name = "EtapaProduccion.findByEtapa", query = "SELECT e FROM EtapaProduccion e WHERE e.etapa = :etapa")})
public class EtapaProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etapa")
    private Long idEtapa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "etapa")
    private String etapa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtapa", fetch = FetchType.LAZY)
    private List<CalidadProduccion> calidadProduccionList;

    public EtapaProduccion() {
    }

    public EtapaProduccion(Long idEtapa) {
        this.idEtapa = idEtapa;
    }

    public EtapaProduccion(Long idEtapa, String etapa) {
        this.idEtapa = idEtapa;
        this.etapa = etapa;
    }

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
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
        hash += (idEtapa != null ? idEtapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtapaProduccion)) {
            return false;
        }
        EtapaProduccion other = (EtapaProduccion) object;
        if ((this.idEtapa == null && other.idEtapa != null) || (this.idEtapa != null && !this.idEtapa.equals(other.idEtapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EtapaProduccion[ idEtapa=" + idEtapa + " ]";
    }
    
}
