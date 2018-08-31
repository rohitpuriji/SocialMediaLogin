package com.customer.socialloginapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.customer.socialloginapp.databinding.ActivityWelcomeBinding;
import com.customer.socialloginapp.interfaces.Login;
import com.customer.socialloginapp.loginManager.FacebookLoginManager;
import com.customer.socialloginapp.loginManager.GoogleLoginManager;
import com.customer.socialloginapp.loginManager.LoginManagerFactory;
import com.customer.socialloginapp.utility.HandleUI;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

/**
 * Activity to show login option like facebook and google
 */
public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding mBinding;
    private LoginManagerFactory mLoginManagerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);

        FacebookLoginManager.getInstance().setmCallbackManager(CallbackManager.Factory.create());

        mLoginManagerFactory = new LoginManagerFactory();

        mBinding.imgGoogle.setOnClickListener(view -> {
            mLoginManagerFactory.getData("google").doLogin(mBinding,WelcomeActivity.this,null);

        });

        mBinding.imgFacebook.setOnClickListener(view -> {
            mLoginManagerFactory.getData("facebook").doLogin(mBinding,WelcomeActivity.this,null);
        });

        mBinding.textView.setOnClickListener(view -> signOut());
    }

    /**
     * Method to signout from google or facebook account
     */
    private void signOut() {
            GoogleLoginManager.getInstance().getmGoogleSignInClient().signOut()
                    .addOnCompleteListener(this, task -> {
                        HandleUI.getInstance().updateUI(mBinding,null,this);
                    });
            HandleUI.getInstance().updateUI(mBinding,null,this);
            LoginManager.getInstance().logOut();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FacebookLoginManager.getInstance().getmCallbackManager().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 101) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleLoginManager.getInstance().setData(data);
        }
    }


}
