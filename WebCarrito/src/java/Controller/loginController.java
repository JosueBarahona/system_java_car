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
import Util.Util;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Josue
 */
@ManagedBean
@SessionScoped
public class loginController implements Serializable{

    @EJB
    private loginDAO loginDAO;
//    private TBitacora itemNuevo;
    private String usuario, password, usuarioSess;

    public loginController() {

    }

    public String login() {
        if (loginDAO.getLogin(usuario, password) > 0) {

            mensaje();

            loginDAO.buscarPorNombre(usuario, password);
            
            usuarioSess = usuario;

            if (loginDAO.buscarRol(usuario, password).equalsIgnoreCase("[Usuario]")) {

                
                HttpSession hs = Util.getSession();
                hs.setAttribute("usuarioSess", usuarioSess);
                return "index_usu.xhtml?faces-redirect=true";
            } else {

                HttpSession hs = Util.getSession();
                hs.setAttribute("usuarioSess", usuarioSess);
                return "index_adm.xhtml?faces-redirect=true";
            }

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

    public String logout() {
        HttpSession hs = Util.getSession();
        hs.invalidate();
        return "../Usuarios/login_usuario.xhtml?faces-redirect=true";
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

    public String getUsuarioSess() {
        return usuarioSess;
    }

    public void setUsuarioSess(String usuarioSess) {
        this.usuarioSess = usuarioSess;
    }

}
