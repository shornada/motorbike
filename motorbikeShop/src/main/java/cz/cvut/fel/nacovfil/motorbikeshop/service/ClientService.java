/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.service;

import cz.cvut.fel.nacovfil.motorbikeshop.dao.ClientDao;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import cz.cvut.fel.nacovfil.motorbikeshop.model.Reservation;
import cz.cvut.fel.nacovfil.motorbikeshop.model.User;
import cz.cvut.fel.nacovfil.motorbikeshop.model.UserRole;
import cz.cvut.fel.nacovfil.motorbikeshop.service.utils.LoggingInterceptor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.slf4j.Logger;

/**
 *
 * @author Filip
 */
@Stateless
public class ClientService extends AbstractServices {

    @EJB
    private final ClientDao dao;
    @Inject
    private Logger logger;

    public ClientService(ClientDao dao/*, PasswordEncoder passwordEncoder*/) {
        this.dao = dao; //this.passwordEncoder = passwordEncoder;
    }

    public ClientService() {
        dao = new ClientDao();
    }

    @Interceptors(LoggingInterceptor.class)
    public void createClient(Client client) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        Objects.requireNonNull(client);
        client.setPassword(new String(md.digest(client.getPassword().getBytes())));
        dao.persist(client);
    }

    public void updateClient(Client client) {
        Objects.requireNonNull(client);
        dao.update(client);
    }

    public void removeClient(Client client) {
        Objects.requireNonNull(client);
        dao.remove(client);
    }

    public Client find(int id) {
        return dao.find(id);
    }
    
    public Client findByUsername(String username) {
        return dao.findByUsername(username);
    }

//    public Client checkLogin(String username, String password) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
//        Client c = findByUsername(username);
//        if(c == null) {
//            return null;
//        }
//        if(c.getPassword().equals(new String(md.digest(password.getBytes())))) {
//            return c;
//        }
//        return null;        
//    }
    public List<Client> findAllClient() {
        return dao.findAll();
    }
}
