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
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact, parent, false);
        contactView.setOnClickListener(ContactsFragment.contactsClickListener);
        return new ContactsViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        TextView usernameTextView = holder.usernameTextView;
        usernameTextView.setText(data.get(position).getRecipientUsername());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {

        TextView usernameTextView;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            usernameTextView = (TextView)itemView.findViewById(R.id.username_text_view);
        }
    }
}
