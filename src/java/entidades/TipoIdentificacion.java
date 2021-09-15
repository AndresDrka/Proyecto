/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
@Table(name = "tipo_identificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t")
    , @NamedQuery(name = "TipoIdentificacion.findByIdTpidentificacion", query = "SELECT t FROM TipoIdentificacion t WHERE t.idTpidentificacion = :idTpidentificacion")
    , @NamedQuery(name = "TipoIdentificacion.findByTipoid", query = "SELECT t FROM TipoIdentificacion t WHERE t.tipoid = :tipoid")})
public class TipoIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tpidentificacion")
    private long idTpidentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Tipo_id")
    private String tipoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTpidentificacion", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(long idTpidentificacion) {
        this.idTpidentificacion = idTpidentificacion;
    }

    public TipoIdentificacion(long idTpidentificacion, String tipoid) {
        this.idTpidentificacion = idTpidentificacion;
        this.tipoid = tipoid;
    }

    public long getIdTpidentificacion() {
        return idTpidentificacion;
    }

    public void setIdTpidentificacion(long idTpidentificacion) {
        this.idTpidentificacion = idTpidentificacion;
    }

    public String getTipoid() {
        return tipoid;
    }

    public void setTipoid(String tipoid) {
        this.tipoid = tipoid;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (this.idTpidentificacion ^ (this.idTpidentificacion >>> 32));
        hash = 71 * hash + Objects.hashCode(this.tipoid);
        hash = 71 * hash + Objects.hashCode(this.usuariosList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoIdentificacion other = (TipoIdentificacion) obj;
        if (this.idTpidentificacion != other.idTpidentificacion) {
            return false;
        }
        if (!Objects.equals(this.tipoid, other.tipoid)) {
            return false;
        }
        if (!Objects.equals(this.usuariosList, other.usuariosList)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "entidades.TipoIdentificacion[ idTpidentificacion=" + idTpidentificacion + " ]";
    }
    
}
