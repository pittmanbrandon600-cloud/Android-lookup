package com.example.androidlookup;

public class MediaEntry {
    private long id;
    private String displayName;
    private String path;
    private long dateModified;
    private long size;
    private int width;
    private int height;
    private long duration; // For videos
    private String type; // "Image" or "Video"

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDateModified() {
        return dateModified;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MediaEntry{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", path='" + path + '\'' +
                ", dateModified=" + dateModified +
                ", size=" + size +
                ", width=" + width +
                ", height=" + height +
                ", duration=" + duration +
                ", type='" + type + '\'' +
                '}';
    }
}