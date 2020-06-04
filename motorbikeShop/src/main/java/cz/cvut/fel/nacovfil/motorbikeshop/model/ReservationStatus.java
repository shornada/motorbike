/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.model;

/**
 *
 * @author Filip
 */
public enum ReservationStatus {
    RESERVED("RESERVED"), CANCELLED("CANCELLED");

    private final String type;

    ReservationStatus (String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
