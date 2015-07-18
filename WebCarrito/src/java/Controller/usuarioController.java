/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.usuarioDAO;
import Entidades.TUsuarios;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Josue
 */
@ManagedBean
@RequestScoped
public class usuarioController {

    @EJB
    private usuarioDAO usuariosDAO;   //EJB es una anotacion exclusiva del DAO, colocarlo justo arriba de este
    private TUsuarios itemNuevo;
    private TUsuarios usuarios;
    private Integer estado;
    private DataModel<TUsuarios> listaUsuarios;
    private String busqueda;
    private Object usuarioDAO;
    
    public Object getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(Object usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public usuarioController() {
        itemNuevo = new TUsuarios();
        
    }

    @PostConstruct
    public void init() {
        buscar(); //carga los datos de fondo una vez iniciado el facelet (o sea la tabla de productos), 
        //para no darle click al boton de ver todos para que aparezcan en la tabla de busqueda.
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public DataModel<TUsuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(DataModel<TUsuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public usuarioDAO getUsuariosDAO() {
        return usuariosDAO;
    }

    public void setUsuariosDAO(usuarioDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    public TUsuarios getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TUsuarios itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public TUsuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(TUsuarios usuarios) {
        this.usuarios = usuarios;
    }

    public String agregar() {
        try {
            System.out.println("Correo: " + itemNuevo.getCorreoUsuario() + "NickName: " + itemNuevo.getNicknameUsuario() + "Nombre: " + itemNuevo.getNombreUsuario() + "Contraseña: " + itemNuevo.getPassUsuario());
            estado = 1;
            itemNuevo.setEstadoUsuario(estado);
            usuariosDAO.persist(itemNuevo);
            estado = 0;
            itemNuevo = new TUsuarios();
            save(); //Mensaje de Datos Guardados
        } catch (Exception e) {
            error();
            System.out.println("Error" + e.getMessage());
        }
        return "";
    }

    public void error() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensaje", "Datos no guardados, error en contraseña posiblemente"));
    }

    public void save() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensaje", "Datos Guardados con exito"));
    }

    public String buscarUsuarios() {
        List<TUsuarios> lista = usuariosDAO.getBuscarUsuario(busqueda);
        ListDataModel<TUsuarios> modeloListaUsuario = new ListDataModel<TUsuarios>(lista);
        setListaUsuarios(modeloListaUsuario);
        return "";
    }

    public void buscar() {
        try {
            List<TUsuarios> lista = usuariosDAO.getFindAll();
            ListDataModel<TUsuarios> modeloListaUsuarios
                    = new ListDataModel<TUsuarios>(lista);
            setListaUsuarios(modeloListaUsuarios);

        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());

        }
    }
        
    

    

    public void onEdit(RowEditEvent event) {
        usuarios = (TUsuarios) event.getObject();

        boolean resultado = usuariosDAO.modificaUsuarios(usuarios);
        FacesMessage msg;
        if (resultado) {
            msg = new FacesMessage("Usuario Modificado", ((TUsuarios) event.getObject()).getNombreUsuario());
        } else {
            msg = new FacesMessage("No se puede actualizar", ((TUsuarios) event.getObject()).getNombreUsuario());
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        buscar();
    }

    public void onCancel(RowEditEvent event) {  //Se ejecuta al hacer click en cancel
        FacesMessage msg = new FacesMessage("Actualizacion cancelada", ((TUsuarios) event.getObject()).getNombreUsuario());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
