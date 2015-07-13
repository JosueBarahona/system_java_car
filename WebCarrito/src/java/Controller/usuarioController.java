/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.usuarioDAO;
import Entidades.TUsuarios;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    
    
        public usuarioController() {
            itemNuevo = new TUsuarios();
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
    
     public String agregar(){
        try{
            System.out.println("Correo: " + itemNuevo.getCorreoUsuario() + "NickName: " + itemNuevo.getNicknameUsuario() + "Nombre: " + itemNuevo.getNombreUsuario());
             estado = 1;
                itemNuevo.setEstadoUsuario(estado);
                usuariosDAO.persist(itemNuevo);
                estado = 0;
                itemNuevo = new TUsuarios();
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
    
}
