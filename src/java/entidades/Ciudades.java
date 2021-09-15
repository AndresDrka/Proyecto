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
@Table(name = "ciudades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudades.findAll", query = "SELECT c FROM Ciudades c")
    , @NamedQuery(name = "Ciudades.findByIdciudades", query = "SELECT c FROM Ciudades c WHERE c.idciudades = :idciudades")
    , @NamedQuery(name = "Ciudades.findByCiudades", query = "SELECT c FROM Ciudades c WHERE c.ciudades = :ciudades")})
public class Ciudades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_ciudades")
    private long idciudades;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Ciudades")
    private String ciudades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCiudades", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;

    public Ciudades() {
    }

    public Ciudades(long idciudades) {
        this.idciudades = idciudades;
    }

    public Ciudades(Long idciudades, String ciudades) {
        this.idciudades = idciudades;
        this.ciudades = ciudades;
    }

    public long getIdciudades() {
        return idciudades;
    }

    public void setIdciudades(long idciudades) {
        this.idciudades = idciudades;
    }

    public String getCiudades() {
        return ciudades;
    }

    public void setCiudades(String ciudades) {
        this.ciudades = ciudades;
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
        int hash = 7;
        hash = 79 * hash + (int) (this.idciudades ^ (this.idciudades >>> 32));
        hash = 79 * hash + Objects.hashCode(this.ciudades);
        hash = 79 * hash + Objects.hashCode(this.usuariosList);
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
        final Ciudades other = (Ciudades) obj;
        if (this.idciudades != other.idciudades) {
            return false;
        }
        if (!Objects.equals(this.ciudades, other.ciudades)) {
            return false;
        }
        if (!Objects.equals(this.usuariosList, other.usuariosList)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "entidades.Ciudades[ idciudades=" + idciudades + " ]";
    }
    
}
