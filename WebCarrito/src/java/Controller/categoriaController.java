/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.categoriaDAO;
import Entidades.TCategorias;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
    
    public TCategorias getItemSeleccionado(){
        if(itemSeleccionado == null){
            itemSeleccionado = new TCategorias();
        }
        return itemSeleccionado;
    }
    
    public void setItemSeleccionado(TCategorias itemSeleccionado){
        this.itemSeleccionado = itemSeleccionado;
    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    
    public void buscar(){
        try{
            List<TCategorias> lista = categoriaDAO.getFindAll();
            ListDataModel<TCategorias> modeloListaCategoria = new ListDataModel<TCategorias>(lista);
            setListaCategoria(modeloListaCategoria);
        }catch(Exception e){
            System.out.println("ERROR:   " + e.getMessage());
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
    
    @PostConstruct
    public void init(){//metodo init que se ejecuta
        buscar();//Carga los datos de fondo una vez iniciado el facelet
    }
    
}
