package Casino.casino.games;

public class BaseGame {
    public String name;

    /**
     * Plays the game.
     */
    public void playGame(){
        System.out.println("Playing the game " + this.name);
    }
}
