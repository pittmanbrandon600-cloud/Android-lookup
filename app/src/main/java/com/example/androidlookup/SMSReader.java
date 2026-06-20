package com.example.androidlookup;

import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import java.util.ArrayList;
import java.util.List;

public class SMSReader {
    private Context context;

    public SMSReader(Context context) {
        this.context = context;
    }

    /**
     * Retrieves all SMS messages from the device
     */
    public List<SMSEntry> getAllMessages() {
        List<SMSEntry> messages = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    Telephony.Sms.CONTENT_URI,
                    new String[]{
                            Telephony.Sms._ID,
                            Telephony.Sms.ADDRESS,
                            Telephony.Sms.BODY,
                            Telephony.Sms.DATE,
                            Telephony.Sms.TYPE,
                            Telephony.Sms.READ
                    },
                    null,
                    null,
                    Telephony.Sms.DATE + " DESC"
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    SMSEntry entry = new SMSEntry();
                    entry.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)));
                    entry.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY)));
                    entry.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE)));
                    entry.setType(getMessageType(cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE))));
                    entry.setRead(cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.READ)) == 1);
                    
                    messages.add(entry);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

    /**
     * Gets SMS messages from a specific contact
     */
    public List<SMSEntry> getMessagesFromContact(String phoneNumber) {
        List<SMSEntry> messages = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    Telephony.Sms.CONTENT_URI,
                    new String[]{
                            Telephony.Sms._ID,
                            Telephony.Sms.ADDRESS,
                            Telephony.Sms.BODY,
                            Telephony.Sms.DATE,
                            Telephony.Sms.TYPE,
                            Telephony.Sms.READ
                    },
                    Telephony.Sms.ADDRESS + "=?",
                    new String[]{phoneNumber},
                    Telephony.Sms.DATE + " DESC"
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    SMSEntry entry = new SMSEntry();
                    entry.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)));
                    entry.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY)));
                    entry.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE)));
                    entry.setType(getMessageType(cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE))));
                    entry.setRead(cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.READ)) == 1);
                    
                    messages.add(entry);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

    private String getMessageType(int type) {
        switch (type) {
            case Telephony.Sms.MESSAGE_TYPE_INBOX:
                return "Inbox";
            case Telephony.Sms.MESSAGE_TYPE_SENT:
                return "Sent";
            case Telephony.Sms.MESSAGE_TYPE_DRAFT:
                return "Draft";
            default:
                return "Other";
        }
    }
}