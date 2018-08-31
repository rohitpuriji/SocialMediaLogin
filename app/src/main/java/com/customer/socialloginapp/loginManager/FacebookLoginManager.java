package com.customer.socialloginapp.loginManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.customer.socialloginapp.databinding.ActivityWelcomeBinding;
import com.customer.socialloginapp.utility.HandleUI;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;

import java.util.Collections;

/**
 * Class to maintain data required for facebook login
 */
public class FacebookLoginManager {
    private static final FacebookLoginManager ourInstance = new FacebookLoginManager();

    private CallbackManager mCallbackManager;
    private AppCompatActivity mContext;
    private ActivityWelcomeBinding mBinding;

    public static FacebookLoginManager getInstance() {
        return ourInstance;
    }

    private FacebookLoginManager() {
    }

    public CallbackManager getmCallbackManager() {
        return mCallbackManager;
    }

    public void setmCallbackManager(CallbackManager mCallbackManager) {
        this.mCallbackManager = mCallbackManager;
    }

    private AppCompatActivity getmContext() {
        return mContext;
    }

    public void setmContext(AppCompatActivity mContext) {
        this.mContext = mContext;
    }

    public ActivityWelcomeBinding getmBinding() {
        return mBinding;
    }

    public void setmBinding(ActivityWelcomeBinding mBinding) {
        this.mBinding = mBinding;
    }


    /**
     *     Configure facebook sign-in to request the user's ID and basic profile
     */
    public void configureFacebookSignin() {

        LoginManager.getInstance().logInWithReadPermissions(getmContext(), Collections.singletonList("public_profile"));

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        setFacebookData(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    /**
     *     Graph request to get the user details after facebook login
     */
    private void setFacebookData(final LoginResult loginResult)
    {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                (object, response) -> {
                    // Application code
                    try {

                        HandleUI.getInstance().updateUI(mBinding,response.getJSONObject().getString("first_name")
                                + " "+response.getJSONObject().getString("last_name"),getmContext());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
