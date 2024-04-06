package Casino.casino.casino;

import Casino.casino.games.BaseGame;


/**
 * The Casino class represents the main casino entity where users can access and play games.
 */
public class Casino {
    private BaseGame[] games;
    private double balance;

    /**
     * Constructs a Casino instance with the specified games and balance.
     *
     * @param games   An array of BaseGame objects representing the available games in the casino.
     * @param balance The initial balance of the casino.
     */
    public Casino(BaseGame[] games, double balance) {
        this.games = games; // TODO Figure out the deepCopy part
        this.balance = balance;
    }

    /**
     * Displays the names of available games in the casino.
     */
    public void displayAvailableGames() {
        for (BaseGame game : games) {
            System.out.println(game.getName());
        }
    }

    /**
     * Plays the specified game.
     *
     * @param game The BaseGame object representing the game to be played.
     */
    public void playGame(BaseGame game) {
        game.playGame();
    }
}
