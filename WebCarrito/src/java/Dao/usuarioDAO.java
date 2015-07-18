/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entidades.TProductos;
import Entidades.TUsuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Josue
 */
@Stateless
public class usuarioDAO {
    
    @PersistenceContext(unitName = "WebCarritoPU")
    private EntityManager em;
    
    
    public void persist(Object object){
        em.persist(object);  
    }
    
    public List<TUsuarios> getBuscarUsuario(String usuario){
        List <TUsuarios> lista = null;
        Query q = em.createNamedQuery("TUsuarios.findByNombreUsuario2");
        q.setParameter("nombre", "%".concat(usuario).concat("%"));
        lista = q.getResultList();
        return lista;
    }
    
    public List<TUsuarios> getFindAll(){
        List<TUsuarios> lista = null;
        Query q = em.createNamedQuery("TUsuarios.findAll");
        lista= q.getResultList();
        return lista;
    }
    
    public boolean modificaUsuarios(TUsuarios usuario){//DEvuelve true si se guarda
        try{
            em.merge(usuario);
            return true;
        }catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
