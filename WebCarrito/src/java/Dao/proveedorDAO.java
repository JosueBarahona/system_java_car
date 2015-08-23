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
 *
 * @author mundial
 */
@Stateless
public class proveedorDAO {
  @PersistenceContext(unitName = "WebCarritoPU")
    private EntityManager em;
    
    
    public void persist(Object object){
        em.persist(object);  
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<TProveedores> getBuscarProveedor(String proveedor){
        List <TProveedores> lista = null;
        Query q = em.createNamedQuery("TProveedores.findByNombreProveedor2");
        q.setParameter("nombre", "%".concat(proveedor).concat("%"));
        lista = q.getResultList();
        return lista;
    }
    
    public List<TProveedores> getFindAll(){
        List<TProveedores> lista = null;
        Query q = em.createNamedQuery("TProveedores.findAll");
        lista= q.getResultList();
        return lista;
    }
    
    public boolean modificaProveedores(TProveedores proveedor){//DEvuelve true si se guarda
        try{
            em.merge(proveedor);//merge se ocupa para modificar editar  q
            return true;
        }catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }
}
