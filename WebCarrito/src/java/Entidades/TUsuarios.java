/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Josue
 */
@Entity
@Table(name = "t_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUsuarios.findAll", query = "SELECT t FROM TUsuarios t"),
    @NamedQuery(name = "TUsuarios.findByIdUsuario", query = "SELECT t FROM TUsuarios t WHERE t.idUsuario = :idUsuario"),
    @NamedQuery(name = "TUsuarios.findByNombreUsuario", query = "SELECT t FROM TUsuarios t WHERE t.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "TUsuarios.findByNicknameUsuario", query = "SELECT t FROM TUsuarios t WHERE t.nicknameUsuario = :nicknameUsuario"),
    @NamedQuery(name = "TUsuarios.findByCorreoUsuario", query = "SELECT t FROM TUsuarios t WHERE t.correoUsuario = :correoUsuario"),
    @NamedQuery(name = "TUsuarios.findByRolUsuario", query = "SELECT t FROM TUsuarios t WHERE t.rolUsuario = :rolUsuario"),
    @NamedQuery(name = "TUsuarios.findByEstadoUsuario", query = "SELECT t FROM TUsuarios t WHERE t.estadoUsuario = :estadoUsuario"),
    @NamedQuery(name = "TUsuarios.findByPassUsuario", query = "SELECT t FROM TUsuarios t WHERE t.passUsuario = :passUsuario")})
public class TUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 150)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Size(max = 100)
    @Column(name = "nickname_usuario")
    private String nicknameUsuario;
    @Size(max = 100)
    @Column(name = "correo_usuario")
    private String correoUsuario;
    @Size(max = 15)
    @Column(name = "rol_usuario")
    private String rolUsuario;
    @Column(name = "estado_usuario")
    private Integer estadoUsuario;
    @Size(max = 100)
    @Column(name = "pass_usuario")
    private String passUsuario;
    @OneToMany(mappedBy = "idUsuaio")
    private List<TCarrito> tCarritoList;

    public TUsuarios() {
    }

    public TUsuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Integer getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Integer estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    @XmlTransient
    public List<TCarrito> getTCarritoList() {
        return tCarritoList;
    }

    public void setTCarritoList(List<TCarrito> tCarritoList) {
        this.tCarritoList = tCarritoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUsuarios)) {
            return false;
        }
        TUsuarios other = (TUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TUsuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
