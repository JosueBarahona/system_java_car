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
@Table(name = "t_proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TProveedores.findAll", query = "SELECT t FROM TProveedores t"),
    @NamedQuery(name = "TProveedores.findByIdProveedor", query = "SELECT t FROM TProveedores t WHERE t.idProveedor = :idProveedor"),
    @NamedQuery(name = "TProveedores.findByNombreProveedor", query = "SELECT t FROM TProveedores t WHERE t.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "TProveedores.findByNitProveedor", query = "SELECT t FROM TProveedores t WHERE t.nitProveedor = :nitProveedor"),
    @NamedQuery(name = "TProveedores.findByNrcProveedor", query = "SELECT t FROM TProveedores t WHERE t.nrcProveedor = :nrcProveedor"),
    @NamedQuery(name = "TProveedores.findByDireccionProveedor", query = "SELECT t FROM TProveedores t WHERE t.direccionProveedor = :direccionProveedor"),
    @NamedQuery(name = "TProveedores.findByTelefonoProveedor", query = "SELECT t FROM TProveedores t WHERE t.telefonoProveedor = :telefonoProveedor")})
public class TProveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Size(max = 100)
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Size(max = 18)
    @Column(name = "nit_proveedor")
    private String nitProveedor;
    @Size(max = 10)
    @Column(name = "nrc_proveedor")
    private String nrcProveedor;
    @Size(max = 255)
    @Column(name = "direccion_proveedor")
    private String direccionProveedor;
    @Size(max = 9)
    @Column(name = "telefono_proveedor")
    private String telefonoProveedor;
    @OneToMany(mappedBy = "idProveedor")
    private List<TProductos> tProductosList;

    public TProveedores() {
    }

    public TProveedores(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getNrcProveedor() {
        return nrcProveedor;
    }

    public void setNrcProveedor(String nrcProveedor) {
        this.nrcProveedor = nrcProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    @XmlTransient
    public List<TProductos> getTProductosList() {
        return tProductosList;
    }

    public void setTProductosList(List<TProductos> tProductosList) {
        this.tProductosList = tProductosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TProveedores)) {
            return false;
        }
        TProveedores other = (TProveedores) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TProveedores[ idProveedor=" + idProveedor + " ]";
    }
    
}
