package com.example.androidlookup;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import java.util.ArrayList;
import java.util.List;

public class MediaReader {
    private Context context;

    public MediaReader(Context context) {
        this.context = context;
    }

    /**
     * Retrieves all images/pictures from the device
     */
    public List<MediaEntry> getAllImages() {
        List<MediaEntry> images = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    new String[]{
                            MediaStore.Images.Media._ID,
                            MediaStore.Images.Media.DISPLAY_NAME,
                            MediaStore.Images.Media.DATA,
                            MediaStore.Images.Media.DATE_MODIFIED,
                            MediaStore.Images.Media.SIZE,
                            MediaStore.Images.Media.WIDTH,
                            MediaStore.Images.Media.HEIGHT
                    },
                    null,
                    null,
                    MediaStore.Images.Media.DATE_MODIFIED + " DESC"
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    MediaEntry entry = new MediaEntry();
                    entry.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)));
                    entry.setDisplayName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)));
                    entry.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                    entry.setDateModified(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)));
                    entry.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
                    entry.setWidth(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)));
                    entry.setHeight(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)));
                    entry.setType("Image");
                    
                    images.add(entry);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return images;
    }

    /**
     * Retrieves all videos from the device
     */
    public List<MediaEntry> getAllVideos() {
        List<MediaEntry> videos = new ArrayList<>();
        
        try {
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    new String[]{
                            MediaStore.Video.Media._ID,
                            MediaStore.Video.Media.DISPLAY_NAME,
                            MediaStore.Video.Media.DATA,
                            MediaStore.Video.Media.DATE_MODIFIED,
                            MediaStore.Video.Media.SIZE,
                            MediaStore.Video.Media.DURATION,
                            MediaStore.Video.Media.WIDTH,
                            MediaStore.Video.Media.HEIGHT
                    },
                    null,
                    null,
                    MediaStore.Video.Media.DATE_MODIFIED + " DESC"
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    MediaEntry entry = new MediaEntry();
                    entry.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)));
                    entry.setDisplayName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
                    entry.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                    entry.setDateModified(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)));
                    entry.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
                    entry.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));
                    entry.setWidth(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)));
                    entry.setHeight(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)));
                    entry.setType("Video");
                    
                    videos.add(entry);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return videos;
    }

    /**
     * Retrieves all images and videos (both media types)
     */
    public List<MediaEntry> getAllMedia() {
        List<MediaEntry> allMedia = new ArrayList<>();
        allMedia.addAll(getAllImages());
        allMedia.addAll(getAllVideos());
        return allMedia;
    }
}