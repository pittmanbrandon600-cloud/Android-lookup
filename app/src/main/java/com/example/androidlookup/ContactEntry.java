package com.example.androidlookup;

import java.util.List;

public class ContactEntry {
    private String contactId;
    private String displayName;
    private List<String> phoneNumbers;
    private List<String> emails;
    private String photoUri;

    // Getters and Setters
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    @Override
    public String toString() {
        return "ContactEntry{" +
                "contactId='" + contactId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", emails=" + emails +
                ", photoUri='" + photoUri + '\'' +
                '}';
    }
}