package com.customer.socialloginapp.loginManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.customer.socialloginapp.databinding.ActivityWelcomeBinding;
import com.customer.socialloginapp.utility.HandleUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * Google Login manager class to maintain data required for google login
 */
public class GoogleLoginManager {

    private static final GoogleLoginManager ourInstance = new GoogleLoginManager();

    private GoogleSignInClient mGoogleSignInClient;
    private AppCompatActivity mContext;
    private Intent data;
    private ActivityWelcomeBinding mBinding;
    public static GoogleLoginManager getInstance() {
        return ourInstance;
    }

    private GoogleLoginManager() {
    }

    private ActivityWelcomeBinding getmBinding() {
        return mBinding;
    }

    public void setmBinding(ActivityWelcomeBinding mBinding) {
        this.mBinding = mBinding;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
        getGoogleSignInResult();
    }

    public GoogleSignInClient getmGoogleSignInClient() {
        return mGoogleSignInClient;
    }

    private void setmGoogleSignInClient(GoogleSignInClient mGoogleSignInClient) {
        this.mGoogleSignInClient = mGoogleSignInClient;
    }

    private AppCompatActivity getmContext() {
        return mContext;
    }

    public void setmContext(AppCompatActivity mContext) {
        this.mContext = mContext;
    }


    /**
     * Configure google sign-in to request the user's ID, email address, and basic profile
     *             ID and basic profile are included in DEFAULT_SIGN_IN.
     */
    public void configureGoogleSignin(){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getmContext(), gso);
        setmGoogleSignInClient(mGoogleSignInClient);
    }

    private void getGoogleSignInResult(){
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(getData());
        handleSignInResult(task);
    }

    /**
     * The GoogleSignInAccount object contains information about the signed-in user, such as the user's name.
     * @param completedTask
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            HandleUI.getInstance().updateUI(getmBinding(),account.getDisplayName(),getmContext());

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode());
            HandleUI.getInstance().updateUI(getmBinding(),null,getmContext());
        }
    }

}
