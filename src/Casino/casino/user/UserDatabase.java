package Casino.casino.user;

import Casino.casino.user.exceptions.IllegalPasswordException;
import Casino.casino.user.exceptions.IllegalUserException;
import Casino.casino.user.exceptions.IllegalUsernameException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The UserDatabase class manages the storage and retrieval of user information.
 */
public class UserDatabase {
    /**
     * The relative path of the database file.
     */
    private static final String DATABASE_RELATIVE_PATH = "src/Casino/casino/user/database.txt";

    /**
     * The list of users stored in the database.
     */
    private static ArrayList<User> users;

    /**
     * Constructs a UserDatabase instance and loads user data from the database file.
     */
    public UserDatabase() {
        load();
    }

    /**
     * Loads user data from the database file into the users list.
     */
    private User find(String username) {
        try {
            Scanner inputStream = new Scanner(new FileInputStream(DATABASE_RELATIVE_PATH));
            int usersCount = inputStream.nextInt();
//            users = new ArrayList<>(usersCount);
            inputStream.nextLine();

            for (int i = 0; i < usersCount; i++) {
                User us = new User(inputStream.nextLine();
                if (us.getName().equals(username)){
                    return us;
                }
            }
//            Collections.sort(users);
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(0);
        } catch (IllegalUserException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Saves user data from the users list to the database file.
     */
    public void save() {
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(DATABASE_RELATIVE_PATH));
            outputStream.println(getSize());
            for (int i = 0; i < getSize(); i++)
                outputStream.println(getUser(i));
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("User not saved");
            System.exit(0);
        }
    }

    /**
     * Checks if the users list contains a specific user.
     *
     * @param user The user to check.
     * @return true if the user is found in the list, false otherwise.
     */
    private boolean contains(User user) {
        for (User u : users)
            if (u.equals(user)) return true;
        return false;
    }

    /**
     * Gets the number of users stored in the database.
     *
     * @return The number of users.
     */
    public int getSize() {
        return users.size();
    }

    /**
     * Gets the user at the specified index in the users list.
     *
     * @param i The index of the user.
     * @return The user at the specified index, or null if the index is out of bounds.
     */
    public User getUser(int i) {
        if (i >= 0 && i < getSize()) return users.get(i);
        return null;
    }

    /**
     * Authenticates a user based on the provided username and password.
     * If the user exists in the database, returns the user object.
     * If the user does not exist, creates a new user object with the provided username,
     * password, and a balance of 0, adds it to the database, and returns the new user object.
     *
     * @param username The username of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return The authenticated user object if authentication is successful
     * @throws IllegalUserException if the username or password does not meet the requirements.
     */
    public static User authenticateUser(String username, String password) throws IllegalUserException {
        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) return user;
        }
//        verifyUserCreation(username, password);
        for (User user : users) {
            if (user.getName().equals(username)) throw new IllegalUserException("Wrong password");
        }
        User newUser = new User(username, password, 0.0);
        users.add(newUser);
        Collections.sort(users);
        return newUser;
    }


}
