/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.productoDAO;
import Entidades.TProductos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author MURCIA
 */
@ManagedBean
@SessionScoped
public class productoController {

    /**
     * Creates a new instance of productoController
     */
    @EJB
    private productoDAO productosDAO;
    private TProductos itemNuevo;//Para ingreso de producto
    private TProductos productos;//Para modificación de producto
    private String ruta;//Para ingreso de producto y modificacion de ruta de imagen
    
    
    
    private DataModel<TProductos> listaProductos;//Listado de productos
    

    public DataModel<TProductos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(DataModel<TProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    private String busqueda;
    private FileUploadEvent event1;
    private File result;

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    public TProductos getProductos() {
        return productos;
    }

    public void setProductos(TProductos productos) {
        this.productos = productos;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public void setAllNull() {
        this.ruta = "";
        everController.itemSeleccionado = null;
        categoriaController.itemSeleccionado = null;
        //System.out.println("Entra a setRutaNull");
        //System.out.println(this.ruta);
    }

    
    
    public TProductos getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TProductos itemNuevo) {
        this.itemNuevo = itemNuevo;
    }
    
        
    public productoController() {
        itemNuevo = new TProductos();
    }
    
    public String agregar(){
        try{
            System.out.println("Entra a agregar!!!");            
            itemNuevo.setIdCategoria(categoriaController.itemSeleccionado);
            itemNuevo.setIdProveedor(everController.itemSeleccionado);
            itemNuevo.setImagenProducto(ruta);
            productosDAO.persist(itemNuevo);
            itemNuevo = new TProductos(); //Para limpiar despues de guardado 
            categoriaController.itemSeleccionado = null; //Para que despues del guardado se limpie y no apunte algun objeto
            everController.itemSeleccionado = null;      //Para que despues del guardado se limpie y no apunte algun objeto
                                                         //Ocupar ambos itemSeleccionado para el formulario de modificar apuntando
                                                         //al id correcto
            ruta=""; //Para limpiar la imagen mostrada despues de guardado
            System.out.println("finaliza agregar!!!");
            mensaje("Mensaje","Datos Guardados con éxito.");
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return "buscar_producto.xhtml?faces-redirect=true";
    }
    
    //////////////////////////////////////////
    //////////////////////////////////////////
    ///Para Preparar la edición de producto///
    //////////////////////////////////////////
    //////////////////////////////////////////
    public String preparaEditar(TProductos p) {
        this.productos = p;
        this.ruta = p.getImagenProducto();
        //everController.itemSeleccionado = p.getIdProveedor();
        //categoriaController.itemSeleccionado = p.getIdCategoria();
        
        return "editar_producto.xhtml?faces-redirect=true";
        //System.out.println(p.getNombreProducto());
    }
    //////////////////////////////////////////
    //////////////////////////////////////////
    //////////////////////////////////////////
    //////////////////////////////////////////
    
    
    //////////////////////////////////////////
    //////////////////////////////////////////
    //Edita luego de haber llenado el formulario
    //////////////////////////////////////////
    //////////////////////////////////////////
    public String editar(){
        try{
            System.out.println("Entra a editar!!!");          
            productos.setIdCategoria(categoriaController.itemSeleccionado);
            productos.setIdProveedor(everController.itemSeleccionado);
            productos.setImagenProducto(ruta);            
            productosDAO.merge(productos);
            productos = new TProductos(); //Para limpiar despues de modificar 
            categoriaController.itemSeleccionado = null; //Para que despues del modificado se limpie y no apunte algun objeto
            everController.itemSeleccionado = null;      //Para que despues del modificado se limpie y no apunte algun objeto
                                                         //Ocupar ambos itemSeleccionado para el formulario de modificar apuntando
                                                         //al id correcto
            ruta=""; //Para limpiar la imagen mostrada despues de modificar   
            System.out.println("finaliza modificar!!!");            
            mensaje("Mensaje","Datos Modificados con éxito.");
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return "buscar_producto.xhtml?faces-redirect=true";
    }
    //////////////////////////////////////////
    //////////////////////////////////////////
    //////////////////////////////////////////
    //////////////////////////////////////////
    
        
    public void mensaje(String titulo,String mensaje){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(titulo,mensaje));
    }
    //////////////////////////////////////////
    //////////////////////////////////////////
    //////////////////////////////////////////
    @PostConstruct
    public void init(){//metodo init que se ejecuta
        buscar();//Carga los datos de fondo una vez iniciado el facelet
    }
    
    public void buscar(){
        //System.out.println("Entra a productosController.buscar");
        try{
            List<TProductos> lista = productosDAO.getFindAll();
            ListDataModel<TProductos> modeloListaProveedor = new ListDataModel<TProductos>(lista);
            setListaProductos(modeloListaProveedor);
        }catch(Exception e){
            System.out.println("ERROR:   " + e.getMessage());
        }
    }
    
    
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        event1 = event;
        ruta = "/resources/imagesProductos/" + event.getFile().getFileName();
        System.out.println("Ruta:" + ruta);
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/imagesProductos/"));

        result = new File(externalContext.getRealPath("resources/imagesProductos/") + File.separator + event.getFile().getFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            //byte[] buffer = new byte[BUFFER_SIZE];
            byte[] buffer = new byte[1024];
            int bulk;
            InputStream inputStream = event1.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                } //end of if

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();

            } //end fo while(true)
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {

            e.printStackTrace();

            FacesMessage error = new FacesMessage("No se puede subir el archivo!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }        
        System.out.println("Finaliza handleFile");
    }
}
