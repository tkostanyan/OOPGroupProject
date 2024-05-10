package Casino.casino.user;

import Casino.casino.user.exceptions.IllegalUserException;

/**
 * The User class represents a user of the casino application.
 */
public class User implements Comparable<User> {
    private final String name; // The name of the user.
    private final String password; // The password of the user.
    private double balance; // The balance of the user.

    /**
     * Constructs a User instance with the specified name, password, and an initial balance.
     *
     * @param name     The name of the user.
     * @param password The password of the user.
     * @param balance  The initial balance of the user.
     */
    public User(String name, String password, double balance) throws IllegalUserException {
        UserDatabase.verifyUserCreation(name, password);
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    /**
     * Constructs a User instance by parsing the details string.
     * The details string should be in the format: "name,password,balance".
     *
     * @param details A string containing user details.
     */
    public User (String details) throws IllegalUserException {
        String[] components = details.split(",");
        UserDatabase.verifyUserCreation(components[0], components[1]);
        this.name = components[0];
        this.password = components[1];
        this.balance = Double.parseDouble(components[2]);
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
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the balance of the user.
     *
     * @param balance The balance to update.
     */
    public void updateBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Compares this user with another user based on their balance.
     *
     * @param other The user to compare with.
     * @return A negative integer if this user's balance is less than the other user's balance,
     *         zero if their balances are equal, and a positive integer if this user's balance
     *         is greater than the other user's balance.
     */
    @Override
    public int compareTo(User other) {
        return (int) ((int) this.balance - other.balance);
    }

    /**
     * Returns a string representation of the user.
     *
     * @return A string containing the name, password, and balance of the user.
     */
    @Override
    public String toString() {
        return getName() + "," + getPassword() + "," + getBalance();
    }

    /**
     * Checks if this user is equal to another object.
     *
     * @param other The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != User.class) return false;
        User otherUser = (User) other;
        return (getName().equals(otherUser.getName()) && getPassword().equals(otherUser.getPassword()));
    }
}
