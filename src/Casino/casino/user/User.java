package Casino.casino.user;


/**
 * The User class represents a user of the casino application.
 */
public class User {
    /**
     * The name of the user.
     */
    public String name;
    /**
     * The balance of the user.
     */
    private double balance;

    /**
     * Constructs a User instance with the specified name and an initial balance of 100.
     *
     * @param name The name of the user.
     */
    public User(String name) {
        this.balance = 100.;
        this.name = name;
    }

    /**
     * Gets the balance of the user.
     *
     * @return The balance of the user.
     */
    public double getBalance() {
        return balance;
    }


}
