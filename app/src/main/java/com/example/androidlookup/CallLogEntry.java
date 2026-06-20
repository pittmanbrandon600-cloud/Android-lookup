package com.example.androidlookup;

public class CallLogEntry {
    private String phoneNumber;
    private String callType;
    private long timestamp;
    private long duration;
    private String contactName;

    // Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        return "CallLogEntry{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", callType='" + callType + '\'' +
                ", timestamp=" + timestamp +
                ", duration=" + duration +
                ", contactName='" + contactName + '\'' +
                '}';
    }
}