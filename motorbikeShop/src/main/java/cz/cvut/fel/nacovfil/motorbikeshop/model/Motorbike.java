/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Filip
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Motorbike.findByColor", query = "SELECT c FROM Motorbike c WHERE c.color = :color"),
        @NamedQuery(name = "Motorbike.findByFuel", query = "SELECT c FROM Motorbike c WHERE c.fuel = :fuel"),
        @NamedQuery(name = "Motorbike.findByCategory", query = "SELECT c FROM Motorbike c WHERE c.category = :category"),
        @NamedQuery(name = "Motorbike.findByBrand", query = "SELECT c FROM Motorbike c WHERE c.brand = :brand"),
        @NamedQuery(name = "Motorbike.findByGear", query = "SELECT c FROM Motorbike c WHERE c.gear = :gear"),
        @NamedQuery(name = "Motorbike.findByModel", query = "SELECT c FROM Motorbike c WHERE c.model = :model"),
        @NamedQuery(name = "Motorbike.findByMaximumPower", query = "SELECT c FROM Motorbike c WHERE c.maximumPower = :maximumPower"),
        @NamedQuery(name = "Motorbike.findByPrice", query = "SELECT c FROM Motorbike c WHERE c.price = :price"),
        @NamedQuery(name = "Motorbike.findByLesserPrice", query = "SELECT c FROM Motorbike c WHERE c.price < :price"),
        @NamedQuery(name = "Motorbike.findByGreaterPrice", query = "SELECT c FROM Motorbike c WHERE c.price > :price"),
        @NamedQuery(name = "Motorbike.findByStatus", query = "SELECT c FROM Motorbike c WHERE c.status = :status")})
public class Motorbike extends AbstractEntity implements Serializable  {
    private String color;
    private String brand;
    private String fuel;
    private String gear;
    private String model;
    private String category;
    private Integer maximumPower;
    private Integer price;
    private String status;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reservation> reservationCollection;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orderCollection;

    public Motorbike() {
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaximumPower() {
        return maximumPower;
    }

    public void setMaximumPower(Integer maximumPower) {
        this.maximumPower = maximumPower;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reservation> getReservationCollection() {
        if(reservationCollection == null) {
            reservationCollection = new ArrayList();
        }
        return reservationCollection;
    }

    public void setReservationCollection(List<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    public List<Order> getOrderCollection() {
        if(orderCollection == null) {
            orderCollection = new ArrayList();
        }
        return orderCollection;
    }

    public void setOrderCollection(List<Order> orderCollection) {
        this.orderCollection = orderCollection;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    

}
