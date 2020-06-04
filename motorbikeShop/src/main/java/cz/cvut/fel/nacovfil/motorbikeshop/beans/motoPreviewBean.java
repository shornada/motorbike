/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.beans;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Order;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Reservation;
import cz.cvut.fel.nacovfil.motorbikeshop.service.MotorbikeService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author FN
 */
@Named(value = "motoPreviewBean")
@ViewScoped
public class motoPreviewBean extends Connection implements Serializable {

    List<Client> users;
    List<Motorbike> motorbikes = new ArrayList();
    private String type;
    private Date resDate;
    private Date today;
    private String username;

    public void init() {
        today = new Date(System.currentTimeMillis());
        if (type != null) {
            motorbikes = motorbikeService.getMotorbikesFromCache(type);

        } else {
            motorbikes = motorbikeService.getMotorbikesFromCache();
        }
    }

    public void initUsers() {
        users = clientService.findAllClient();
    }

    public List<String> getUsernamesAutocomplete(String query) {
        List<String> usernames = new ArrayList<>();
        for (Client c : users) {
            if (c.getUsername().toLowerCase().contains(query.trim().toLowerCase())) {
                usernames.add(c.getUsername());
            }
        }
        return usernames;
    }

    /**
     * Creates a new instance of motoPreviewBean
     */
    public motoPreviewBean() {
    }

    public void reserveMotorbike(Motorbike m, Client c) {
        if (m != null && c != null && resDate != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getFlash().setKeepMessages(true);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Motocykl byl zarezervován", null);
            fc.addMessage(null, fm);
            Reservation res = new Reservation();
            res.setFromDateTimestamp(today.getTime());
            res.setToDateTimestamp(resDate.getTime());
            motorbikeService.makeMotorbikeReserved(m, res, c);
            return;
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Při rezervaci došlo k chybě", null);
        fc.addMessage(null, fm);
    }

    public void orderMotorbike(Motorbike m) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getFlash().setKeepMessages(true);
        Client orderClient = clientService.findByUsername(username);
        if (orderClient == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Uživatel s tímto username neexistuje", null);
            fc.addMessage(null, fm);
            return;
        }
        if (m != null && orderClient != null && today != null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Motocykl byl objednán", null);
            fc.addMessage(null, fm);
            Order order = new Order();
            order.setDateTimestamp(today.getTime());
            motorbikeService.makeMotorbikeOrder(m, order, orderClient);
            return;
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Při objednávce došlo k chybě", null);
        fc.addMessage(null, fm);
    }

    public List<Motorbike> getMotorbikes() {
        return motorbikes;
    }

    public void setMotorbikes(List<Motorbike> motorbikes) {
        this.motorbikes = motorbikes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
