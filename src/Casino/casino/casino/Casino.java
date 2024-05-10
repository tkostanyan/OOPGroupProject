package Casino.casino.casino;

import Casino.casino.games.BaseGame;
import Casino.casino.user.User;


/**
 * The Casino class represents the main casino entity where users can access and play games.
 */
public class Casino {
    private User user;
    /**
     * Constructs a Casino instance with the specified games and balance.
     *
     * @param user   An array of BaseGame objects representing the available games in the casino.
     */
    public Casino(User user) {
//        this.games = games; // TODO Figure out the deepCopy part
        this.user = user;
    }

    public double getBalance() {
        return user.getBalance();
    }

    public void addToBalance(double balance) {
        this.user.setBalance(user.getBalance() + balance);
    }

    public User getUser(){
        return user;
    }

}
