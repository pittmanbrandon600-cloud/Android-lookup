package com.example.androidlookup;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import java.util.ArrayList;
import java.util.List;

public class ContactsReader {
    private Context context;

    public ContactsReader(Context context) {
        this.context = context;
    }

    /**
     * Retrieves all contacts from the device
     */
    public List<ContactEntry> getAllContacts() {
        List<ContactEntry> contacts = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    ContactsContract.Contacts.CONTENT_URI,
                    new String[]{
                            ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME,
                            ContactsContract.Contacts.HAS_PHONE_NUMBER,
                            ContactsContract.Contacts.PHOTO_URI
                    },
                    null,
                    null,
                    ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String displayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    int hasPhone = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    String photoUri = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI));

                    ContactEntry contact = new ContactEntry();
                    contact.setContactId(contactId);
                    contact.setDisplayName(displayName);
                    contact.setPhotoUri(photoUri);

                    if (hasPhone > 0) {
                        List<String> phoneNumbers = getPhoneNumbers(contactId);
                        contact.setPhoneNumbers(phoneNumbers);
                    }

                    List<String> emails = getEmailAddresses(contactId);
                    contact.setEmails(emails);

                    contacts.add(contact);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contacts;
    }

    /**
     * Gets phone numbers for a specific contact
     */
    private List<String> getPhoneNumbers(String contactId) {
        List<String> phoneNumbers = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{contactId},
                    null
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phoneNumbers.add(phoneNumber);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return phoneNumbers;
    }

    /**
     * Gets email addresses for a specific contact
     */
    private List<String> getEmailAddresses(String contactId) {
        List<String> emails = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Email.ADDRESS},
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                    new String[]{contactId},
                    null
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    emails.add(email);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emails;
    }
}