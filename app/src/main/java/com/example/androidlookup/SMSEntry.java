package com.example.androidlookup;

public class SMSEntry {
    private String phoneNumber;
    private String message;
    private long timestamp;
    private String type;
    private boolean read;

    // Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "SMSEntry{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", read=" + read +
                '}';
    }
}