/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Josue
 */
@Entity
@Table(name = "t_carrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCarrito.findAll", query = "SELECT t FROM TCarrito t"),
    @NamedQuery(name = "TCarrito.findByIdCarrito", query = "SELECT t FROM TCarrito t WHERE t.idCarrito = :idCarrito"),
    @NamedQuery(name = "TCarrito.findByFehaCarrito", query = "SELECT t FROM TCarrito t WHERE t.fehaCarrito = :fehaCarrito"),
    @NamedQuery(name = "TCarrito.findByNotaCarrito", query = "SELECT t FROM TCarrito t WHERE t.notaCarrito = :notaCarrito"),
    @NamedQuery(name = "TCarrito.findByEstadoCarrito", query = "SELECT t FROM TCarrito t WHERE t.estadoCarrito = :estadoCarrito")})
public class TCarrito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carrito")
    private Integer idCarrito;
    @Column(name = "feha_carrito")
    @Temporal(TemporalType.DATE)
    private Date fehaCarrito;
    @Size(max = 200)
    @Column(name = "nota_carrito")
    private String notaCarrito;
    @Column(name = "estado_carrito")
    private Integer estadoCarrito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrito")
    private List<TCarritoDetalle> tCarritoDetalleList;
    @JoinColumn(name = "id_usuaio", referencedColumnName = "id_usuario")
    @ManyToOne
    private TUsuarios idUsuaio;

    public TCarrito() {
    }

    public TCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Date getFehaCarrito() {
        return fehaCarrito;
    }

    public void setFehaCarrito(Date fehaCarrito) {
        this.fehaCarrito = fehaCarrito;
    }

    public String getNotaCarrito() {
        return notaCarrito;
    }

    public void setNotaCarrito(String notaCarrito) {
        this.notaCarrito = notaCarrito;
    }

    public Integer getEstadoCarrito() {
        return estadoCarrito;
    }

    public void setEstadoCarrito(Integer estadoCarrito) {
        this.estadoCarrito = estadoCarrito;
    }

    @XmlTransient
    public List<TCarritoDetalle> getTCarritoDetalleList() {
        return tCarritoDetalleList;
    }

    public void setTCarritoDetalleList(List<TCarritoDetalle> tCarritoDetalleList) {
        this.tCarritoDetalleList = tCarritoDetalleList;
    }

    public TUsuarios getIdUsuaio() {
        return idUsuaio;
    }

    public void setIdUsuaio(TUsuarios idUsuaio) {
        this.idUsuaio = idUsuaio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrito != null ? idCarrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCarrito)) {
            return false;
        }
        TCarrito other = (TCarrito) object;
        if ((this.idCarrito == null && other.idCarrito != null) || (this.idCarrito != null && !this.idCarrito.equals(other.idCarrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TCarrito[ idCarrito=" + idCarrito + " ]";
    }
    
}
