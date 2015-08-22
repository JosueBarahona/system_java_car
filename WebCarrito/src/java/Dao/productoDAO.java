/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.TProductos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MURCIA
 */
@Stateless
public class productoDAO {

    @PersistenceContext(unitName = "WebCarritoPU")
    private EntityManager em;
    
    
    public void persist(Object object){
        em.persist(object);  
    }
    
    public void merge(Object object){
        em.merge(object);
    }
    
    
    public List<TProductos> getFindAll(){
        List<TProductos> lista;
        Query q = em.createNamedQuery("TProductos.findAll");         
        lista = q.getResultList();
        return lista;
    }
    
    public List<TProductos> getFindAll(Integer orden){
        List<TProductos> lista;
        Query q;
        if (orden == 1)
            q = em.createNamedQuery("TProductos.findAll");        
        else
            q = em.createNamedQuery("TProductos.findAllOrderNombre"); 
        lista = q.getResultList();
        return lista;
    }
    
   
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
