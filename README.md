# 🎫 Ticket Booking System

A **Java-based console ticket booking system** built with **Gradle**.

The application allows users to create accounts, authenticate, search trains, view seat availability, book seats, manage bookings, and maintain their login session between application runs.

The project uses **local JSON files as a lightweight database**, so no external database server such as MySQL or PostgreSQL is required.

---

## ✨ Features

* 👤 User registration and login
* 🔐 Password hashing using BCrypt
* 💾 Persistent login sessions
* 🚆 Train search with station timings
* 💺 Seat availability and seat booking
* 🎫 Ticket generation and booking history
* ❌ Booking cancellation
* 🚪 Login/logout session management
* 📄 JSON-based local data persistence
* 🧪 Unit testing with JUnit
* ⚙️ Gradle build and dependency management
* 🪟 One-click Windows setup using `setup.bat`

---

## 🛠️ Technology Stack

| Technology     | Purpose                                |
| -------------- | -------------------------------------- |
| **Java 22**    | Application development                |
| **Gradle 9.8** | Build and dependency management        |
| **Jackson**    | JSON serialization and deserialization |
| **jBCrypt**    | Password hashing                       |
| **Lombok**     | Boilerplate code reduction             |
| **JUnit**      | Unit testing                           |
| **JSON Files** | Local data persistence                 |

---

# 🚀 Quick Start

> **Windows users can run the project with almost no technical setup.**

### 1. Install Java 22

This project requires **Java 22**.

After installing Java, verify the installation:

```text
java -version
```

You should see Java version `22`.

### 2. Download the Project

Clone the repository:

```bash
git clone https://github.com/Babar-5566/ticketBooking-gradle.git
```

Or download the repository as a ZIP from GitHub and extract it.

### 3. Start the Application

Open the project folder and **double-click**:

```text
setup.bat
```

The setup script will:

1. Check the Java installation.
2. Check the Java version.
3. Build the project using the Gradle Wrapper.
4. Download required Gradle dependencies.
5. Start the application.

### Do I need to install Gradle?

**No.**

The project includes the **Gradle Wrapper**, so Gradle does not need to be installed separately.

---

# ▶️ Running the Application

After starting the application, you will see the console menu.

### Guest Menu

```text
1. Sign up
2. Login
3. Search Trains
4. Exit the App
```

Guests can search trains, but authentication is required before booking a seat.

### Logged-in Menu

```text
1. Fetch My Bookings
2. Search Trains
3. Book a Seat
4. Cancel My Booking
5. Logout
6. Exit the App
```

---

# 👤 Demo Accounts

The repository contains dummy accounts for testing.

| Username          | Password |
| ----------------- | -------- |
| `Lovepreet Singh` | `12345`  |
| `rahul`           | `hhbb11` |

These credentials are provided **for demonstration purposes only**.

You can also create a new account using the **Sign Up** option.

---

# 🎫 How the Application Works

## 1. Create an Account

From the guest menu, select:

```text
1. Sign up
```

Provide the required account details to create a new user.

---

## 2. Login

Select:

```text
2. Login
```

Enter your username and password.

After successful authentication, the application stores the user's session so it can be restored when the application is started again.

---

## 3. Search for Trains

Select:

```text
Search Trains
```

Enter the:

```text
Source
Destination
```

The application displays matching trains along with their station timings.

Logged-in users can select a train for booking.

---

## 4. Book a Seat

After selecting a train, choose:

```text
Book a Seat
```

The application displays the available seat layout.

Example:

```text
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
```

Where:

```text
0 = Available
1 = Booked
```

Select the required row and column and provide the travel date.

A ticket is created and added to the user's bookings.

---

## 5. View Bookings

Select:

```text
Fetch My Bookings
```

The application displays information such as:

* Ticket ID
* User ID
* Source
* Destination
* Travel date
* Train ID
* Train number
* Seat row
* Seat column
* Station timings

---

## 6. Cancel a Booking

Select:

```text
Cancel My Booking
```

Enter the **Ticket ID** of the booking you want to cancel.

---

## 7. Logout

Select:

```text
Logout
```

The current session is cleared and the application returns to the guest state.

---

# 💾 Local Database

Instead of using an external database server, the application stores its data in JSON files.

The files are located at:

```text
app/src/main/java/ticket/booking/localDB/
```

### `users.json`

Stores registered users and their booking information.

### `trains.json`

Stores train information, station timings, and seat availability.

### `session.json`

Stores the currently logged-in user's ID, allowing the application to restore the session when restarted.

---

# 📁 Project Structure

```text
ticketBooking-gradle/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── ticket/
│   │   │           └── booking/
│   │   │               ├── App.java
│   │   │               ├── Action.java
│   │   │               ├── entities/
│   │   │               ├── service/
│   │   │               ├── util/
│   │   │               └── localDB/
│   │   │                   ├── users.json
│   │   │                   ├── trains.json
│   │   │                   └── session.json
│   │   │
│   │   └── test/
│   │
│   └── build.gradle.kts
│
├── gradle/
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
├── setup.bat
└── README.md
```

### Main Components

* **`App.java`** — Console interface and application flow
* **`Action.java`** — Represents application actions/menu operations
* **`entities/`** — Core data models such as users, trains, and tickets
* **`service/`** — Business logic and application services
* **`util/`** — Utility/helper classes
* **`localDB/`** — Local JSON data storage

---

# 🧪 Testing

Run the test suite with:

```bash
gradlew.bat test
```

Run a complete build:

```bash
gradlew.bat build
```

---

# 👨‍💻 Developer Setup

Developers can run the application directly through the Gradle Wrapper.

### Build

```bash
gradlew.bat build
```

### Run

```bash
gradlew.bat run --console=plain
```

### Run Tests

```bash
gradlew.bat test
```

### Clean Build

```bash
gradlew.bat clean build
```

> The Gradle Wrapper is included in the repository, so a separate Gradle installation is not required.

---

# 🔐 Security & Limitations

This is an **educational console application** and is not intended for production deployment in its current form.

The project uses **jBCrypt for password hashing**, but the local demonstration database contains dummy credential fields for convenience.

For a production system, additional security measures would be required, including:

* Secure secret and credential management
* Proper database-backed persistence
* Stronger authentication and authorization controls
* Input validation and sanitization
* Secure session management
* Proper logging and monitoring
* Protection against concurrent booking conflicts
* Removal of demonstration credentials from the database

**Never commit real passwords, API keys, database credentials, or other secrets to GitHub.**

---

# 🎯 Project Purpose

This project was developed to practice and demonstrate:

* Java OOP
* Collections and data structures
* Exception handling
* File handling
* JSON serialization/deserialization
* Password hashing
* Service-layer design
* Session management
* Gradle
* Unit testing
* Git and GitHub

The architecture is intentionally simple and can be extended toward a more production-oriented application.

---

# 🔮 Future Improvements

Possible future enhancements include:

* Migration from JSON files to a relational database
* REST API using Spring Boot
* Web or mobile frontend
* Role-based access control
* Improved authentication and session management
* Transaction-safe seat booking
* Search and filtering improvements
* Comprehensive integration testing
* Docker-based deployment
* CI/CD pipeline

---

# 👨‍💻 Author

**Sk Babar Ali**

Java / Computer Science Student

[GitHub](https://github.com/Babar-5566)
