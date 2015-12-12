package org.rosedu.dandroid.messageme.controller;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.rosedu.dandroid.messageme.entities.Message;
import org.rosedu.dandroid.messageme.general.Constants;

import java.util.List;

public class MessagesAdapter extends BaseAdapter {

    private Activity context;
    private List<Message> data;

    public MessagesAdapter(Activity context, List<Message> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {

        // TODO: exercise 01a

        return 0;
    }

    @Override
    public Object getItem(int position) {

        // TODO: exercise 01a

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void setTypeface(String status, TextView textView) {
        switch(status) {
            case Constants.READ:
                textView.setTypeface(Typeface.DEFAULT);
                break;
            case Constants.UNREAD:
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO: exercise 01a

        return null;
    }

    @Override
    public int getViewTypeCount() {

        // TODO: exercise 01d

        return -1;
    }

    @Override
    public int getItemViewType(int position) {

        // TODO: exercise 01d

        return -1;
    }

}
