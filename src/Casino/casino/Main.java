package Casino.casino;

import Casino.casino.casino.Casino;
import Casino.casino.games.BaseGame;
import Casino.casino.games.BlackJack;
import Casino.casino.games.Roulette;
import Casino.casino.user.User;

import java.util.Scanner;

/**
 * The Main class serves as the entry point for the casino application.
 * It allows users to log in, displays available games, and starts gameplay.
 */
public class Main {
    /**
     * Array to store the registered users of the casino application.
     */
    private static User[] users = new User[0]; // TODO Optimize user creation

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Game is starting ... ");

        System.out.println("Enter your username");
        String name = sc.next();
        User user = getOrCreateUser(name);

        System.out.println("You are logged in as a " + user.getName() + " with balance " + user.getBalance());

        Casino casino = new Casino(user);
//        casino.displayAvailableGames();
    }

    /**
     * Retrieves an existing user by username or creates a new user if not found.
     *
     * @param username The username of the user to retrieve or create.
     * @return The User object corresponding to the username.
     */
    private static User getOrCreateUser(String username) {
        for (User user : users) {
            if (user.getName().equals(username))
                return user;
        }
        User[] newUsers = new User[users.length + 1];
        for (int i = 0; i < users.length; i++) {
            newUsers[i] = users[i];
        }
        User newUser = new User(username, 100.0);
        newUsers[users.length] = newUser;
        return newUser;
    }
}