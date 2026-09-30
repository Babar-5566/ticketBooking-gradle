# 🎫 Ticket Booking System

A simple **Java-based console ticket booking system** built using **Gradle**.

The application allows users to:

* Create an account
* Login and stay logged in between application runs
* Search available trains
* Select a train
* View available seats
* Book a seat
* View their bookings
* Cancel bookings
* Logout
* Exit the application

The project uses local JSON files as a simple database, making it easy to run without installing MySQL, PostgreSQL, or any other database server.

---

## 🛠️ Technology Stack

* **Java 22**
* **Gradle 9.8**
* **Jackson** — JSON data handling
* **jBCrypt** — password hashing
* **Lombok**
* **JUnit** — testing

---

## 📁 Project Structure

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

---

# 🚀 Easy Setup

## Windows Users

### Step 1 — Install Java 22

This project requires **Java 22**.

After installing Java, open Command Prompt or PowerShell and run:

```text
java -version
```

You should see Java version 22.

---

### Step 2 — Download the project

Either clone the repository using Git:

```bash
git clone https://github.com/Babar-5566/ticketBooking-gradle.git
```

or download the project as a ZIP from GitHub and extract it.

---

### Step 3 — Run the setup

Open the project folder and double-click:

```text
setup.bat
```

The setup script will:

1. Check whether Java is installed.
2. Check the Java version.
3. Build the project using the included Gradle Wrapper.
4. Start the Ticket Booking System.

You **do not need to install Gradle separately**.

---

# ▶️ Running Manually

If you prefer using the terminal:

### Build the project

Windows:

```bash
gradlew.bat build
```

### Start the application

Windows:

```bash
gradlew.bat run --console=plain
```

---

# 👤 Demo Accounts

The project includes dummy users for testing.

| Username        | Password |
| --------------- | -------- |
| Lovepreet Singh | 12345    |
| rahul           | hhbb11   |

These are **demo credentials only**.

You can also create a new account using the **Sign Up** option.

---

# 🎫 How to Use

## 1. Guest Menu

When you start the application without an active session:

```text
1. Sign up
2. Login
3. Search Trains
4. Exit the App
```

Guests can search trains but must login before booking.

---

## 2. Login

Select:

```text
2. Login
```

Enter your username and password.

After successful login, the application remembers your session.

---

## 3. Search Trains

Select:

```text
Search Trains
```

Enter:

```text
Source
Destination
```

The application displays matching trains and their station timings.

Logged-in users can select a train for booking.

---

## 4. Book a Seat

After selecting a train:

```text
Book a Seat
```

The application displays the seat layout.

For example:

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

Select the row and column of the desired seat and enter the travel date.

A ticket is then created and stored in the user's bookings.

---

## 5. View Bookings

Select:

```text
Fetch My Bookings
```

The application displays ticket information including:

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

Enter the Ticket ID of the booking you want to cancel.

---

## 7. Logout

Select:

```text
Logout
```

The current session is removed.

The next time the application starts, it will show the guest menu.

---

# 💾 Local Database

This project intentionally uses JSON files instead of an external database.

The files are located at:

```text
app/src/main/java/ticket/booking/localDB/
```

### `users.json`

Stores registered users and their bookings.

### `trains.json`

Stores train information and seat availability.

### `session.json`

Stores the currently logged-in user's ID so the application can restore the session when restarted.

---

# 🔐 Security Note

This is an educational console project.

The project uses **jBCrypt for password hashing**, but the current dummy database also contains demonstration password fields for convenience.

Do not use the included authentication/database design for a production application without additional security improvements.

Never commit real passwords, API keys, database credentials, or other secrets to GitHub.

---

# 🧪 Testing

Run the test suite using:

```bash
gradlew.bat test
```

To build the complete project:

```bash
gradlew.bat build
```

---

# 📌 Project Purpose

This project was created to practice:

* Java OOP
* Collections
* Exception handling
* File handling
* JSON serialization/deserialization
* Password hashing
* Service-layer design
* Session management
* Gradle
* Unit testing
* Git/GitHub

It is designed as a learning project that can be progressively improved toward a more production-oriented architecture.

---

# 🚀 Quick Start — No Technical Knowledge Required

**Just downloaded this project and don't know what to do?**

### 👉 Windows users: Start here

1. Download or clone this repository.
2. Open the project folder.
3. **Double-click `setup.bat`**.
4. Follow the instructions shown on the screen.

That's it. 🎉

`setup.bat` automatically:

* Checks whether Java is installed.
* Checks the Java version.
* Downloads the required Gradle dependencies.
* Builds the project.
* Starts the Ticket Booking System.

### ⚠️ Before running `setup.bat`

You need **Java 22** installed on your computer.

You can check this by opening Command Prompt and running:

```text
java -version
```

If Java is not installed, install Java 22 first and then double-click `setup.bat`.

> **You do NOT need to install Gradle separately.**
> The project already includes the Gradle Wrapper.

---

# 🎯 What Does This Application Do?

The Ticket Booking System allows users to:

* Create an account
* Login
* Search trains
* View available seats
* Book a seat
* View their bookings
* Cancel bookings
* Logout
* Exit the application

The project uses local JSON files as a simple database, so no MySQL, PostgreSQL, or other database server is required.

---

# 🛠️ Technology Stack

* **Java 22**
* **Gradle 9.8**
* **Jackson** — JSON data handling
* **jBCrypt** — password hashing
* **Lombok**
* **JUnit** — testing

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
│   │   └── test/
│   │
│   └── build.gradle.kts
│
├── gradle/
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
├── setup.bat          ← ⭐ DOUBLE-CLICK THIS
└── README.md
```

---

# 👤 Demo Accounts

The project includes dummy users for testing.

| Username        | Password |
| --------------- | -------- |
| Babar           | babar@123|

You can also create a new account using **Sign Up**.

---

# 🎫 How to Use the Application

After running `setup.bat`, the application will open in the terminal.

## Guest Menu

```text
1. Sign up
2. Login
3. Search Trains
4. Exit the App
```

Guests can search trains but must login before booking.

## Logged-in Menu

```text
1. Fetch My Bookings
2. Search Trains
3. Book a Seat
4. Cancel My Booking
5. Logout
6. Exit the App
```

---

# 💾 Local Database

The application uses JSON files instead of an external database:

```text
app/src/main/java/ticket/booking/localDB/
```

* `users.json` — users and their bookings
* `trains.json` — trains and seat availability
* `session.json` — current login session

---

# 🧪 For Developers

If you are a developer and want to run the project manually:

### Build

```bash
gradlew.bat build
```

### Run

```bash
gradlew.bat run --console=plain
```

### Run tests

```bash
gradlew.bat test
```

---

# 🔐 Security Note

This is an educational console project.

The included JSON database contains dummy credentials for demonstration purposes. Do not put real passwords, API keys, database credentials, or other secrets into the repository.

The authentication system should be further hardened before being used in a production environment.

---

# 👨‍💻 Author

**Sk Babar Ali**

Computer Science Engineering Student
