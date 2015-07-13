/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Josue
 */
@Entity
@Table(name = "t_carrito_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCarritoDetalle.findAll", query = "SELECT t FROM TCarritoDetalle t"),
    @NamedQuery(name = "TCarritoDetalle.findByCantidadCarritoDetalle", query = "SELECT t FROM TCarritoDetalle t WHERE t.cantidadCarritoDetalle = :cantidadCarritoDetalle"),
    @NamedQuery(name = "TCarritoDetalle.findByIdCarritoDetalle", query = "SELECT t FROM TCarritoDetalle t WHERE t.idCarritoDetalle = :idCarritoDetalle")})
public class TCarritoDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_carrito_detalle")
    private int cantidadCarritoDetalle;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carrito_detalle")
    private Integer idCarritoDetalle;
    @JoinColumn(name = "id_carrito", referencedColumnName = "id_carrito")
    @ManyToOne(optional = false)
    private TCarrito idCarrito;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private TProductos idProducto;

    public TCarritoDetalle() {
    }

    public TCarritoDetalle(Integer idCarritoDetalle) {
        this.idCarritoDetalle = idCarritoDetalle;
    }

    public TCarritoDetalle(Integer idCarritoDetalle, int cantidadCarritoDetalle) {
        this.idCarritoDetalle = idCarritoDetalle;
        this.cantidadCarritoDetalle = cantidadCarritoDetalle;
    }

    public int getCantidadCarritoDetalle() {
        return cantidadCarritoDetalle;
    }

    public void setCantidadCarritoDetalle(int cantidadCarritoDetalle) {
        this.cantidadCarritoDetalle = cantidadCarritoDetalle;
    }

    public Integer getIdCarritoDetalle() {
        return idCarritoDetalle;
    }

    public void setIdCarritoDetalle(Integer idCarritoDetalle) {
        this.idCarritoDetalle = idCarritoDetalle;
    }

    public TCarrito getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(TCarrito idCarrito) {
        this.idCarrito = idCarrito;
    }

    public TProductos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(TProductos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarritoDetalle != null ? idCarritoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCarritoDetalle)) {
            return false;
        }
        TCarritoDetalle other = (TCarritoDetalle) object;
        if ((this.idCarritoDetalle == null && other.idCarritoDetalle != null) || (this.idCarritoDetalle != null && !this.idCarritoDetalle.equals(other.idCarritoDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TCarritoDetalle[ idCarritoDetalle=" + idCarritoDetalle + " ]";
    }
    
}
