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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APRENDIZ
 */
@Entity
@Table(name = "detallefactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallefactura.findAll", query = "SELECT d FROM Detallefactura d")
    , @NamedQuery(name = "Detallefactura.findByIddetalle", query = "SELECT d FROM Detallefactura d WHERE d.iddetalle = :iddetalle")
    , @NamedQuery(name = "Detallefactura.findByCantidad", query = "SELECT d FROM Detallefactura d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "Detallefactura.findByPrecioTotal", query = "SELECT d FROM Detallefactura d WHERE d.precioTotal = :precioTotal")
    , @NamedQuery(name = "Detallefactura.findByPorcentajeIva", query = "SELECT d FROM Detallefactura d WHERE d.porcentajeIva = :porcentajeIva")})
public class Detallefactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_detalle")
    private Long iddetalle;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "PrecioTotal")
    private Integer precioTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PorcentajeIva")
    private int porcentajeIva;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;
    @JoinColumn(name = "num_pedido", referencedColumnName = "num_pedido")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedidos numPedido;

    public Detallefactura() {
    }

    public Detallefactura(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Detallefactura(Long iddetalle, int porcentajeIva) {
        this.iddetalle = iddetalle;
        this.porcentajeIva = porcentajeIva;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(int porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Pedidos getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(Pedidos numPedido) {
        this.numPedido = numPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactura)) {
            return false;
        }
        Detallefactura other = (Detallefactura) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallefactura[ iddetalle=" + iddetalle + " ]";
    }
    
}
