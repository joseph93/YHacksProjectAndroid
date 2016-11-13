package com.example.josep.yhacks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainFragment extends Fragment {

    private TextView textDetails;
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> fbCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if(profile != null) {
                textDetails.setText("Welcome " + profile.getFirstName() + "!");
                /*
                Fragment s_fragment = new SuggestionFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.beginTransaction()
                        .replace(R.id.fragment_suggestion,s_fragment).commit();
                 */
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Log.d("ERROR", error.getMessage());
        }
    };

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.fb_login_button);
        textDetails = (TextView)view.findViewById(R.id.text_details);
        loginButton.setReadPermissions("user_friends", "email", "user_events");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, fbCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
