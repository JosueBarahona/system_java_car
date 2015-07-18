/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.TUsuarios;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Josue
 */
@Stateless
public class loginDAO {
    
    @PersistenceContext(unitName = "WebCarritoPU")
    
 
    
    private EntityManager em;
    public void persist(Object object) {
        em.persist(object);
    }

    public Integer getLogin(String usuario, String password) {
        Query q = em.createNamedQuery("TUsuarios.login");
        q.setParameter("usuario", usuario);
        q.setParameter("password", password);
        int result = q.getResultList().size();
        System.out.println("Resultados " + result);
        return result;
    }

    public List<TUsuarios> buscarPorNombre(String usuario, String password) {
        Query q = em.createNamedQuery("TUsuarios.buscaNombre");
        q.setParameter("usuario", usuario);//AQUI LE MANDO A LA ENTIDAD LOS PARAMETRO DE BUSQUEDA EN EL WHERE.
        q.setParameter("password", password);

        String result = q.getResultList().toString();

        System.out.println("Nombre: " + result);
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO", result);
        return q.getResultList();
    }
    
    public List<TUsuarios> buscarRol(String usuario, String password) {
        Query q = em.createNamedQuery("TUsuarios.rol");
        q.setParameter("usuario", usuario);//AQUI LE MANDO A LA ENTIDAD LOS PARAMETRO DE BUSQUEDA EN EL WHERE.
        q.setParameter("password", password);

        String result = q.getResultList().toString();
        
        try{
            if(result.equals("[Usuario]")){
                System.out.println("Rol: " + result);
              
                //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("window.location.href='ingresar_usuario.xhtml'; return false;");
            } else{
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("window.location.href='ingresar_usuario_adm.xhtml'; return false;");
            }
        }catch(Exception e){
            System.out.println("Error"+e.getMessage());
        }
        
        return q.getResultList();
    }

 

}
