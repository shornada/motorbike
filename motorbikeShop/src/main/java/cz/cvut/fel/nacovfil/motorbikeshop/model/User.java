/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Filip
 */
@MappedSuperclass
public abstract class User extends AbstractEntity implements Serializable {
    protected String username;
    protected String password;
    protected String first_name;
    protected String surname;
    protected Long birthdateTimestamp;
    protected String email;
    protected Integer phone_number;
    protected UserRole userRole;

    public User() {
    }

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public void encodePassword(PasswordEncoder encoder) {
//        this.password = encoder.encode(password);
//    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getBirthdateTimestamp() {
        return birthdateTimestamp;
    }

    public void setBirthdateTimestamp(Long birthdateTimestamp) {
        this.birthdateTimestamp = birthdateTimestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    } 
    
    public boolean HasClientPrivilage (){
        if (this.getUserRole() == UserRole.USER || this.getUserRole() == UserRole.EMPLOYEE || this.getUserRole() == UserRole.ADMIN){
            return true;
        }
        return false;
    }
    
    public boolean HasEmployeePrivilage (){
        if (this.getUserRole() == UserRole.EMPLOYEE || this.getUserRole() == UserRole.ADMIN){
            return true;
        }
        return false;
    }
    
    public boolean HasAdminPrivilage (){
        if (this.getUserRole() == UserRole.ADMIN){
            return true;
        }
        return false;
    }
            
}
