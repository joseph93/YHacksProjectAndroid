package com.example.josep.yhacks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Fragment m_fragment = new SuggestionFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, m_fragment, m_fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
