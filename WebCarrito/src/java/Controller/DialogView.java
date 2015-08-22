/*

 */
package Controller;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.CloseEvent;
 
@ManagedBean
public class DialogView {
 
    public void destroyWorld() {
        System.out.println("Entra!!");
        addMessage("Error del Sistema!", "No se pudo destruir el mundo, vuelva a intentarlo mas tarde.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}