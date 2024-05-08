package Casino.casino.games;

/**
 * The Roulette class represents the Roulette game in the casino.
 */
public class Roulette extends BaseGame {
    /**
     * Constructs a Roulette instance.
     */
    public Roulette() {
        super("Roulette");
    }

    /**
     * Plays the Roulette game.
     */
    @Override
    public void playGame() {
        System.out.println("Playing the game: " + getName());
    }

}
