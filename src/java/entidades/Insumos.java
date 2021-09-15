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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APRENDIZ
 */
@Entity
@Table(name = "insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumos.findAll", query = "SELECT i FROM Insumos i")
    , @NamedQuery(name = "Insumos.findByIdInsumos", query = "SELECT i FROM Insumos i WHERE i.idInsumos = :idInsumos")
    , @NamedQuery(name = "Insumos.findByNombreInsumo", query = "SELECT i FROM Insumos i WHERE i.nombreInsumo = :nombreInsumo")
    , @NamedQuery(name = "Insumos.findByCantidad", query = "SELECT i FROM Insumos i WHERE i.cantidad = :cantidad")})
public class Insumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_insumos")
    private Long idInsumos;
    @Size(max = 50)
    @Column(name = "nombreInsumo")
    private String nombreInsumo;
    @Column(name = "cantidad")
    private Integer cantidad;
    @ManyToMany(mappedBy = "insumosList", fetch = FetchType.LAZY)
    private List<Productos> productosList;

    public Insumos() {
    }

    public Insumos(Long idInsumos) {
        this.idInsumos = idInsumos;
    }

    public Long getIdInsumos() {
        return idInsumos;
    }

    public void setIdInsumos(Long idInsumos) {
        this.idInsumos = idInsumos;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumos != null ? idInsumos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumos)) {
            return false;
        }
        Insumos other = (Insumos) object;
        if ((this.idInsumos == null && other.idInsumos != null) || (this.idInsumos != null && !this.idInsumos.equals(other.idInsumos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Insumos[ idInsumos=" + idInsumos + " ]";
    }
    
}
