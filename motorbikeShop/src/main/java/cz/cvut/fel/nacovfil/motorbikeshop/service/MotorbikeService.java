/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.service;

import cz.cvut.fel.nacovfil.motorbikeshop.dao.MotorbikeDao;
import cz.cvut.fel.nacovfil.motorbikeshop.elastic.ElasticSearch;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.ElasticPhoto;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import cz.cvut.fel.nacovfil.motorbikeshop.model.MotorbikeStatus;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Order;
import cz.cvut.fel.nacovfil.motorbikeshop.model.OrderStatus;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Reservation;
import cz.cvut.fel.nacovfil.motorbikeshop.model.ReservationStatus;
import cz.cvut.fel.nacovfil.motorbikeshop.service.utils.LoggingInterceptor;
import cz.cvut.fel.nacovfil.motorbikeshop.service.utils.MotorbikeCache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.slf4j.Logger;

/**
 *
 * @author Filip
 */
@Singleton
public class MotorbikeService extends AbstractServices {

    @EJB
    private final MotorbikeDao dao;
    @Inject
    private Logger logger;
    private MotorbikeCache cache;

    @PostConstruct
    private void init() {
        cache = MotorbikeCache.getCache();
        this.reloadCache();
    }

    public Motorbike find(int id) {
        return dao.find(id);
    }

    public Motorbike find(Long id) {
        return dao.find(id);
    }

    public List<Motorbike> findAll() {
        return dao.findAll();
    }
    
    public MotorbikeService() {
        this.dao = new MotorbikeDao();
    }

    public MotorbikeService(MotorbikeDao dao) {
        this.dao = dao;
    }

    public void createMotorbike(Motorbike motorbike) {
        Objects.requireNonNull(motorbike);
        motorbike.setStatus(MotorbikeStatus.FOR_SALE.toString());
        dao.persist(motorbike);
    }

    public void makeMotorbikeSold(Motorbike motorbike) {
        motorbike.setStatus(MotorbikeStatus.SOLD.toString());
        dao.update(motorbike);
    }

    public void makeMotorbikeReserved(Motorbike motorbike, Reservation res, Client c) {
        if (motorbike != null && res != null && c != null) {
            motorbike.setStatus(MotorbikeStatus.RESERVED.toString());
            motorbike.getReservationCollection().add(res);
            res.setMotorbike(motorbike);
            c.getReservationCollection().add(res);
            res.setClient(c);
            res.setStatus(ReservationStatus.RESERVED.toString());
            reservationService.createReservation(res);
            dao.update(motorbike);
            clientService.updateClient(c);
        }
    }
    
    public void makeMotorbikeOrder(Motorbike motorbike, Order order, Client c) {
        if (motorbike != null && order != null && c != null) {
            motorbike.getOrderCollection().add(order);
            order.setMotorbike(motorbike);
            c.getOrderCollection().add(order);
            order.setClient(c);
            order.setStatus(OrderStatus.SOLD.toString());
            orderService.createOrder(order);
            dao.update(motorbike);
            clientService.updateClient(c);
        }
    }

    public void makeMotorbikeForSale(Motorbike motorbike) {
        motorbike.setStatus(MotorbikeStatus.FOR_SALE.toString());
        dao.update(motorbike);
    }

    public void updateMotorbike(Motorbike motorbike) {
        Objects.requireNonNull(motorbike);
        dao.update(motorbike);
    }

    public void removeMotorbike(Motorbike motorbike) {
        Objects.requireNonNull(motorbike);
        dao.remove(motorbike);
    }

    public void reloadCache() {
        cache.reloadCache(this.findAll());
    }

    public List<Motorbike> getMotorbikesFromCache() {
        return cache.getAll();
    }

    public List<Motorbike> getMotorbikesFromCache(String type) {
        return cache.getAll(type);
    }

    public Motorbike getMotorbikeFromCacheById(Integer id) {
        return cache.getById(id);
    }
}
