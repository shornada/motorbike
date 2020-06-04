/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.service.utils;

import java.util.Date;
import javax.ejb.Singleton;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author FN
 */
@Named(value = "converterBean")
@ApplicationScoped
@Singleton
public class ConverterBean {

    /**
     * Creates a new instance of ConverterBean
     */
    public ConverterBean() {
    }
    
    public Date getDateFromTimestamp(Long timestamp) {
        return new Date(timestamp);
    }
    
}
