/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.categoriaDAO;
import Entidades.TCategorias;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author MURCIA
 */
@ManagedBean
@SessionScoped
public class categoriaController {

    /**
     * Creates a new instance of categoriaController
     */
    @EJB
    private categoriaDAO categoriaDAO;
    private TCategorias itemNuevo;
    private TCategorias categoria;
    private DataModel<TCategorias> listaCategoria;
    private String busqueda;

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    static TCategorias itemSeleccionado;

    public TCategorias getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TCategorias itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public TCategorias getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TCategorias();
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TCategorias itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void buscar() {
        try {
            List<TCategorias> lista = categoriaDAO.getFindAll();
            
            ListDataModel<TCategorias> modeloListaCategoria = new ListDataModel<TCategorias>(lista);
            setListaCategoria(modeloListaCategoria);
            //System.out.println("lista: " + lista );
        } catch (Exception e) {
            System.out.println("ERROR buscar:   " + e.getMessage());
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    public DataModel<TCategorias> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(DataModel<TCategorias> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    public TCategorias getCategoria() {
        return categoria;
    }

    public void setCategoria(TCategorias categoria) {
        this.categoria = categoria;
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    public categoriaController() {
        itemNuevo = new TCategorias();
        itemSeleccionado = new TCategorias();
    }
    
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    
    @PostConstruct
    public void init() {//metodo init que se ejecuta
        buscar();//Carga los datos de fondo una vez iniciado el facelet
        buscarCategorias();//Carga los datos del selectonemenu de categoria al iniciar
    }
    
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    public String agregar() {
        try {
            System.out.println("Categoria: " + itemNuevo.getNombreCategoria());
            categoriaDAO.persist(itemNuevo);
            itemNuevo = new TCategorias();
            save(); //Mensaje de Datos Guardados

        } catch (Exception e) {
            error();
            System.out.println("Error" + e.getMessage());
        }
        return "";
    }
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////Mensajes////////////////////////////////////////////
    public void error() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensaje", "Datos no guardados, error en contraseña posiblemente"));
    }

    public void save() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensaje", "Datos Guardados con exito"));
    }
    //////////////////////////Mensajes////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    
    
    //////////////////////////Verificar Seleccion////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    public void verificarSeleccion(){
        System.out.println("CategoriaSeleccionada: " + itemSeleccionado.getNombreCategoria());
    }
    //////////////////////////Verificar Seleccion////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    
    private List<TCategorias> lista2;
    
    public void buscarCategorias() {
        try {
            this.lista2 = categoriaDAO.getFindAll();   //Sólo esto se necesita hacer para cargar los datos al selectonemenu       
        } catch (Exception e) {
            System.out.println("ERROR buscar:   " + e.getMessage());
        }
    }

    public List<TCategorias> getLista2() {
        return lista2;
    }

    public void setLista2(List<TCategorias> lista2) {
        this.lista2 = lista2;
    }
    
}
