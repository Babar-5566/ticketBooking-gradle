package ticket.booking.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UserBookingService {

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<User> userList;

    private User user;

    private final String USER_FILE_PATH = "src/main/java/ticket/booking/localDB/users.json";

    private static final String SESSION_FILE_PATH = "src/main/java/ticket/booking/localDB/session.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void loadUserListFromFile() throws IOException {
        userList = objectMapper.readValue(new File(USER_FILE_PATH), new TypeReference<List<User>>() {
        });
    }

    public Optional<User> loginUser(String username, String password) {
        return userList.stream()
                .filter(user1 -> user1.getName().equals(username)
                        && UserServiceUtil.checkPassword(
                                password,
                                user1.getHashedPassword()))
                .findFirst();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_FILE_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings() {
        user.printTickets();
    }

    // todo: Complete this function
    public Boolean cancelBooking(String ticketId) {
        if (ticketId == null || ticketId.isEmpty()) {
            return Boolean.FALSE;
        }

        boolean removed = user.getTicketsBooked()
                .removeIf(ticket -> ticket.getTicketId().equals(ticketId));

        if (removed) {
            try {
                saveUserListToFile();
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        }

        System.out.println("No ticket found with ID " + ticketId);
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeat(
            Train train,
            int row,
            int seat,
            String source,
            String destination,
            String dateOfTravel) {

        try {
            List<List<Integer>> seats = train.getSeats();

            // Validate row and seat
            if (row < 0 || row >= seats.size()
                    || seat < 0 || seat >= seats.get(row).size()) {
                return false;
            }

            // Check whether seat is already booked
            if (seats.get(row).get(seat) == 1) {
                return false;
            }

            // Mark seat as booked
            seats.get(row).set(seat, 1);
            train.setSeats(seats);

            // Create ticket
            Ticket ticket = new Ticket(
                    UUID.randomUUID().toString(),
                    user.getUserId(),
                    source,
                    destination,
                    dateOfTravel,
                    train,
                    row,
                    seat);

            // Add ticket to current user's bookings
            user.getTicketsBooked().add(ticket);

            // Persist both changes
            saveUserListToFile();

            TrainService trainService = new TrainService();
            trainService.addTrain(train);

            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void saveSession(User user) throws IOException {
        Map<String, String> session = new HashMap<>();
        session.put("userId", user.getUserId());

        objectMapper.writeValue(new File(SESSION_FILE_PATH), session);
    }

    public User loadSession() throws IOException {
        File sessionFile = new File(SESSION_FILE_PATH);

        if (!sessionFile.exists()) {
            return null;
        }

        Map<String, String> session = objectMapper.readValue(sessionFile, new TypeReference<Map<String, String>>() {
        });

        String userId = session.get("userId");

        if (userId == null) {
            return null;
        }

        return userList.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public void clearSession() throws IOException {
        objectMapper.writeValue(
                new File(SESSION_FILE_PATH),
                new HashMap<>());
    }
}
