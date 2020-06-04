/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.beans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FN
 */
@Named(value = "applicationBean")
@ApplicationScoped
public class ApplicationBean {

    /**
     * Creates a new instance of ApplicationBean
     */
    public ApplicationBean() {
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(ApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpServletResponse resp = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            context.responseComplete();
            context.getExternalContext().invalidateSession();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User logged out", null));
            return "pages/main.xhtml?faces-redirect=true";            
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Log out failed", null));
            Logger.getLogger(ApplicationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
