/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.exception.security;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FN
 */
public class UserDetail {
    private String uid;
    private String login;
    private String password;
    private List<String> roles = new ArrayList<>();
    //...

    public UserDetail(String uid, String login, String password) {
        this.uid = uid;
        this.login = login;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String role) {
        roles.add(role);
    }
}
