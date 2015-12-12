package org.rosedu.dandroid.messageme.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.rosedu.dandroid.messageme.R;
import org.rosedu.dandroid.messageme.entities.Contact;
import org.rosedu.dandroid.messageme.general.Constants;
import org.rosedu.dandroid.messageme.network.CustomRequest;
import org.rosedu.dandroid.messageme.network.VolleyController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactsFragment extends Fragment {

    private TextView contactsTextView;

    private RecyclerView contactsRecyclerView;

    private List<Contact> contactsList;
    private int senderId;
    private String senderUsername;

    public static ContactsClickListener contactsClickListener;

    private class ContactsClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int position = contactsRecyclerView.getChildPosition(view);

            // TODO: exercise 02g - create an Intent to WriteMessage activity using sender_id, sender_username, recipient_id, recipient_username

        }

    }

    public ContactsFragment() {
        contactsList = new ArrayList<>();
        contactsClickListener = new ContactsClickListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactsTextView = (TextView)getActivity().findViewById(R.id.contacts_text_view);

        // TODO: exercise 02d
        // obtain reference to contacts recycler view
        // set the layout manager (of type LinearLayoutManager)

        senderId = Integer.parseInt(getActivity().getIntent().getStringExtra(Constants.USER_ID_ATTRIBUTE));
        senderUsername = getActivity().getIntent().getStringExtra(Constants.USERNAME_ATTRIBUTE);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(Constants.USERNAME_ATTRIBUTE, senderUsername);
        CustomRequest<JSONArray> customRequest = new CustomRequest(
                Request.Method.POST,
                Constants.USER_LIST_WEB_SERVICE_ADDRESS,
                parameters,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int position = 0; position < response.length(); position++) {
                                JSONObject user = response.getJSONObject(position);
                                final int recipientId = Integer.parseInt(user.get(Constants.USER_ID_ATTRIBUTE).toString());
                                final String recipientUsername = user.get(Constants.USERNAME_ATTRIBUTE).toString();
                                Contact contact = new Contact(recipientId, recipientUsername);
                                contactsList.add(contact);
                            }

                            // TODO: exercise 02f - create ContactsAdapter instance and set it to the contactsRecyclerView

                        } catch (JSONException jsonException) {
                            Log.e(Constants.TAG, jsonException.getMessage());
                            if (Constants.DEBUG) {
                                jsonException.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Snackbar.make(contactsTextView, getResources().getString(R.string.an_error_has_occurred), Snackbar.LENGTH_LONG)
                                .show();
                    }

                },
                JSONArray.class
        );
        VolleyController.getInstance(getActivity().getApplicationContext()).addToRequestQueue(customRequest);
    }

}
