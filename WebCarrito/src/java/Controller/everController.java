/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.everDAO;
import Entidades.TProveedores;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Este archivo se hizo porque nunca se obtuvo proveedoresController con la finalidad de evitar conflictos al hacer push y pull
 * @author MURCIA
 */
@ManagedBean 
@SessionScoped
public class everController {

    /**
     * Creates a new instance of everController
     */
    @EJB
    private everDAO everDAO;
    private TProveedores itemNuevo;
    private List<TProveedores> lista;  
    static TProveedores itemSeleccionado;    ///Revisar si lleva public o static
    
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public List<TProveedores> getLista() {
        return lista;
    }

    public void setLista(List<TProveedores> lista) {
        this.lista = lista;
    }
    
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    
    public TProveedores getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TProveedores itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public TProveedores getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TProveedores();
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TProveedores itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    
    
    public void buscar() {
        try {
            this.lista = everDAO.getFindAll();            
        } catch (Exception e) {
            System.out.println("ERROR buscar:   " + e.getMessage());
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    
    @PostConstruct
    public void init(){
        buscar();//Carga los datos de fondo una vez iniciado el facelet
    }
    
    public everController() {
        itemNuevo = new TProveedores();
        itemSeleccionado = new TProveedores();
    }
    
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////Mensajes////////////////////////////////////////////
    public void error() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensaje", "Datos no guardados"));
    }

    public void save() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensaje", "Datos Guardados con exito"));
    }
    //////////////////////////Mensajes////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
}
