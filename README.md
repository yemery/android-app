# Project Management App

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Database Schema](#database-schema)
- [Installation](#installation)
- [Usage](#usage)
- [Code Structure](#code-structure)
- [API Reference](#api-reference)


## Features

### User Management 👤
- User registration with validation
- Secure login/logout
- Profile viewing and editing
- Account deletion

### Project Management 📂
- Create projects with details (name, description, dates, status)
- View all projects in list/grid
- Edit existing projects
- Delete projects
- Filter projects by status/user

### Database Operations 💾
- Local SQLite storage
- Efficient CRUD operations
- Data relationships (user-projects)
- Data validation

## Tech Stack

- **Frontend**: 
  - Android XML layouts
  - Material Design Components
- **Backend**: 
  - Java 8
  - Android SDK
- **Database**: 
  - SQLite
  - Room (optional)
- **Architecture**: 
  - MVC Pattern
  - DAO pattern

## Database Schema

```sql
-- Users table
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT,
    email TEXT UNIQUE NOT NULL,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);

-- Projects table
CREATE TABLE projects (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    start_date TEXT,
    end_date TEXT,
    status TEXT,
    user_id INTEGER,
    FOREIGN KEY(user_id) REFERENCES users(id)
);
```

## Installation

- **Prerequisites**: 
  - Android Studio Flamingo or later
  - Android SDK 33+
  - Java JDK 11+
  
- **Setup**: 
  - Android Studio Flamingo or later
  - Android SDK 33+
  - Java JDK 11+

 ```
git clone https://github.com/yourusername/project-management-app.git
cd project-management-app
```

- **Build**: 
  - Open project in Android Studio
  - Sync Gradle dependencies
  - Build -> Make Project
 
- **Run**: 
  - Select target device (emulator or physical device)
  - Click Run button
 
## Usage

- **Authentication**: 
  - Register a new account
  - Login with credentials
  
- **Projects**: 
  - Tap "+" to add new project
  - Swipe left/right to edit/delete

- **Profile**: 
  - Access from navigation drawer
  - Edit personal information
  - Change password option
  - Delete account option

## Code Structure
```
src/
├── main/
│   ├── java/com/example/mobile_project_sqlite3/
│   │   ├── controller/       # Business logic
│   │   │   ├── ProjectController.java
│   │   │   └── UserController.java
│   │   ├── dao/              # Data Access
│   │   │   ├── impl/
│   │   │   └── api/
│   │   ├── db/               # Database
│   │   │   ├── DatabaseHelper.java
│   │   │   └── DatabaseOperations.java
│   │   ├── model/            # Data models
│   │   │   ├── Project.java
│   │   │   └── User.java
│   │   ├── service/          # Service layer
│   │   │   ├── impl/
│   │   │   └── api/
│   │   └── view/             # UI
│   │       ├── activity/
│   │       │   ├── auth/      # Auth screens
│   │       │   └── project/   # Project screens
│   │       └── adapter/       # RecyclerView adapters
│   └── res/                  # Resources
│       ├── layout/            # XML layouts
│       ├── values/            # Strings, colors etc.
│       └── drawable/          # Images/icons

```

## API Reference
- **UserController.java**:
  ```
  public boolean registerUser(String firstName, String lastName, 
                          String email, String username, String password);
  public User loginUser(String username, String password);
  public boolean updateUser(User updatedUser);
  public boolean deleteUser(long userId);
  public User getUserById(long userId);

  ```
- **ProjectController.java**:
  ```
  public long createProject(Project project);
  public boolean updateProject(Project project);
  public boolean deleteProject(long projectId);
  public Project getProjectById(long projectId);
  public List<Project> getAllProjects();
  public List<Project> getProjectsByUserId(long userId);
  public List<Project> getProjectsByStatus(String status);
  ```









