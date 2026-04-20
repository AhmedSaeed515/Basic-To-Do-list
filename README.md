# ToDo List — Android App

A simple Android task management application built with Java. Users can create, view, update, and delete tasks, with each task having a name and a description. Data is persisted locally using SQLite.

---

## Features

- **Add tasks** — Create a new task with a name and description via a dedicated screen.
- **View tasks** — All tasks are displayed in a scrollable list on the main screen.
- **Task details** — Tap any task to view its full name and description.
- **Edit tasks** — Long-press a task to open a context menu and choose Edit to update its name or description.
- **Delete tasks** — Long-press a task and choose Delete; a confirmation dialog prevents accidental removal.
- **Persistent storage** — All tasks are saved locally in an SQLite database, so data survives app restarts.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Platform | Android (minSdk 29, targetSdk 32) |
| Database | SQLite via `SQLiteOpenHelper` |
| UI | XML Layouts, ListView, FloatingActionButton |
| Build | Gradle 7.3.3 |

---

## Project Structure

```
ToDo_List/
├── app/
│   └── src/main/
│       ├── java/com/example/todo_list/
│       │   ├── MainActivity.java       # Main screen — task list, context menu
│       │   ├── addTaskScreen.java      # Screen for adding a new task
│       │   ├── updateTask.java         # Screen for editing an existing task
│       │   ├── description.java        # Screen for viewing task details
│       │   └── database.java          # SQLite helper — CRUD operations
│       ├── res/
│       │   ├── layout/                 # XML layouts for all 4 screens
│       │   ├── menu/taskmenu.xml       # Context menu (Edit / Delete)
│       │   └── values/strings.xml      # String resources
│       └── AndroidManifest.xml
├── build.gradle
└── settings.gradle
```

---

## Database Schema

The app uses a single SQLite table:

```sql
CREATE TABLE task (
    id          INTEGER PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT
);
```

### `database.java` — Available Methods

| Method | Description |
|---|---|
| `Newtask(name, desc)` | Inserts a new task |
| `fetchallTask()` | Returns a cursor with all tasks |
| `getTaskdesc(name)` | Returns the description of a specific task |
| `UpdateTask(oldname, newname, newdesc)` | Updates an existing task |
| `DeleteTask(name)` | Deletes a task by name |

---

## Screens & Navigation

```
MainActivity (Task List)
    │
    ├── [FAB +] ──────────────► addTaskScreen → back to MainActivity
    │
    ├── [Tap task] ───────────► description (view-only) → back to MainActivity
    │
    └── [Long-press task]
            ├── Edit ─────────► updateTask → back to MainActivity
            └── Delete ───────► AlertDialog → removes from list & DB
```

---

## Getting Started

### Prerequisites

- Android Studio (Electric Eel or newer recommended)
- Android device or emulator running **API 29+** (Android 10+)

### Run the App

1. Clone or extract the project.
2. Open **Android Studio** → `File > Open` → select the `ToDo_List` folder.
3. Let Gradle sync finish.
4. Click **Run ▶** or use `Shift+F10`.

A prebuilt debug APK is also available at:
```
app/build/intermediates/apk/debug/app-debug.apk
```

---

## Notes

- The app does not validate empty task names — adding a task without a name is currently allowed.
- The context menu (Edit/Delete) is triggered by a **long-press** on any list item.
- The ActionBar color is `#89B7CC` (a soft blue) applied consistently across all screens.
