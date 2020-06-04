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
import javax.persistence.Table;

/**
 *
 * @author Filip
 */
@Entity
@Table(name="orders")
@NamedQueries({
        @NamedQuery(name = "Order.findByClient", query = "SELECT b FROM Order b WHERE b.client = :client"),
        @NamedQuery(name = "Order.findByMotorbike", query = "SELECT b FROM Order b WHERE b.motorbike = :motorbike"),
        @NamedQuery(name = "Order.findByDate", query = "SELECT b FROM Order b WHERE b.dateTimestamp = :date"),
        @NamedQuery(name = "Order.findByStatus", query = "SELECT b FROM Order b WHERE b.status = :status"),
})
public class Order extends AbstractEntity implements Serializable {

    private Long dateTimestamp;

    private Integer price;

    private String status;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Motorbike motorbike;

    public Order() {
    }

    public Long getDateTimestamp() {
        return dateTimestamp;
    }

    public void setDateTimestamp(Long dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
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

    public Motorbike getMotorbike() {
        return motorbike;
    }

    public void setMotorbike(Motorbike motorbike) {
        this.motorbike = motorbike;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
