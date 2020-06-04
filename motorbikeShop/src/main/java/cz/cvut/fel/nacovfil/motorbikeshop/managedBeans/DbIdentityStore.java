/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.managedBeans;

import cz.cvut.fel.nacovfil.motorbikeshop.service.ClientService;
import cz.cvut.fel.nacovfil.motorbikeshop.service.utils.LoggingInterceptor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author FN
 */
@ApplicationScoped
public class DbIdentityStore implements IdentityStore {

    @EJB
    ClientService clientService;
    @Inject
    private org.slf4j.Logger logger;

    /**
     * Creates a new instance of DbIdentityStore
     */
    public DbIdentityStore() {
    }

    @Override
    public int priority() {
        return 70;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }

    public CredentialValidationResult validate(UsernamePasswordCredential credential) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String password = clientService.findByUsername(credential.getCaller()).getPassword();
        if (password != null && password.equals(new String(md.digest(credential.getPasswordAsString().getBytes())))) {
            return new CredentialValidationResult(credential.getCaller());
        }
        return INVALID_RESULT;
    }

}
