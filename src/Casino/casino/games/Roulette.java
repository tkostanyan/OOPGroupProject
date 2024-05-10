package Casino.casino.games;

import Casino.casino.casino.Casino;
import Casino.casino.user.User;


import javax.swing.*;
import java.util.*;

/**
 * The Roulette class represents the Roulette game in the casino.
 */
public class Roulette extends BaseGame {

    private int totalBetsPlaced = 0;
    private double totalWinnings = 0;
    private int totalSpins = 0;

    private Casino casino;
    private Map<String, Integer> numberFrequency = new HashMap<>();
    private Map<String, Integer> colorFrequency = new HashMap<>();
    private List<Player> players;
    private List<Bet> currentBets;
    private JLabel balanceLabel;


    public static class Bet {

        private double bet;
        private String type;
        private int multiplier;
        private String target;


        public Bet(double bet, String type, String target) {
            this.bet = bet;
            this.type = type;
            this.target = target;
        }

        public double getAmount() {
            return bet;
        }

        public String getType() {
            return type;
        }

        public double calculateWin(double amount, boolean isWinner) {
            if (!isWinner) {
                return 0; // No winnings
            }
            switch (type) {
                case "number":
                    multiplier = 35; // Win 35 times the bet
                    break;
                case "color":
                case "even":
                case "odd":
                    multiplier = 2; // Win double the bet
                    break;
                default:
                    multiplier = 0; // No winnings if an unsupported bet type
                    return 0;
            }
            return amount * multiplier;
        }

        public boolean checkOutcome(String spinResult) {
            try {
                int resultNumber = Integer.parseInt(spinResult.trim());
                switch (type) {
                    case "number" -> {
                        int targetNumber = Integer.parseInt(target);
                        return resultNumber == targetNumber;
                    }
                    case "color" -> {
                        boolean isRed = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36).contains(resultNumber);
                        return (target.equals("red") && isRed) || (target.equals("black") && !isRed);
                    }
                    case "even" -> {
                        return resultNumber % 2 == 0;
                    }
                    case "odd" -> {
                        return resultNumber % 2 != 0;
                    }
                    default -> throw new IllegalArgumentException("Unsupported bet type: " + type);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid spin result format: " + spinResult);
                return false;
            }
        }

    }


    public class Player {
        private User user;
        private List<Bet> currentBets;

        public Player(User user) {
            this.user = user;
            this.currentBets = new ArrayList<>();
        }

        public String getName() {
            return user.getName();
        }

        public double getBalance() {
            return user.getBalance();
        }

        public List<Bet> getCurrentBets() {
            return currentBets;
        }

        public void addBet(Bet bet) {
            if (getBalance() >= bet.getAmount()) {
                currentBets.add(bet);
                user.setBalance(user.getBalance() - bet.getAmount());
                balanceLabel.setText("Balance: $" + user.getBalance());

                System.out.println("Bet placed: " + bet.getAmount() + ", New balance: " + user.getBalance());
            } else {
                throw new IllegalArgumentException("Insufficient balance to place bet");
            }
        }

        public void win(double amount) {
            user.setBalance(user.getBalance() + amount);  // Accurately add the winnings to the user's balance
            balanceLabel.setText("Balance: $" + user.getBalance());
            System.out.println("Balance after win for " + user.getName() + ": " + user.getName());
        }

        public void resetBets() {
            currentBets.clear();
        }

    }

