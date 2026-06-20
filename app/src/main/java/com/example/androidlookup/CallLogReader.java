package com.example.androidlookup;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import java.util.ArrayList;
import java.util.List;

public class CallLogReader {
    private Context context;

    public CallLogReader(Context context) {
        this.context = context;
    }

    /**
     * Retrieves all call logs from the device
     */
    public List<CallLogEntry> getAllCallLogs() {
        List<CallLogEntry> callLogs = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    CallLog.Calls.CONTENT_URI,
                    new String[]{
                            CallLog.Calls._ID,
                            CallLog.Calls.NUMBER,
                            CallLog.Calls.TYPE,
                            CallLog.Calls.DATE,
                            CallLog.Calls.DURATION,
                            CallLog.Calls.CACHED_NAME
                    },
                    null,
                    null,
                    CallLog.Calls.DATE + " DESC"
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    CallLogEntry entry = new CallLogEntry();
                    entry.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER)));
                    entry.setCallType(getCallType(cursor.getInt(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE))));
                    entry.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE)));
                    entry.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION)));
                    entry.setContactName(cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME)));
                    
                    callLogs.add(entry);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return callLogs;
    }

    /**
     * Gets call logs for a specific contact
     */
    public List<CallLogEntry> getCallLogsForContact(String phoneNumber) {
        List<CallLogEntry> callLogs = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    CallLog.Calls.CONTENT_URI,
                    new String[]{
                            CallLog.Calls._ID,
                            CallLog.Calls.NUMBER,
                            CallLog.Calls.TYPE,
                            CallLog.Calls.DATE,
                            CallLog.Calls.DURATION,
                            CallLog.Calls.CACHED_NAME
                    },
                    CallLog.Calls.NUMBER + "=?",
                    new String[]{phoneNumber},
                    CallLog.Calls.DATE + " DESC"
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    CallLogEntry entry = new CallLogEntry();
                    entry.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER)));
                    entry.setCallType(getCallType(cursor.getInt(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE))));
                    entry.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE)));
                    entry.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION)));
                    entry.setContactName(cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME)));
                    
                    callLogs.add(entry);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return callLogs;
    }

    private String getCallType(int type) {
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                return "Incoming";
            case CallLog.Calls.OUTGOING_TYPE:
                return "Outgoing";
            case CallLog.Calls.MISSED_TYPE:
                return "Missed";
            default:
                return "Unknown";
        }
    }
}