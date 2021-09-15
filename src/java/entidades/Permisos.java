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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p")
    , @NamedQuery(name = "Permisos.findByIdPermisos", query = "SELECT p FROM Permisos p WHERE p.idPermisos = :idPermisos")
    , @NamedQuery(name = "Permisos.findByNombrepermisos", query = "SELECT p FROM Permisos p WHERE p.nombrepermisos = :nombrepermisos")
    , @NamedQuery(name = "Permisos.findByIcon", query = "SELECT p FROM Permisos p WHERE p.icon = :icon")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permisos")
    private Long idPermisos;
    @Size(max = 45)
    @Column(name = "Nombre_permisos")
    private String nombrepermisos;
    @Lob
    @Size(max = 65535)
    @Column(name = "URL")
    private String url;
    @Size(max = 45)
    @Column(name = "ICON")
    private String icon;
    @JoinTable(name = "roles_has_permisos", joinColumns = {
        @JoinColumn(name = "id_permisos", referencedColumnName = "id_permisos")}, inverseJoinColumns = {
        @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Roles> rolesList;
    @OneToMany(mappedBy = "permisoPadre", fetch = FetchType.LAZY)
    private List<Permisos> permisosList;
    @JoinColumn(name = "permiso_padre", referencedColumnName = "id_permisos")
    @ManyToOne(fetch = FetchType.LAZY)
    private Permisos permisoPadre;

    public Permisos() {
    }

    public Permisos(Long idPermisos) {
        this.idPermisos = idPermisos;
    }

    public Long getIdPermisos() {
        return idPermisos;
    }

    public void setIdPermisos(Long idPermisos) {
        this.idPermisos = idPermisos;
    }

    public String getNombrepermisos() {
        return nombrepermisos;
    }

    public void setNombrepermisos(String nombrepermisos) {
        this.nombrepermisos = nombrepermisos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @XmlTransient
    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    @XmlTransient
    public List<Permisos> getPermisosList() {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList) {
        this.permisosList = permisosList;
    }

    public Permisos getPermisoPadre() {
        return permisoPadre;
    }

    public void setPermisoPadre(Permisos permisoPadre) {
        this.permisoPadre = permisoPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermisos != null ? idPermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idPermisos == null && other.idPermisos != null) || (this.idPermisos != null && !this.idPermisos.equals(other.idPermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Permisos[ idPermisos=" + idPermisos + " ]";
    }
    
}
