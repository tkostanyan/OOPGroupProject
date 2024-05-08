package Casino.casino.games;

/**
 * The BlackJack class represents the Blackjack game in the casino.
 */
public class BlackJack extends BaseGame {
    /**
     * Constructs a BlackJack instance.
     */
    public BlackJack() {
        super("BlackJack");
    }

    /**
     * Plays the BlackJack game.
     */
    @Override
    public void playGame() {
        System.out.println("Playing the game: " + getName());
    }
}
