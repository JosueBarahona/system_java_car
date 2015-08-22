/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Josue
 */
@Entity
@Table(name = "t_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TProductos.findAll", query = "SELECT t FROM TProductos t"),
    @NamedQuery(name = "TProductos.findAllSum", query = "SELECT t, d.suma FROM TProductos t,(SELECT cd.idProducto, SUM(cd.cantidadCarritoDetalle) suma FROM TCarritoDetalle cd GROUP BY cd.idProducto) d WHERE t.idProducto = d.idProducto"),
    @NamedQuery(name = "TProductos.findByIdProducto", query = "SELECT t FROM TProductos t WHERE t.idProducto = :idProducto"),
    @NamedQuery(name = "TProductos.findByCantidadProducto", query = "SELECT t FROM TProductos t WHERE t.cantidadProducto = :cantidadProducto"),
    @NamedQuery(name = "TProductos.findByPrecioProducto", query = "SELECT t FROM TProductos t WHERE t.precioProducto = :precioProducto"),
    @NamedQuery(name = "TProductos.findByNombreProducto", query = "SELECT t FROM TProductos t WHERE t.nombreProducto = :nombreProducto"),
    @NamedQuery(name = "TProductos.findByDescripcionProducto", query = "SELECT t FROM TProductos t WHERE t.descripcionProducto = :descripcionProducto"),
    @NamedQuery(name = "TProductos.findByImagenProducto", query = "SELECT t FROM TProductos t WHERE t.imagenProducto = :imagenProducto")})
public class TProductos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private Integer idProducto;
    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_producto")
    private Double precioProducto;
    @Size(max = 150)
    @Column(name = "nombre_producto")
    private String nombreProducto;
    @Size(max = 150)
    @Column(name = "descripcion_producto")
    private String descripcionProducto;
    @Size(max = 500)
    @Column(name = "imagen_producto")
    private String imagenProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<TCarritoDetalle> tCarritoDetalleList;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne
    private TProveedores idProveedor;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private TCategorias idCategoria;

    public TProductos() {
    }

    public TProductos(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    @XmlTransient
    public List<TCarritoDetalle> getTCarritoDetalleList() {
        return tCarritoDetalleList;
    }

    public void setTCarritoDetalleList(List<TCarritoDetalle> tCarritoDetalleList) {
        this.tCarritoDetalleList = tCarritoDetalleList;
    }

    public TProveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(TProveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public TCategorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(TCategorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TProductos)) {
            return false;
        }
        TProductos other = (TProductos) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TProductos[ idProducto=" + idProducto + " ]";
    }
    
}
