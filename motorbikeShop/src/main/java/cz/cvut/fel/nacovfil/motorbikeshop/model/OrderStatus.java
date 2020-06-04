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
public enum OrderStatus {
    SOLD("SOLD"), CANCELLED("CANCELLED");

    private final String type;

    OrderStatus (String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
