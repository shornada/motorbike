/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 *
 * @author Filip
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Reservation.findByStatus", query = "SELECT c FROM Reservation c WHERE c.status = :status"),
        @NamedQuery(name = "Reservation.findByUser", query = "SELECT c FROM Reservation c WHERE c.client = :client"),
        @NamedQuery(name = "Reservation.findByMotorbike", query = "SELECT c FROM Reservation c WHERE c.motorbike = :motorbike"),
        @NamedQuery(name = "Reservation.findByMotorbikeAndDay", query = "SELECT c FROM Reservation c WHERE c.motorbike = :motorbike AND c.fromDateTimestamp < :day AND c.toDateTimestamp > :day"),
        @NamedQuery(name = "Reservation.findByMotorbikeAndBetweenDates", query = "SELECT c FROM Reservation c WHERE c.motorbike = :motorbike AND ((c.fromDateTimestamp BETWEEN :fromDateTimestamp AND :toDateTimestamp) OR (c.toDateTimestamp BETWEEN :fromDateTimestamp AND :toDateTimestamp))")
})
public class Reservation extends AbstractEntity implements Serializable{

    private Long fromDateTimestamp;

    private Long toDateTimestamp;
    private Integer price;
    private String status;

    @ManyToOne(optional = false)
    private Client client;

    @ManyToOne(optional = false)
    private Motorbike motorbike;

    public Reservation() {
    }

    public Integer getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    public Motorbike getMotorbike() {
        return motorbike;
    }

    public void setMotorbike(Motorbike motorbike) {
        this.motorbike = motorbike;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Long getFromDateTimestamp() {
        return fromDateTimestamp;
    }

    public void setFromDateTimestamp(Long fromDateTimestamp) {
        this.fromDateTimestamp = fromDateTimestamp;
    }

    public Long getToDateTimestamp() {
        return toDateTimestamp;
    }

    public void setToDateTimestamp(Long toDateTimestamp) {
        this.toDateTimestamp = toDateTimestamp;
    }
    
    
}