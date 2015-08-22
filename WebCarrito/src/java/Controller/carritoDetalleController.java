/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.carritoDetalleDAO;
import Entidades.TCarritoDetalle;
import Entidades.TProductos;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author MURCIA
 */
@ManagedBean
@RequestScoped
public class carritoDetalleController {

    @EJB
    private carritoDetalleDAO carritoDetalleDAO;
    private TCarritoDetalle carritoDetalle;
    
    private Integer unidadesVendidas;

    
    
    public Integer getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(Integer unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }
    
    
    public Integer consultaUnidadesVendidas(TProductos producto){
        Integer resultado;
        resultado = carritoDetalleDAO.totalUnidadesVendidas(producto);
        return resultado;
    }
    
    
    
    public carritoDetalleController() {
        
        
    }
    
}
