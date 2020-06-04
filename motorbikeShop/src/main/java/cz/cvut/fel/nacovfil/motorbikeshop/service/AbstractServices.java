/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.service;

import javax.ejb.EJB;

/**
 *
 * @author FN
 */
public abstract class AbstractServices {
    @EJB
    protected ClientService clientService;
    
    @EJB
    protected MotorbikeService motorbikeService;
    
    @EJB
    protected OrderService orderService;
    
    @EJB
    protected ReservationService reservationService;
}
