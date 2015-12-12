package org.rosedu.dandroid.messageme.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.rosedu.dandroid.messageme.R;
import org.rosedu.dandroid.messageme.entities.Message;
import org.rosedu.dandroid.messageme.general.Constants;
import org.rosedu.dandroid.messageme.network.CustomRequest;
import org.rosedu.dandroid.messageme.network.VolleyController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MessagesFragment extends Fragment {

    TextView messagesTextView;

    private ActionMode applicationActionMode;

    public MessagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        messagesTextView = (TextView)getActivity().findViewById(R.id.messages_text_view);
        final LayoutInflater inflater = (LayoutInflater)getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final String userId = getActivity().getIntent().getStringExtra(Constants.USER_ID_ATTRIBUTE);
        final String username = getActivity().getIntent().getStringExtra(Constants.USERNAME_ATTRIBUTE);
        final ListView messagesListView = (ListView)getActivity().findViewById(R.id.messages_list_view);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(Constants.USER_ID_ATTRIBUTE, userId);
        CustomRequest<JSONArray> customRequest = new CustomRequest(
                Request.Method.POST,
                Constants.MESSAGE_LIST_WEB_SERVICE_ADDRESS,
                parameters,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            final List<Message> messages = new ArrayList<>();
                            for (int position = 0; position < response.length(); position++) {
                                JSONObject dispatch = response.getJSONObject(position);
                                final int messageId = Integer.parseInt(dispatch.get(Constants.MESSAGE_ID_ATTRIBUTE).toString());
                                final String sender = dispatch.get(Constants.SENDER_ATTRIBUTE).toString();
                                final String receiver = dispatch.get(Constants.RECIPIENT_ATTRIBUTE).toString();
                                final String subject = dispatch.get(Constants.SUBJECT_ATTRIBUTE).toString();
                                final String content = dispatch.get(Constants.CONTENT_ATTRIBUTE).toString();
                                final String timestamp = dispatch.get(Constants.TIMESTAMP_ATTRIBUTE).toString();
                                final String status = dispatch.get(Constants.STATUS_ATTRIBUTE).toString();
                                Message message = new Message(messageId, sender, receiver, subject, content, timestamp, status);
                                messages.add(message);
                            }

                            // TODO: exercise 01b - create MessagesAdapter instance and set it to the messagesListView

                            messagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    // TODO: exercise 01c

                                }
                            });
                            messagesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                public boolean onItemLongClick(AdapterView adapterView, View view, int position, long id) {
                                    if (applicationActionMode != null) {
                                        return false;
                                    }
                                    applicationActionMode = getActivity().startActionMode(new ActionMode.Callback() {
                                        @Override
                                        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

                                            // TODO: exercise 04 - implement method

                                            return false;
                                        }

                                        @Override
                                        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                                            // TODO: exercise 04 - implement method

                                            return true;
                                        }

                                        @Override
                                        public void onDestroyActionMode(ActionMode actionMode) {

                                            // TODO: exercise 04 - implement method

                                        }
                                    });
                                    view.setSelected(true);
                                    return true;
                                }
                            });
                            getActivity().registerForContextMenu(messagesListView);
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
                        Snackbar.make(messagesTextView, getResources().getString(R.string.an_error_has_occurred), Snackbar.LENGTH_LONG)
                                .show();
                    }

                },
                JSONArray.class
        );
        VolleyController.getInstance(getActivity().getApplicationContext()).addToRequestQueue(customRequest);

        // TODO: exercise 04 - initialize ActionMode object

    }

}
