    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.service.utils;

import cz.cvut.fel.nacovfil.motorbikeshop.model.Client;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author FN
 */
public class LoggingInterceptor {

    @Inject
    private Logger logger;

    @AroundConstruct
    private void onInit(InvocationContext ic) {
        try {
            logger.debug("Logging initialized");
            ic.proceed();
        } catch (Exception ex) {

        } finally {
            logger.debug("Initialization done");
        }
    }

    @AroundInvoke
    private Object doLog(InvocationContext ic) {
        Object obj = null;
        Client c = (Client)ic.getParameters()[0];
        try {
            logger.info("Registration of user " + c.getUsername() + " in progress");
            obj = ic.proceed();
        } catch (Exception ex) {

        }
        return obj;
    }
}
