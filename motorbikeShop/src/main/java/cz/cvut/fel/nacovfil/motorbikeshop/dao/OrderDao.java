/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.dao;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Order;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
public class OrderDao extends BaseDao<Order>{
    public OrderDao() {
        super(Order.class);
    }
    
    @PersistenceContext(unitName = "dbConnect")
    private EntityManager em;
    
    public List<Order> findByClient(Client client) {
        try {
            return em.createNamedQuery("Order.findByClient", Order.class)
                    .setParameter("client", client)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Order> findByMotorbike(Motorbike motorbike) {
        try {
            return em.createNamedQuery("Order.findByMotorbike", Order.class)
                    .setParameter("motorbike", motorbike)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Order> findByDate(Long fromDate) {
        try {
            return em.createNamedQuery("Order.findByDate", Order.class)
                    .setParameter("date",  fromDate)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Order> findByStatus(String status) {
        try {
            return em.createNamedQuery("Order.findByStatus", Order.class)
                    .setParameter("status",  status)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}