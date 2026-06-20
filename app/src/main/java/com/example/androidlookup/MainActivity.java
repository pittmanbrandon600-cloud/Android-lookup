package com.example.androidlookup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private Button btnCallLogs, btnMessages, btnContacts, btnMedia, btnPermissions;
    private TextView tvInfo;
    private RecyclerView rvData;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private CallLogReader callLogReader;
    private SMSReader smsReader;
    private ContactsReader contactsReader;
    private MediaReader mediaReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize readers
        callLogReader = new CallLogReader(this);
        smsReader = new SMSReader(this);
        contactsReader = new ContactsReader(this);
        mediaReader = new MediaReader(this);

        // Initialize UI elements
        btnPermissions = findViewById(R.id.btn_permissions);
        btnCallLogs = findViewById(R.id.btn_call_logs);
        btnMessages = findViewById(R.id.btn_messages);
        btnContacts = findViewById(R.id.btn_contacts);
        btnMedia = findViewById(R.id.btn_media);
        tvInfo = findViewById(R.id.tv_info);
        rvData = findViewById(R.id.rv_data);
        rvData.setLayoutManager(new LinearLayoutManager(this));

        // Check permissions
        if (!PermissionHandler.hasAllPermissions(this)) {
            PermissionHandler.requestPermissions(this);
            btnPermissions.setText("Grant Permissions");
        } else {
            btnPermissions.setText("Permissions Granted ✓");
        }

        // Button listeners
        btnPermissions.setOnClickListener(v -> {
            if (!PermissionHandler.hasAllPermissions(this)) {
                PermissionHandler.requestPermissions(this);
            } else {
                Toast.makeText(this, "All permissions already granted", Toast.LENGTH_SHORT).show();
            }
        });

        btnCallLogs.setOnClickListener(v -> loadCallLogs());
        btnMessages.setOnClickListener(v -> loadMessages());
        btnContacts.setOnClickListener(v -> loadContacts());
        btnMedia.setOnClickListener(v -> loadMedia());
    }

    private void loadCallLogs() {
        if (!PermissionHandler.hasPermission(this, android.Manifest.permission.READ_CALL_LOG)) {
            Toast.makeText(this, "Call log permission not granted", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute(() -> {
            List<CallLogEntry> callLogs = callLogReader.getAllCallLogs();
            runOnUiThread(() -> {
                tvInfo.setText("Total Call Logs: " + callLogs.size());
                // Set adapter for RecyclerView
                // TODO: Implement CallLogAdapter
                for (CallLogEntry entry : callLogs) {
                    System.out.println(entry);
                }
            });
        });
    }

    private void loadMessages() {
        if (!PermissionHandler.hasPermission(this, android.Manifest.permission.READ_SMS)) {
            Toast.makeText(this, "SMS permission not granted", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute(() -> {
            List<SMSEntry> messages = smsReader.getAllMessages();
            runOnUiThread(() -> {
                tvInfo.setText("Total Messages: " + messages.size());
                // Set adapter for RecyclerView
                // TODO: Implement SMSAdapter
                for (SMSEntry entry : messages) {
                    System.out.println(entry);
                }
            });
        });
    }

    private void loadContacts() {
        if (!PermissionHandler.hasPermission(this, android.Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(this, "Contacts permission not granted", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute(() -> {
            List<ContactEntry> contacts = contactsReader.getAllContacts();
            runOnUiThread(() -> {
                tvInfo.setText("Total Contacts: " + contacts.size());
                // Set adapter for RecyclerView
                // TODO: Implement ContactsAdapter
                for (ContactEntry entry : contacts) {
                    System.out.println(entry);
                }
            });
        });
    }

    private void loadMedia() {
        if (!PermissionHandler.hasPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Media permission not granted", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute(() -> {
            List<MediaEntry> allMedia = mediaReader.getAllMedia();
            List<MediaEntry> images = mediaReader.getAllImages();
            List<MediaEntry> videos = mediaReader.getAllVideos();

            runOnUiThread(() -> {
                tvInfo.setText("Total Media: " + allMedia.size() + " (Images: " + images.size() + ", Videos: " + videos.size() + ")");
                // Set adapter for RecyclerView
                // TODO: Implement MediaAdapter
                for (MediaEntry entry : allMedia) {
                    System.out.println(entry);
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}