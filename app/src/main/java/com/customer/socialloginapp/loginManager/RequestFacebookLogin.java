package com.customer.socialloginapp.loginManager;

import android.support.v7.app.AppCompatActivity;

import com.customer.socialloginapp.databinding.ActivityWelcomeBinding;
import com.customer.socialloginapp.interfaces.Login;
import com.facebook.CallbackManager;

/**
 *  class to implement login interface and override do login to start facebook login
  */
public class RequestFacebookLogin implements Login {

    @Override
    public void doLogin(ActivityWelcomeBinding mBinding, AppCompatActivity mContext, CallbackManager mCallbackManager) {
        FacebookLoginManager.getInstance().setmBinding(mBinding);
        FacebookLoginManager.getInstance().setmContext(mContext);
        FacebookLoginManager.getInstance().configureFacebookSignin();
    }
}
