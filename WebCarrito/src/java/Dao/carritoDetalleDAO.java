/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.TProductos;
import java.math.BigDecimal;
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
public class carritoDetalleDAO {

    @PersistenceContext(unitName = "WebCarritoPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public Integer totalUnidadesVendidas(Integer tProductos) {
        String consulta;
        Integer resultado = 0;
        try {
            consulta = "SELECT IF(SUM(cd.cantidad_carrito_detalle)<>'NULL',SUM(cd.cantidad_carrito_detalle),0) as suma FROM t_carrito_detalle as cd WHERE cd.id_producto = " + tProductos;
            Query query = em.createNativeQuery(consulta);
            resultado = ((BigDecimal) (query.getSingleResult())).intValue();
            
            
            ///////////////////////////////////////////////////////////////////////
            ///////////////ADITIONAL INFO FOR getSingleResult()////////////////////
            //resultado = ((BigDecimal)((List)query.getSingleResult()).get(0)).doubleValue();
            //Supuestamente siempre devuelve un resultado de tipo Big        Decimal
            //Se castea a list para acceder al primer registro con get(0) pero no es necesario con getSingleResult()
            //Me dio error hacer esto            
            ///////////////FIN ADITIONAL INFO FOR getSingleResult()////////////////
            ///////////////////////////////////////////////////////////////////////
            
            
            
            ///////////////////////////////////////////////////////////////////////
            ///////////////ADITIONAL INFO ////////////////////////////////////////
            //Para hacer lo mismo pero con una entidad entera devuelta se hace:
            /*
             String consulta="select * from persona";
             Query q=em.createNativeQuery(consulta,com.paquete.modelo.Persona.class);
             Persona[] personas=(Persona[])q.getResultList().toArray(new Persona[0]);//Arreglo de tipo persona
             */
            /////////////FIN ADITIONAL INFO///////////////////////////////////////
            //////////////////////////////////////////////////////////////////////
            
            
        } catch (Exception e) {
            System.out.println("Error Consulta totalUnidadesVenditas: " + e.getMessage());
        }
        return resultado;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
