/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.TProductos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MURCIA
 */
@Stateless
public class carritoDetalleDAO {
    @PersistenceContext(unitName = "WebCarritoPU")
    private EntityManager em;
    
    
    public void persist(Object object){
        em.persist(object);  
    }
    
    public Integer totalUnidadesVendidas(TProductos tProductos){
        String consulta;
        Integer resultado = 0;
        try{
            consulta = "SELECT SUM(cd.cantidadCarritoDetalle) FROM TCarritoDetalle cd WHERE cd.idProducto = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, tProductos.getIdProducto());
            resultado = (Integer)query.getSingleResult();
        }catch(Exception e){
            System.out.println("Error Consulta totalUnidadesVenditas: "+ e.getMessage());
        }
        return resultado;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