    public Roulette(Casino casino, JLabel balanceLabel) {
        super("Roulette");
        this.balanceLabel = balanceLabel;
        this.casino = casino;
        this.players = new ArrayList<>();
        this.currentBets = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public boolean placeBet(Player player, double amount, String type) {
        if (player.getBalance() < amount) {
            JOptionPane.showMessageDialog(null, "Insufficient balance to place bet. Please add funds.");
            return false; // Exit the method if balance is insufficient
        }

        if (!isValidType(type)) {
            return false;
        }

        Bet bet;
        String target = "";

        if (type.equals("number")) {
            // In case of number bet, we need to prompt the user for the target number
            target = JOptionPane.showInputDialog(null, "Enter the number you want to bet on (0-36):");
            if (target == null || target.isEmpty() || !target.matches("\\d+") || Integer.parseInt(target) < 0 || Integer.parseInt(target) > 36) {
                JOptionPane.showMessageDialog(null, "Invalid number entered. Please enter a number between 0 and 36.");
                return false;
            }
            bet = new Bet(amount, type, target);
        } else if (type.equals("color")) {
            // In case of color bet, we'll prompt the user for red or black
            String[] options = {"Red", "Black"};
            int choice = JOptionPane.showOptionDialog(null, "Choose a color to bet on:", "Color Bet", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                target = "red";
            } else {
                target = "black";
            }
            bet = new Bet(amount, type, target.toLowerCase()); // Convert target to lowercase
        } else {
            // For even, odd, or other bets, we don't need a target
            bet = new Bet(amount, type, target);
        }

        currentBets.add(bet);
        player.addBet(bet);
        totalBetsPlaced++;

        if (!target.isEmpty()) {
            if (type.equals("number")) {
                numberFrequency.put(target, numberFrequency.getOrDefault(target, 0) + 1);
            }
        }
        return true;
    }


    private boolean isValidType(String type) {
        switch (type) {
            case "number":
            case "color":
            case "even":
            case "odd":
                return true;
            default:
                return false;
        }
    }

    public String spinRoulette() {
        int result = new Random().nextInt(37);
        return Integer.toString(result);
    }

    public void resolveBets(String spinResult) {
        for (Player player : players) {
            for (Bet bet : player.getCurrentBets()) {
                boolean outcome = bet.checkOutcome(spinResult);
                if (outcome) {
                    double winAmount = bet.calculateWin(bet.getAmount(), true);
                    totalWinnings += winAmount;
                    player.win(winAmount);  // Properly add winnings
                    System.out.println("Player " + player.getName() + " wins: " + winAmount);
                } else {
                    System.out.println("Player " + player.getName() + " does not win on bet type: " + bet.getType());
                }
            }
        }

        totalSpins++;
        updateFrequencies(spinResult); // Update frequencies after each spin
        resetRound();
    }

    private void updateFrequencies(String spinResult) {
        // Update number frequency
        numberFrequency.put(spinResult, numberFrequency.getOrDefault(spinResult, 0) + 1);

        // Determine the color based on the spin result
        boolean isRed;
        try {
            int resultNumber = Integer.parseInt(spinResult.trim());
            isRed = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36).contains(resultNumber);
        } catch (NumberFormatException e) {
            // Handle invalid spin result format
            System.err.println("Invalid spin result format: " + spinResult);
            return;
        }

        // Update color frequency based on the determined color
        String color = isRed ? "red" : "black";
        colorFrequency.put(color, colorFrequency.getOrDefault(color, 0) + 1);
    }


    public void resetRound() {
        currentBets.clear();
        for (Player player : players) {
            player.resetBets();
        }
        System.out.println("Resetting round...");
    }

    private void concludeGame() {
        System.out.println("Game over!");
        displayStatus();
    }

    public void displayStatus() {
        for (Player player : players) {
            System.out.println(player.getName() + "'s balance: " + player.getBalance());
        }
    }

    public void runGame(JTextField betAmountField, JTextField betTypeField) {
        registerPlayers();

        boolean continuePlaying = true;
        while (continuePlaying) {
            double amount = Double.parseDouble(betAmountField.getText());
            String type = betTypeField.getText().toLowerCase();

            for (Player player : players) {
                placeBet(player, amount, type);
            }

            String result = spinRoulette();
            System.out.println("Roulette wheel stopped on: " + result);
            resolveBets(result);

            continuePlaying = continuePlaying();
            resetRound();
        }
        concludeGame();
    }

    private boolean continuePlaying() {
        int choice = JOptionPane.showConfirmDialog(null, "Continue playing?", "Continue", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    private void registerPlayers() {
        System.out.println("Registering players...");
        players.add(new Player(casino.getUser()));
    }


    public int getTotalBetsPlaced() {
        return totalBetsPlaced;
    }

    public double getTotalWinnings() {
        return totalWinnings;
    }

    public int getTotalSpins() {
        return totalSpins;
    }

    public Map<String, Integer> getNumberFrequency() {
        return numberFrequency;
    }

    public Map<String, Integer> getColorFrequency() {
        return colorFrequency;
    }

}
