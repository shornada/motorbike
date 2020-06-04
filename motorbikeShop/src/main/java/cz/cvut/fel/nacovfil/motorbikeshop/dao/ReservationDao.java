/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.dao;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Reservation;
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
public class ReservationDao extends BaseDao<Reservation>{
    public ReservationDao() {
        super(Reservation.class);
    }
    
    @PersistenceContext(unitName = "dbConnect")
    private EntityManager em;
    
    public List<Reservation> findByStatus(String status) {
        try {
            return em.createNamedQuery("Reservation.findByStatus", Reservation.class)
                    .setParameter("status",  status)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Reservation> findByUser(Client client) {
        try {
            return em.createNamedQuery("Reservation.findByUser", Reservation.class)
                    .setParameter("client",  client)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Reservation> findByMotorbike(Motorbike motorbike) {
        try {
            return em.createNamedQuery("Reservation.findByMotorbike", Reservation.class)
                    .setParameter("motorbike",  motorbike)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Reservation> findByMotorbikeAndBetweenDates(Motorbike motorbike, Long fromDate, Long toDate) {
        try {
            return em.createNamedQuery("Reservation.findByCarAndBetweenDates", Reservation.class)
                    .setParameter("motorbike",  motorbike)
                    .setParameter("fromDateTimestamp", fromDate)
                    .setParameter("toDateTimestamp", toDate)
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