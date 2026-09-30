package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.service.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static Map<Integer, Action> getGuestMenu() {
        System.out.println("Choose option");
        System.out.println("1. Sign up");
        System.out.println("2. Login");
        System.out.println("3. Search Trains");
        System.out.println("4. Exit the App");
        System.out.println("[Please login to book/view/cancel train tickets]");

        return Map.of(
                1, Action.SIGN_UP,
                2, Action.LOGIN,
                3, Action.SEARCH_TRAIN,
                4, Action.EXIT);
    }

    public static Map<Integer, Action> getLoggedInMenu() {
        System.out.println("Choose option");
        System.out.println("1. Fetch My Bookings");
        System.out.println("2. Search Trains");
        System.out.println("3. Book a Seat");
        System.out.println("4. Cancel My Booking");
        System.out.println("5. Logout");
        System.out.println("6. Exit the App");

        return Map.of(
                1, Action.FETCH_BOOKINGS,
                2, Action.SEARCH_TRAIN,
                3, Action.BOOK_SEAT,
                4, Action.CANCEL_BOOKING,
                5, Action.LOGOUT,
                6, Action.EXIT);
    }

    public static void main(String[] args) {
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        User currentUser = null;
        UserBookingService userBookingService;
        try {
            userBookingService = new UserBookingService();
            currentUser = userBookingService.loadSession();
            if (currentUser != null) {
                userBookingService.setUser(currentUser);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        boolean exit = false;
        Train trainSelectedForBooking = null;
        String selectedSource = null;
        String selectedDestination = null;
        String selectedDateOfTravel = null;
        while (!exit) {
            Map<Integer, Action> menu;

            if (currentUser == null) {
                menu = getGuestMenu();
            } else {
                menu = getLoggedInMenu();
            }

            System.out.print("Enter your choice : ");
            option = scanner.nextInt();

            Action action = menu.get(option);

            if (action == null) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }
            switch (action) {
                case LOGIN:
                    System.out.print("Enter the username to login : ");
                    String nameToLogin = scanner.next();
                    System.out.print("Enter the password to login : ");
                    String passwordToLogin = scanner.next();
                    Optional<User> loggedInUser = userBookingService.loginUser(nameToLogin, passwordToLogin);

                    if (loggedInUser.isPresent()) {
                        currentUser = loggedInUser.get();

                        try {
                            userBookingService.setUser(currentUser);
                            userBookingService.saveSession(currentUser);
                            System.out.println("Login successful!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;

                case SIGN_UP:
                    System.out.print("Enter the username to signup : ");
                    String nameToSignUp = scanner.next();

                    System.out.print("Enter the password to signup : ");
                    String passwordToSignUp = scanner.next();

                    User userToSignup = new User(
                            nameToSignUp,
                            passwordToSignUp,
                            UserServiceUtil.hashPassword(passwordToSignUp),
                            new ArrayList<>(),
                            UUID.randomUUID().toString());

                    if (userBookingService.signUp(userToSignup)) {
                        System.out.println("Signup successful!\nLogging you in...");

                        currentUser = userToSignup;
                        userBookingService.setUser(currentUser);

                        try {
                            userBookingService.saveSession(currentUser);
                            System.out.println("Login successful!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        System.out.println("Signup failed.");
                    }
                    break;

                case SEARCH_TRAIN:
                    System.out.print("Enter the Source station : ");
                    selectedSource = scanner.next();

                    System.out.print("Enter the Destination station : ");
                    selectedDestination = scanner.next();

                    List<Train> trains = userBookingService.getTrains(selectedSource, selectedDestination);

                    if (trains.isEmpty()) {
                        System.out.println("No trains found for this route.");
                        break;
                    }

                    int index = 1;

                    for (Train t : trains) {
                        System.out.println(index + ". Train id : " + t.getTrainId());

                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.println(
                                    "Station " + entry.getKey() +
                                            " time: " + entry.getValue());
                        }

                        index++;
                    }
                    if (currentUser == null) {
                        break;
                    }
                    int trainChoice = 0;
                    if (index - 1 == 1) {
                        System.out.print("Select this train ? (1 for yes, 0 for no) : ");
                        int ch = scanner.nextInt();
                        if (ch == 0) {
                            System.out.println("No train selected.");
                            break;
                        } else
                            trainChoice = 1;
                    } else {
                        System.out.print("Select any train to book tickets next time? (1 to " + (index - 1) + " : ");
                        trainChoice = scanner.nextInt();
                    }

                    if (trainChoice < 1 || trainChoice > trains.size()) {
                        System.out.println("Invalid train selection.");
                        break;
                    }

                    trainSelectedForBooking = trains.get(trainChoice - 1);
                    break;

                case FETCH_BOOKINGS:
                    System.out.println("Fetching your bookings...");
                    userBookingService.fetchBookings();
                    break;
                case BOOK_SEAT:
                    if (trainSelectedForBooking == null) {
                        System.out.println("Please search and select a train first.");
                        break;
                    }
                    
                    System.out.print("Enter the date of travel (YYYY-MM-DD): ");
                    selectedDateOfTravel = scanner.next();

                    System.out.println("Select a seat out of these seats");

                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);

                    for (List<Integer> row : seats) {
                        for (Integer val : row) {
                            System.out.print(val + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Select the seat by typing the row and column");

                    System.out.print("Enter the row : ");
                    int row = scanner.nextInt();

                    System.out.print("Enter the column : ");
                    int col = scanner.nextInt();

                    System.out.println("Booking your seat....");

                    Boolean booked = userBookingService.bookTrainSeat(
                            trainSelectedForBooking,
                            row - 1,
                            col - 1,
                            selectedSource,
                            selectedDestination,
                            selectedDateOfTravel);

                    if (booked) {
                        System.out.println("Booked! Enjoy your journey");
                    } else {
                        System.out.println("Can't book this seat");
                    }
                    break;

                case CANCEL_BOOKING:
                    System.out.print("Enter the ticket ID to cancel : ");
                    String ticketId = scanner.next();

                    Boolean cancelled = userBookingService.cancelBooking(ticketId);

                    if (cancelled) {
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Booking not found or could not be cancelled.");
                    }

                    break;
                case LOGOUT:
                    try {
                        userBookingService.clearSession();
                        currentUser = null;
                        userBookingService.setUser(null);
                        trainSelectedForBooking = null;
                        System.out.println("Logged out successfully.");
                    } catch (IOException ex) {
                        System.out.println("Unable to logout.");
                        ex.printStackTrace();
                    }

                    break;
                case EXIT:
                    exit = true;
                    System.out.println("Thank you for using Train Booking System.");
                    break;
                default:
                    System.out.println("Default choice");
            }
        }
    }
}
