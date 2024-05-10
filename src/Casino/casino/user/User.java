package Casino.casino.user;


/**
 * The User class represents a user of the casino application.
 */
public class User {
    /**
     * The name of the user.
     */
    private String name;
    /**
     * The balance of the user.
     */
    private double balance;

    /**
     * Constructs a User instance with the specified name and an initial balance.
     *
     * @param name    The name of the user.
     * @param balance The initial balance of the user.
     */
    public User(String name, double balance) {
        this.balance = balance;
        this.name = name;
    }

    public User(User original){
        this.balance = original.balance;
        this.name = original.name;
    }

    /**
     * Gets the balance of the user.
     *
     * @return The balance of the user.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the balance of the user.
     *
     * @param balance The balance to set.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static User getUser(String username, String password){
        return new User(username, 0.);
    }

}
