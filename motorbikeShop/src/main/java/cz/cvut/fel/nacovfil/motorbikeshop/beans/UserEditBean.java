/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.beans;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.User;
import cz.cvut.fel.nacovfil.motorbikeshop.model.UserRole;
import cz.cvut.fel.nacovfil.motorbikeshop.service.ClientService;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author FN
 */
@Named(value = "userEditBean")
@ViewScoped
public class UserEditBean extends Connection implements Serializable {

    Client c;
    private String newPassword;
    private String newPasswordCheck;
    private Date birthDate;
    private Date today;
    private boolean registration;
    private String type;
    private String userRole;

    /**
     * Creates a new instance of UserEditBean
     */
    public UserEditBean() {
    }

    public void init(Client logged) {
        today = new Date(System.currentTimeMillis());
        newPassword = null;
        userRole = "User";
        if (logged != null) {
            if (type != null && type.equals("adminEdit")) {
                registration = true;
                c = new Client();
            } else {
                c = logged;
                birthDate = new Date(c.getBirthdateTimestamp());
                registration = false;
            }

        } else {
            c = new Client();
            registration = true;
        }
    }

    public String finish() throws NoSuchAlgorithmException {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getFlash().setKeepMessages(true);
        if (registration) {
            if (clientService.findByUsername(c.getUsername()) != null) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Uživatel s tímto username již existuje", null);
                fc.addMessage(null, fm);
                return null;
            }
            c.setBirthdateTimestamp(birthDate.getTime());
            c.setPassword(newPassword);
            if (userRole.equals("User")) {
                c.setUserRole(UserRole.USER);
            } else if (userRole.equals("Employee")) {
                c.setUserRole(UserRole.EMPLOYEE);
            } else {
                c.setUserRole(UserRole.EMPLOYEE);
            }
            clientService.createClient(c);
        } else {
            if (newPassword != null && !newPassword.isEmpty()) {
                c.setPassword(newPassword);
            }
            c.setBirthdateTimestamp(birthDate.getTime());
            clientService.updateClient(c);
        }

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "completed", null);
        fc.addMessage(null, fm);
        return "main.xhtml?faces-redirect=true";
    }

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }

    public void setNewPasswordCheck(String newPasswordCheck) {
        this.newPasswordCheck = newPasswordCheck;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean adminEdit() {
        if (type != null && type.equals("adminEdit")) {
            return true;
        }
        return false;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
