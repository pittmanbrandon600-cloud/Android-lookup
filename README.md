# Android-lookup

A comprehensive Android application for accessing and managing phone data including call logs, text messages, contacts, pictures, and videos.

## Features

- **📞 Call Logs**: View all incoming, outgoing, and missed calls with timestamps and durations
- **💬 Text Messages**: Access SMS messages with sender information, content, and read status
- **👥 Contacts**: Browse all device contacts with phone numbers, email addresses, and photos
- **📸 Media**: View all pictures and videos stored on the device with metadata
- **🔐 Runtime Permissions**: Proper handling of Android runtime permissions

## Architecture

### Data Models
- `CallLogEntry.java` - Represents a single call log entry
- `SMSEntry.java` - Represents a single text message
- `ContactEntry.java` - Represents a contact with phone numbers and emails
- `MediaEntry.java` - Represents an image or video file

### Data Readers
- `CallLogReader.java` - Queries and retrieves call log data
- `SMSReader.java` - Queries and retrieves SMS message data
- `ContactsReader.java` - Queries and retrieves contact information
- `MediaReader.java` - Queries and retrieves image and video data

### Utilities
- `PermissionHandler.java` - Manages runtime permissions for Android 6.0+
- `MainActivity.java` - Main UI activity with data loading functionality

## Required Permissions

The application requires the following permissions in `AndroidManifest.xml`:
- `READ_CALL_LOG` - Access to call history
- `READ_SMS` - Access to text messages
- `READ_CONTACTS` - Access to contact list
- `READ_EXTERNAL_STORAGE` - Access to photos and videos
- `ACCESS_MEDIA_LOCATION` - Access to media location metadata

## Usage

1. **Request Permissions**: Tap "Check Permissions" to grant required permissions
2. **Load Call Logs**: Tap "Load Call Logs" to retrieve all call history
3. **Load Messages**: Tap "Load Text Messages" to retrieve all SMS messages
4. **Load Contacts**: Tap "Load Contacts" to retrieve all device contacts
5. **Load Media**: Tap "Load Media" to retrieve all photos and videos

## Requirements

- **Minimum API Level**: 24 (Android 7.0)
- **Target API Level**: 34 (Android 14)
- **Language**: Java 8+

## Dependencies

- AndroidX AppCompat
- AndroidX ConstraintLayout
- AndroidX RecyclerView
- Google Material Components
- AndroidX Core

## Installation

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on device or emulator (must have the required permissions granted)

## Security Considerations

⚠️ **Important**: This app requires sensitive permissions. Use responsibly and:
- Only use for authorized data access
- Comply with local privacy laws and regulations
- Do not use to access data without proper consent
- Properly handle and secure sensitive user information

## Future Enhancements

- RecyclerView adapters for all data types
- Export data to CSV/JSON formats
- Search and filter functionality
- Database caching
- More detailed statistics and analytics
- Backup and restore features

## License

MIT License

## Contributing

Contributions are welcome! Please submit pull requests or open issues for bug reports and feature requests.
