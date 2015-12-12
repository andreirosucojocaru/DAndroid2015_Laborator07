package org.rosedu.dandroid.messageme.entities;

public class Contact {

    private int recipientId;
    private String recipientUsername;

    public Contact() {
        this.recipientId = -1;
        this.recipientUsername = null;
    }

    public Contact(int recipientId, String recipientUsername) {
        this.recipientId = recipientId;
        this.recipientUsername = recipientUsername;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }
}
