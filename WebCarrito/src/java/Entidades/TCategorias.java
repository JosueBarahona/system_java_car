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
@Table(name = "t_categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCategorias.findAll", query = "SELECT t FROM TCategorias t"),
    @NamedQuery(name = "TCategorias.findByIdCategoria", query = "SELECT t FROM TCategorias t WHERE t.idCategoria = :idCategoria"),
    @NamedQuery(name = "TCategorias.findByNombreCategoria", query = "SELECT t FROM TCategorias t WHERE t.nombreCategoria = :nombreCategoria")})
public class TCategorias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Size(max = 100)
    @Column(name = "nombre_categoria")
    private String nombreCategoria;
    @OneToMany(mappedBy = "idCategoria")
    private List<TProductos> tProductosList;

    public TCategorias() {
    }

    public TCategorias(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
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
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCategorias)) {
            return false;
        }
        TCategorias other = (TCategorias) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TCategorias[ idCategoria=" + idCategoria + " ]";
    }
    
}
