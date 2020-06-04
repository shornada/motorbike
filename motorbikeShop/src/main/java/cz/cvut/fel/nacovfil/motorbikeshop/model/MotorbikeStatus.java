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
public enum MotorbikeStatus {
    FOR_SALE("FOR_SALE"), SOLD("SOLD"), RESERVED("RESERVED");

    private final String type;

    MotorbikeStatus (String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
