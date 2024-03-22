package Casino.casino;

import Casino.casino.casino.Casino;
import Casino.casino.games.BaseGame;
import Casino.casino.games.BlackJack;
import Casino.casino.games.Roulette;
import Casino.casino.user.User;

import java.util.Scanner;

public class Main {
    public static BaseGame[] games = {new BlackJack(), new Roulette()};
    private static User[] users = new User[0]; // TODO Optimize user creation

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Game is starting ... ");
        System.out.println("Enter your username");
        String name = sc.next();
        User user = getOrCreateUser(name);
        System.out.println("You are logged in as a " + user.name + " with balance " + user.getBalance());
        Casino casino = new Casino(games, user.getBalance());
        casino.displayAvailableGames();
    }

    public static User getOrCreateUser(String name) {
        for (User user : users) {
            if (user.name == name)
                return user;
        }
        User[] newUsers = new User[users.length + 1];
        for (int i = 0; i < users.length; i++) {
            newUsers[i] = users[i];
        }
        User newUser = new User(name);
        newUsers[users.length] = newUser;
        return newUser;
    }
}