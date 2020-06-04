/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nacovfil.motorbikeshop.beans;

import cz.cvut.fel.nacovfil.motorbikeshop.service.utils.Producer;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Table;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 *
 * @author FN
 */
@Named
@RequestScoped
public class LoginBean {
 
    @Inject
    private SecurityContext securityContext;
 
    @NotNull private String username;
 
    @NotNull private String password;

    public LoginBean() {
    }
    
    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status = securityContext
          .authenticate(
                        getRequest(context),
                        getResponse(context),
            withParams().credential(credential));
        if (status.equals(SEND_CONTINUE)) {
            // Authentication mechanism has send a redirect, should not
            // send anything to response from JSF now.
            context.responseComplete();
        } else if (status.equals(status.SUCCESS)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Succsessfully logged in", null));
            context.getExternalContext().getFlash().setKeepMessages(true);
            //Producer.send("User " + username + " succesfully logged in");
        } else if (status == AuthenticationStatus.SEND_FAILURE) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Špatný login nebo heslo", null));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }
    }
    
    private static HttpServletResponse
            getResponse(FacesContext context
            ) {
        return (HttpServletResponse) context
                .getExternalContext()
                .getResponse();

    }

    private static HttpServletRequest
            getRequest(FacesContext context
            ) {
        return (HttpServletRequest) context
                .getExternalContext()
                .getRequest();

    }
            
    
      
    // ...

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
}
