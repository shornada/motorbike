/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.beans;

import cz.cvut.fel.nacovfil.motorbikeshop.dao.OrderDao;
import cz.cvut.fel.nacovfil.motorbikeshop.service.ClientService;
import cz.cvut.fel.nacovfil.motorbikeshop.service.MotorbikeService;
import cz.cvut.fel.nacovfil.motorbikeshop.service.OrderService;
import cz.cvut.fel.nacovfil.motorbikeshop.service.ReservationService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author FN
 */
public abstract class Connection implements Serializable {
    @EJB
    ClientService clientService;
    @EJB
    MotorbikeService motorbikeService;
    @EJB
    OrderService orderService;
    @EJB
    ReservationService reservationService;
}
