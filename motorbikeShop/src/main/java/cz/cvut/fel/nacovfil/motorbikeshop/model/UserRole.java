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
public enum UserRole {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER"), EMPLOYEE("ROLE_EMPLOYEE");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

