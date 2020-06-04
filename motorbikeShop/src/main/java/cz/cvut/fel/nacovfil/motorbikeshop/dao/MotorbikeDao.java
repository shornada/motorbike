/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.dao;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
@LocalBean
public class MotorbikeDao extends BaseDao<Motorbike>{
    public MotorbikeDao() {
        super(Motorbike.class);
    }
    
    @PersistenceContext(unitName = "dbConnect")
    private EntityManager em;
    
    public Motorbike findByColor(String color) {
        try {
            return em.createNamedQuery("Motorbike.findByColor", Motorbike.class).setParameter("color", color)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByColor(String color) {
        try {
            return em.createNamedQuery("Motorbike.findByColor", Motorbike.class)
                    .setParameter("color", color)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByFuel(String fuel) {
        try {
            return em.createNamedQuery("Motorbike.findByFuel", Motorbike.class)
                    .setParameter("fuel",  fuel)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByGear(String gear) {
        try {
            return em.createNamedQuery("Motorbike.findByGear", Motorbike.class)
                    .setParameter("gear",  gear)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByModel(String model) {
        try {
            return em.createNamedQuery("Motorbike.findByModel", Motorbike.class)
                    .setParameter("model",  model)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByMaximumPower(int maximumPower) {
        try {
            return em.createNamedQuery("Motorbike.findByMaximumPower", Motorbike.class)
                    .setParameter("maximumPower",  maximumPower)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByPrice(int price) {
        try {
            return em.createNamedQuery("Motorbike.findByPrice", Motorbike.class)
                    .setParameter("price",  price)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByLesserPrice(int price) {
        try {
            return em.createNamedQuery("Motorbike.findByLesserPrice", Motorbike.class)
                    .setParameter("price",  price)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByGreaterPrice(int price) {
        try {
            return em.createNamedQuery("Motorbike.findByGreaterPrice", Motorbike.class)
                    .setParameter("price",  price)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Motorbike> findAllByStatus(String status) {
        try {
            return em.createNamedQuery("Motorbike.findByStatus", Motorbike.class)
                    .setParameter("status",  status)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Motorbike> findAllByBrand(String brand) {
        try {
            return em.createNamedQuery("Motorbike.findByBrand", Motorbike.class)
                    .setParameter("brand",  brand)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Motorbike> findAllByCategory(String category) {
        try {
            return em.createNamedQuery("Motorbike.findByCategory", Motorbike.class)
                    .setParameter("category",  category)
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