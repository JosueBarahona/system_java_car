/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.loginDAO;
import Dao.usuarioDAO;
import Entidades.TCategorias;
import Entidades.TProductos;
import Entidades.TUsuarios;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Josue
 */
@ManagedBean
@SessionScoped
public class loginController {

    @EJB
    private loginDAO loginDAO;
//    private TBitacora itemNuevo;
    private String usuario, password, nombrecomp, rol;

    public loginController() {

    }

    public String login() {
        if (loginDAO.getLogin(usuario, password) > 0) {
            mensaje();
            loginDAO.buscarPorNombre(usuario, password);
            loginDAO.buscarRol(usuario, password);
            
            
            
            return "ingresar_usuario";
        } else {
            error(); // Muestra mensaje de error
            return "";
        }
    }

      public void mensaje() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensaje", "Login exitoso"));
    }

    public void error() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensaje", "Error al hacer Login"));
    }

    public loginDAO getLoginDAO() {
        return loginDAO;
    }

    public void setLoginDAO(loginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombrecomp() {
        return nombrecomp;
    }

    public void setNombrecomp(String nombrecomp) {
        this.nombrecomp = nombrecomp;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
