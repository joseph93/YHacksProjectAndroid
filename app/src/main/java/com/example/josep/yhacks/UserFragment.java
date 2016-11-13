package com.example.josep.yhacks;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private Profile profile;
    private ImageView profilePic;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilePic = (ImageView)view.findViewById(R.id.profilePic);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            profile = bundle.getParcelable("profile");
            if(profile != null) {
                Log.d("IMAGE_VIEW", "Image URI: " + profile.getProfilePictureUri(30,30).toString());
                profilePic.setImageURI(profile.getProfilePictureUri(30,30));
            }

        }
    }
}
