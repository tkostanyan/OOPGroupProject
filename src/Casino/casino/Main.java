package Casino.casino;

import Casino.casino.user.User;
import Casino.casino.user.UserDatabase;
import Casino.casino.user.exceptions.IllegalUserException;
import java.util.Scanner;
import static Casino.casino.user.UserDatabase.authenticateUser;

/**
 * The Main class serves as the entry point for the casino application.
 * It allows users to log in, displays available games, and starts gameplay.
 */
public class Main {
    public static void main(String[] args) {
        UserDatabase database = new UserDatabase();

        Scanner sc = new Scanner(System.in);
        System.out.println("Game is starting ... ");

        System.out.println("Enter Username.");
        String name = sc.next();

        System.out.println("Enter Password.");
        String password = sc.next();

        User user = null;
        try {
            user = authenticateUser(name, password);
        } catch (IllegalUserException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        System.out.println(user);

        database.save();
    }
}