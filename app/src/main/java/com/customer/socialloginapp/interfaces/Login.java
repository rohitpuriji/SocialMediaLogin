package com.customer.socialloginapp.interfaces;

import android.support.v7.app.AppCompatActivity;

import com.customer.socialloginapp.databinding.ActivityWelcomeBinding;
import com.facebook.CallbackManager;

/**
 * All class ll implement do login method to send common data
 */

public interface Login {
    /**
     * @param mBinding
     * @param mContext
     * @param mCallbackManager
     */
    void doLogin(ActivityWelcomeBinding mBinding, AppCompatActivity mContext, CallbackManager mCallbackManager);

}
