package Casino.casino.user;

import Casino.casino.user.exceptions.IllegalPasswordException;
import Casino.casino.user.exceptions.IllegalUserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
     * Constructs a UserDatabase instance and loads user data from the database file.
     */
    public UserDatabase() {
//        load();
    }

    /**
     * Loads user data from the database file into the users list.
     */
    private static User find(String username) throws IllegalUserException {
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream(DATABASE_RELATIVE_PATH));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(0);
        }

        while(inputStream.hasNextLine()) {
            User us = new User(inputStream.nextLine());
            if (us.getName().equals(username)) return us;
        }
        inputStream.close();

        return new User()
    }

    /**
     * Saves user data from the users list to the database file.
     */
    public void save(String username, String password) {
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(DATABASE_RELATIVE_PATH));
            outputStream.println(new User(username, password, 0.0));
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("User not saved");
            System.exit(0);
        }
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
        User user = find(username);
        if (user.getPassword().equals(password)) return user;
        else throw new IllegalPasswordException("Wrong password");
    }

}
