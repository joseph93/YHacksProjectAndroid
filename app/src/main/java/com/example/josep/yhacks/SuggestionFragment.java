package com.example.josep.yhacks;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestionFragment extends Fragment {

    private AccessToken token;
    public SuggestionFragment() {
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
        return inflater.inflate(R.layout.fragment_suggestion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       final TextView mTextView = (TextView) view.findViewById(R.id.response);
        Bundle bundle = this.getArguments();
        token = bundle.getParcelable("token");

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="http://graph.facebook.com/search?q=coffee&type=place&center=37.76,-122.427&distance=1000";

        /*

// Request a string response from the provided URL.
       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
       queue.add(stringRequest);
*/

        GraphRequest request = GraphRequest.newGraphPathRequest(
                token,
                "/search",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        // Insert your code here
                        mTextView.setText("Response is: "+ response);
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("q", "coffee");
        parameters.putString("type", "place");
        parameters.putString("center", "37.76,-122.427");
        parameters.putString("distance", "1000");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
