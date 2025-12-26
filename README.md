# NoteApp - Android Note-Taking Application

NoteApp is a functional and intuitive Android application built with Java and Android Studio. It allows users to efficiently manage their daily tasks and thoughts through a streamlined note-taking interface supported by local SQLite storage.

## Features

* **Create & Save Notes:** Easily add new notes with a title and detailed content.


* **View & Edit:** Access all saved notes in a list and tap any item to modify its details.


* **Search Functionality:** Quickly find specific notes using a real-time search bar that filters by title.


* **Delete with Confirmation:** Remove unwanted notes with a confirmation dialog to prevent accidental data loss.


* **Data Validation:** Includes input checks to ensure notes are not saved with empty titles or details.


* **Persistent Storage:** All data is stored locally using an SQLite database, ensuring notes remain available even after closing the app.



## Technical Architecture

The project follows a modular structure to ensure maintainability and clean architecture:

* **`MainActivity.java`**: The primary interface that displays the list of notes and handles search queries.


* **`DetailsActivity.java`**: Manages the UI for adding, viewing, and editing individual notes.


* **`DBHelper.java`**: A custom `SQLiteOpenHelper` class that handles all database operations (CRUD).


* **`Note.java`**: The data model class representing a note object (ID, Title, Details).


* **`NotesAdapter.java`**: A custom `RecyclerView.Adapter` that manages how note data is bound to the list view.



## Database Schema

The application uses an SQLite table named `notes` with the following structure:

| Column | Type | Description |
| --- | --- | --- |
| id | INTEGER | Primary Key (Autoincrement) |
| title | TEXT | The title of the note |
| details | TEXT | The full content of the note |

## Prerequisites

* **Android Studio** (Hedgehog or newer recommended)
* **JDK 11** or higher
* **Android SDK** (API Level 21+ supported)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/wazteth/simple-note-taking-app.git

```


2. Open the project in **Android Studio**.
3. Let the **Gradle** sync complete.
4. Run the application on an **Android Emulator** or a physical device.

## Testing

The application has been verified through several test cases:

* **Add Note:** Verified that notes appear on the home screen after saving.


* **Edit Note:** Confirmed that modifications are correctly updated in the database.


* **Search:** Tested the real-time filtering of the note list.


* **Validation:** Verified toast messages appear when attempting to save empty fields.



## Future Enhancements

* Implementation of **Cloud Backup** for data synchronization.


* Addition of **Security Features** such as biometric authentication or encrypted storage.


* Support for **Dark Mode** and other accessibility features.


* Note **Categorization** and tagging for better organization.
