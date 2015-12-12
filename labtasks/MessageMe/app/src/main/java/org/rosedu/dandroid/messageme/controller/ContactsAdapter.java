package org.rosedu.dandroid.messageme.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.rosedu.dandroid.messageme.R;
import org.rosedu.dandroid.messageme.entities.Contact;
import org.rosedu.dandroid.messageme.views.ContactsFragment;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<Contact> data;

    public ContactsAdapter(List<Contact> data) {
        this.data = data;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // TODO: exercise 2e - implement method
        // inflate the contact layout using the layout inflater obtained from the parent context
        // set the on click event listener

        return null;
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {

        // TODO: exercise 2e - implement method

    }

    @Override
    public int getItemCount() {

        // TODO: exercise 2e - implement method

        return 0;
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {

        TextView usernameTextView;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            usernameTextView = (TextView)itemView.findViewById(R.id.username_text_view);
        }
    }
}
