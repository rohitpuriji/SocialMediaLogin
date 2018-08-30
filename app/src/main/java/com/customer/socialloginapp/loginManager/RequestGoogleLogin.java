package com.customer.socialloginapp.loginManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.customer.socialloginapp.databinding.ActivityWelcomeBinding;
import com.customer.socialloginapp.interfaces.Login;
import com.facebook.CallbackManager;

/**
 *  class to implement login interface and override do login to start google login
 */

public class RequestGoogleLogin implements Login {

    @Override
    public void doLogin(ActivityWelcomeBinding mBinding, AppCompatActivity mContext, CallbackManager mCallbackManager) {
        GoogleLoginManager.getInstance().setmBinding(mBinding);
        GoogleLoginManager.getInstance().setmContext(mContext);
        GoogleLoginManager.getInstance().configureGoogleSignin();
        Intent signInIntent = GoogleLoginManager.getInstance().getmGoogleSignInClient().getSignInIntent();
        mContext.startActivityForResult(signInIntent, 101);
    }
}
