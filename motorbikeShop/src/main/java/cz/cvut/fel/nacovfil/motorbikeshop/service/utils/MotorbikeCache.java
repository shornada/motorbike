/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.service.utils;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Motorbike;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FN
 */
public class MotorbikeCache {
    private Map<Integer, Motorbike> cache = new HashMap();
    private static MotorbikeCache mc;

    private MotorbikeCache() {
    }
    
    public static MotorbikeCache getCache() {
        if(mc== null) {
            mc = new MotorbikeCache();
        }
        return mc;
    }
    
    public Motorbike getById(Integer id) {
        if(cache.containsKey(id)) {
            return cache.get(id);
        }
        return null;
    }
    
    public List<Motorbike> getAll() {
        return new ArrayList(cache.values());
    }
    
    public List<Motorbike> getAll(String type) {
        return filterByType(type);
    }
    
    private List<Motorbike> filterByType(String type) {
        List<Motorbike> res = new ArrayList();
        for(Motorbike m: this.getAll()) {
            if(m.getCategory().equals(type)) {
                res.add(m);
            }
        }
        return res;
    }
    
    public void reloadCache(List<Motorbike> motorbikes) {
        cache.clear();
        for(Motorbike m: motorbikes) {
            cache.put(m.getId(), m);
        }        
    }
    
    public void add(Motorbike m) {
        if(!cache.containsKey(m.getId())) {
            cache.put(m.getId(), m);
        }
    }
    
}
