/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.TCategorias;
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
public class categoriaDAO {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "WebCarritoPU")
    private EntityManager em;
    
    
    public void persist(Object object){
        em.persist(object);  
    }
    
    public List<TCategorias> getFindAll(){
        List<TCategorias> lista = null;
        Query q = em.createNamedQuery("TCategorias.findAll");
        lista = q.getResultList();
        return lista;
    }
}
