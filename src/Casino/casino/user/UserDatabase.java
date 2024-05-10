package Casino.casino.user;

import Casino.casino.user.exceptions.IllegalPasswordException;

import java.io.*;
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
     * Authenticates a user based on the provided username and password.
     * If the user exists in the database, returns the user object.
     * If the user does not exist, creates a new user object with the provided username,
     * password, and a balance of 0, adds it to the database, and returns the new user object.
     *
     * @param username The username of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return The authenticated user object if authentication is successful
     * @throws IllegalPasswordException if the username or password does not meet the requirements.
     */
    public static User authenticateUser(String username, String password) throws IllegalPasswordException {
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream(DATABASE_RELATIVE_PATH));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(0);
        }

        while (inputStream.hasNextLine()) {
            User us = new User(inputStream.nextLine());
            if (us.getName().equals(username) && us.getPassword().equals(password)) return us;
            System.out.println(us.getName() + " " + us.getPassword());
            if (us.getName().equals(username) && !us.getPassword().equals(password))
                throw new IllegalPasswordException("Wrong password");
        }
        inputStream.close();

        User newUser = new User(username, password, 0.);
        newUser.save();
        return newUser;
    }

    public static int findUser(String username) {
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream(DATABASE_RELATIVE_PATH));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(0);
        }
        int i = 1;
        while (inputStream.hasNextLine()) {
            User us = new User(inputStream.nextLine());
            if (us.getName().equals(username)) return i;
        }
        inputStream.close();
        return -1;
    }

    /**
     * Saves user data from the users list to the database file.
     */
    public static void save(User user) {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream(DATABASE_RELATIVE_PATH, true));
        } catch (FileNotFoundException e) {
            System.err.println("User not saved");
            System.exit(0);
        }

        int userRow = findUser(user.getName());

        if (userRow != -1) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_RELATIVE_PATH))) {
                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    if (lineNumber == userRow) {
                        content.append(user).append(System.lineSeparator());
                    } else {
                        content.append(line).append(System.lineSeparator());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception properly in your application
            }

            try {
                outputStream = new PrintWriter(new FileOutputStream(DATABASE_RELATIVE_PATH));
            } catch (FileNotFoundException e) {
                System.err.println("User not saved");
                System.exit(0);
            }
            outputStream.print(content);
        } else {
            outputStream.println(user);

        }
        outputStream.close();
    }


}
