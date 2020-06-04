/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.dao;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
public class ClientDao extends BaseDao<Client>{
    public ClientDao() {
        super(Client.class);
    }
    
    @PersistenceContext(unitName = "dbConnect")
    private EntityManager em;
    
    public Client findByUsername(String username) {
        try {
            return (Client) em.createQuery(" Select c from Client c Where c.username = :username", Client.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Client> findAllByFirst_name(String first_name) {
        try {
            return em.createNamedQuery("Client.findByFirst_name", Client.class)
                    .setParameter("first_name", first_name)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Client> findAllBySurname(String surname) {
        try {
            return em.createNamedQuery("Client.findBySurname", Client.class)
                    .setParameter("surname", surname)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Client> findAllByBirthdate(Long birthdate) {
        try {
            return em.createNamedQuery("Client.findByBirthdateTimestamp", Client.class)
                    .setParameter("birthdateTimestamp", birthdate)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Client> findAllByEmail(String email) {
        try {
            return em.createNamedQuery("Client.findByEmail", Client.class)
                    .setParameter("email", email)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Client> findAllByPhone_number(int phnoe_number) {
        try {
            return em.createNamedQuery("Client.findByPhone_number", Client.class)
                    .setParameter("phone_number", phnoe_number)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}