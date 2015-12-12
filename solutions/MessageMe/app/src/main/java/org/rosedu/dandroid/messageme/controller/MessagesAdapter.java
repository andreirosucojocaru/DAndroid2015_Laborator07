package org.rosedu.dandroid.messageme.controller;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.rosedu.dandroid.messageme.R;
import org.rosedu.dandroid.messageme.entities.Message;
import org.rosedu.dandroid.messageme.general.Constants;

import java.util.List;

public class MessagesAdapter extends BaseAdapter {

    private Activity context;
    private List<Message> data;

    private LayoutInflater layoutInflater;

    private static class MessageViewHolder {
        TextView senderTextView, subjectTextView, contentTextView, dateAndTimeTextView;
    }

    public MessagesAdapter(Activity context, List<Message> data) {
        this.context = context;
        this.data = data;

        layoutInflater = context.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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
        Message message = data.get(position);
        String status = message.getStatus();
        View messageView = null;
        MessageViewHolder messageViewHolder = null;
        if (convertView == null) {
            if (position % 2 == 0) {
                messageView = layoutInflater.inflate(R.layout.message_even, parent, false);
            } else {
                messageView = layoutInflater.inflate(R.layout.message_odd, parent, false);
            }
            messageViewHolder = new MessageViewHolder();
            messageViewHolder.senderTextView = (TextView)messageView.findViewById(R.id.sender_text_view);
            messageViewHolder.subjectTextView = (TextView)messageView.findViewById(R.id.subject_text_view);
            messageViewHolder.contentTextView = (TextView)messageView.findViewById(R.id.content_text_view);
            messageViewHolder.dateAndTimeTextView = (TextView)messageView.findViewById(R.id.date_and_time_text_view);
            messageView.setTag(messageViewHolder);
        } else {
            messageView = convertView;
        }
        messageViewHolder = (MessageViewHolder)messageView.getTag();
        messageViewHolder.senderTextView.setText(message.getSender());
        setTypeface(status, messageViewHolder.senderTextView);
        messageViewHolder.subjectTextView.setText(message.getSubject());
        setTypeface(status, messageViewHolder.subjectTextView);
        messageViewHolder.contentTextView.setText(message.getContent());
        setTypeface(status, messageViewHolder.contentTextView);
        messageViewHolder.dateAndTimeTextView.setText(message.getTimestamp());
        setTypeface(status, messageViewHolder.dateAndTimeTextView);
        return messageView;
    }

    @Override
    public int getViewTypeCount() {
        return Constants.NUMBER_OF_LAYOUTS;
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 2);
    }

}
