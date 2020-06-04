/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.exception.security;

import java.security.Principal;

/**
 *
 * @author FN
 */
public class CustomPrincipal implements Principal {

    private UserDetail userDetail;

    public CustomPrincipal(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String getName() {
        return userDetail.getLogin();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + getName();
    }
}
