/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.beans;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.User;
import cz.cvut.fel.nacovfil.motorbikeshop.service.ClientService;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author FN
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean extends Connection implements Serializable {
    Client logged;

    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
    }

    public void init() throws IOException {
        logged = getUser();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().redirect(fc.getExternalContext().getRequestContextPath() + "/shop/pages/main.xhtml");
    }

    /**
     * vrátí přihlášeného uživatele
     *
     * @return uživatel
     */
    public Client getUser() {
        return getUserFromRequest((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
    }

    /**
     * získá uživatele z requestu
     *
     * @param req request
     * @return uživatel
     */
    public Client getUserFromRequest(HttpServletRequest req) {
        String username = req.getRemoteUser();
        if (logged == null && username != null) {
            logged = clientService.findByUsername(username);
        }
        return logged;
    }

    public Client getLogged() {
        return logged;
    }

    public void setLogged(Client logged) {
        this.logged = logged;
    }
    
    

}
