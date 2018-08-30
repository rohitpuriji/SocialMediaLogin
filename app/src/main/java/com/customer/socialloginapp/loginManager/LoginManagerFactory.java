package com.customer.socialloginapp.loginManager;

import com.customer.socialloginapp.interfaces.Login;

/**
 * class having method to return type of login requested by user
 */
public class LoginManagerFactory {

    /**
     * mwthod to return type of login requested by user
     */
    public Login getData(String loginFor){
        if(loginFor == null){
            return null;
        }
        if(loginFor.equalsIgnoreCase("google")){
            return new RequestGoogleLogin();

        } else if(loginFor.equalsIgnoreCase("facebook")){
            return new RequestFacebookLogin();

        }

        return null;
    }


}
