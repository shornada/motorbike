/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.service;

import cz.cvut.fel.nacovfil.motorbikeshop.dao.OrderDao;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Order;
import cz.cvut.fel.nacovfil.motorbikeshop.model.OrderStatus;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Filip
 */
@Stateless
public class OrderService extends AbstractServices {
    @EJB
    private final OrderDao dao;

    public OrderService() {
        dao = new OrderDao();
    }

    public OrderService(OrderDao dao) {
        this.dao = dao;
    }

    public void createOrder(Order order) {
        Objects.requireNonNull(order);
        order.setStatus(OrderStatus.SOLD.toString());
        dao.persist(order);
    }

    public void updateOrder(Order order) {
        Objects.requireNonNull(order);
        dao.update(order);
    }

    public void removeOrder(Order order) {
        Objects.requireNonNull(order);
        dao.remove(order);
    }

    public Order find(int id) {
        return dao.find(id);
    }

    public List<Order> findAll() {
        return dao.findAll();
    }

    public List<Order> findByClient(Client client) {
        return dao.findByClient(client);
    }

    public List<Order> findByMotorbike(Motorbike motorbike) {
        return dao.findByMotorbike(motorbike);
    }

    public List<Order> findByDate(Long fromDateTimestamp) {
        return dao.findByDate(fromDateTimestamp);
    }

    public List<Order> findByStatus(String status) {
        return dao.findByStatus(status);
    }

    public void changeToCancelled(Order order) {
        Objects.requireNonNull(order);
        order.setStatus(OrderStatus.CANCELLED.toString());
        dao.update(order);
    }

    public void changeToSold(Order order){
        Objects.requireNonNull(order);
        order.setStatus(OrderStatus.SOLD.toString());
        dao.update(order);
    }
}
