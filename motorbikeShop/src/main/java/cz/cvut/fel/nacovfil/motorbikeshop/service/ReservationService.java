/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.service;

import cz.cvut.fel.nacovfil.motorbikeshop.dao.ReservationDao;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Reservation;
import cz.cvut.fel.nacovfil.motorbikeshop.model.ReservationStatus;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Filip
 */
@Stateless
public class ReservationService extends AbstractServices{
    @EJB
    private final ReservationDao rao;

    public ReservationService() {
        rao = new ReservationDao();
    }

    public ReservationService(ReservationDao rao) {
        this.rao = rao;
    }

    public Reservation find(Integer id) {
        return rao.find(id);
    }

    public List<Reservation> findAll() {
        return rao.findAll();
    }

    public void createReservation(Reservation reservation) {
        Objects.requireNonNull(reservation);
        reservation.setStatus(ReservationStatus.RESERVED.toString());
        rao.persist(reservation);
    }

    public void updateReservation(Reservation reservation) {
        Objects.requireNonNull(reservation);
        rao.update(reservation);
    }

    public List<Reservation> findByStatus(String status) {
        return rao.findByStatus(status);
    }

    public List<Reservation> findByUser(Client client) {
        return rao.findByUser(client);
    }

    public List<Reservation> findByCar(Motorbike motorbike) {
        return rao.findByMotorbike(motorbike);
    }

    public List<Reservation> findByCarAndBetweenDates(Motorbike motorbike, Long fromDateTimestamp, Long toDateTimestamp) {
        return rao.findByMotorbikeAndBetweenDates(motorbike, fromDateTimestamp, toDateTimestamp);
    }

    public void changeToCancelled(Reservation reservation) {
        reservation.setStatus(ReservationStatus.CANCELLED.toString());
        rao.update(reservation);
    }
 
}
