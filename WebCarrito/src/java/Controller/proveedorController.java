/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.proveedorDAO;
import Entidades.TProveedores;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author mundial
 */
@ManagedBean
@SessionScoped
public class proveedorController {

    @EJB
    private proveedorDAO proveedorDAO;   //EJB es una anotacion exclusiva del DAO, colocarlo justo arriba de este
    private TProveedores itemNuevo;// en itemnuevo guardo los valores en memoria para guardarlos con el persis
    private TProveedores proveedores;//para tener entrada a la estructura de la base de datos
    private String busqueda; //variable para guardar loq escribo en el tex en el momento
    private DataModel<TProveedores> ListaProveedores;//es para llenar DataTable
   /* private TProveedores proveedor;*/

    public TProveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(TProveedores proveedores) {
        this.proveedores = proveedores;
    }

    public DataModel<TProveedores> getListaProveedores() {
        return ListaProveedores;
    }

    public void setListaProveedores(DataModel<TProveedores> ListaProveedores) {
        this.ListaProveedores = ListaProveedores;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    public proveedorController() {
        itemNuevo = new TProveedores();
    }

   
    public TProveedores getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TProveedores itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    /*public TProveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(TProveedores proveedor) {
        this.proveedor = proveedor;
    }*/
   
     public String agregar(){
        try{
            
               proveedorDAO.persist(itemNuevo);
                
                itemNuevo = new TProveedores();
                save(); //Mensaje de Datos Guardados
            
                
            
        }catch(Exception e){
            error();
            System.out.println("Error"+e.getMessage());
        }
        return "";
    }
    
    public void error(){
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage("Mensaje","Datos no guardados, error en contraseña posiblemente"));
    }
    
    public void save(){
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage("Mensaje","Datos Guardados con exito"));
    }
    public String buscarProveedor() {
        List<TProveedores> lista = proveedorDAO.getBuscarProveedor(busqueda);//List es una lista q carga para mostrar en un menu intem o datatable
        ListDataModel<TProveedores> modeloListaProveedor = new ListDataModel<TProveedores>(lista);
        setListaProveedores(modeloListaProveedor);
        return "";
    }

    public void buscar() {
        try {
            List<TProveedores> lista = proveedorDAO.getFindAll();
            ListDataModel<TProveedores> modeloListaProveedores
                    = new ListDataModel<TProveedores>(lista);
            setListaProveedores(modeloListaProveedores);

        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());

        }
    }
      public void onEdit(RowEditEvent event) {
        proveedores = (TProveedores) event.getObject();// el get objec es el conjunto de toda la entidad

        boolean resultado = proveedorDAO.modificaProveedores(proveedores);
        FacesMessage msg;
        if (resultado) {
            msg = new FacesMessage("Usuario Modificado", ((TProveedores) event.getObject()).getNombreProveedor());
        } else {
            msg = new FacesMessage("No se puede actualizar", ((TProveedores) event.getObject()).getNombreProveedor());
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        buscar();
    }

    public void onCancel(RowEditEvent event) {  //Se ejecuta al hacer click en cancel
        FacesMessage msg = new FacesMessage("Actualizacion cancelada", ((TProveedores) event.getObject()).getNombreProveedor());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
    
}
