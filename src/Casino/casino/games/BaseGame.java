package Casino.casino.games;

/**
 * The BaseGame class represents a basic game in the casino.
 */
public abstract class BaseGame {
    private String name;

    /**
     * Constructs a BaseGame instance with the specified name.
     *
     * @param name The name of the game.
     */
    public BaseGame(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the game.
     *
     * @return The name of the game.
     */
    public String getName() {
        return name;
    }
}
