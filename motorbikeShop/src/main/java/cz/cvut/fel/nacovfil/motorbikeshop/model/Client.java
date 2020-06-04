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
        @NamedQuery(name = "Client.findByFirst_name", query = "SELECT c FROM Client c WHERE c.first_name = :first_name"),
        @NamedQuery(name = "Client.findBySurname", query = "SELECT c FROM Client c WHERE c.surname = :surname"),
        @NamedQuery(name = "Client.findByBirthdateTimestamp", query = "SELECT c FROM Client c WHERE c.birthdateTimestamp = :birthdateTimestamp"),
        @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email"),
        @NamedQuery(name = "Client.findByPhone_number", query = "SELECT c FROM Client c WHERE c.phone_number = :phone_number")
})
public class Client extends User implements Serializable {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reservation> reservationCollection;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orderCollection;

    public Client() {
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
    
    public boolean hasClientPrivilage(){
        if (this.getUserRole() == UserRole.USER || this.getUserRole() == UserRole.EMPLOYEE || this.getUserRole() == UserRole.ADMIN){
            return true;
        }
        return false;
    }
    
    public boolean hasEmployeePrivilage(){
        if (this.getUserRole() == UserRole.EMPLOYEE || this.getUserRole() == UserRole.ADMIN){
            return true;
        }
        return false;
    }
    
    public boolean hasAdminPrivilage(){
        if (this.getUserRole() == UserRole.ADMIN){
            return true;
        }
        return false;
    }

}
