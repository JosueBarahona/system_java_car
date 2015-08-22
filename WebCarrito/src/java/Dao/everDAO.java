/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.TProveedores;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Este archivo se hizo porque nunca se obtuvo proveedoresDAO con la finalidad de evitar conflictos al hacer push y pull
 * @author MURCIA
 */
@Stateless
public class everDAO {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "WebCarritoPU")
    private EntityManager em;
    
    
    public void persist(Object object){
        em.persist(object);  
    }
    
    public List<TProveedores> getFindAll(){
        List<TProveedores> lista = null;
        Query q = em.createNamedQuery("TProveedores.findAll");
        lista = q.getResultList();
        return lista;
    }
}
