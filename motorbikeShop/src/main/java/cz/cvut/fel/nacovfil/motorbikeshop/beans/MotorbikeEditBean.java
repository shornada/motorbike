/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.beans;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import cz.cvut.fel.nacovfil.motorbikeshop.model.MotorbikeStatus;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author FN
 */
@Named(value = "motorbikeEditBean")
@ViewScoped
public class MotorbikeEditBean extends Connection implements Serializable {

    Motorbike m;

    /**
     * Creates a new instance of MotorbikeEditBean
     */
    public MotorbikeEditBean() {
    }

    public void init() {
        m = new Motorbike();
    }

    public String finish() {
        if (m != null) {
            motorbikeService.createMotorbike(m);
            motorbikeService.reloadCache();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getFlash().setKeepMessages(true);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Motorbike succesfully created", null);
            fc.addMessage(null, fm);
            return "/pages/main.xhtml?faces-redirect=true";
        }
        return null;
    }

    public Motorbike getM() {
        return m;
    }

    public void setM(Motorbike m) {
        this.m = m;
    }

}
