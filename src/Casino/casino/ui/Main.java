package Casino.casino.ui;

import Casino.casino.casino.Casino;
import Casino.casino.games.Roulette;
import Casino.casino.user.User;
import Casino.casino.user.UserDatabase;
import Casino.casino.user.exceptions.IllegalUserException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPanel gamesPanel;
    private Casino casino;
    private BlackjackGUI blackjackUI;

    private JLabel balanceLabel;

    private RouletteGUI rouletteGUI;

    public class LoginPanel extends JPanel {
        private JTextField usernameField;
        private JPasswordField passwordField;

        public LoginPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel usernameLabel = new JLabel("Username:");
            usernameField = new JTextField(20); // Adjust size as needed
            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField(20); // Adjust size as needed

            add(usernameLabel);
            add(usernameField);
            add(passwordLabel);
            add(passwordField);
        }

        public String getUsername() {
            return usernameField.getText();
        }

        public String getPassword() {
            return new String(passwordField.getPassword());
        }
    }

    public Main() {
        setTitle("Casino");
        setSize(500, 400); // Increased size for a bigger panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginPanel loginPanel = new LoginPanel();
        int result = JOptionPane.showConfirmDialog(null, loginPanel, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = loginPanel.getUsername();
            String password = loginPanel.getPassword();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username or password cannot be empty. Exiting...");
                System.exit(0);
            } else {
                // Continue with authentication logic here
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login canceled. Exiting...");
            System.exit(0);
        }


//        if (username == null || username.trim().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Username cannot be empty. Exiting...");
//            System.exit(0);
//        }
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JPasswordField();

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        User user = null;
        try {
            user = UserDatabase.authenticateUser(usernameField.getText(), passwordField.getText());
        } catch (IllegalUserException e) {
            JOptionPane.showMessageDialog(null, "Login canceled. Exiting...");
        }

        casino = new Casino(user);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + usernameField.getText() + "!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel to hold balance display and increase balance button
        JPanel balancePanel = new JPanel(new BorderLayout());

        // Balance display
        this.balanceLabel = new JLabel("Balance: $" + casino.getBalance());
        this.balanceLabel.setHorizontalAlignment(JLabel.LEFT);
        this.balanceLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        this.balanceLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add some padding
        balancePanel.add(this.balanceLabel, BorderLayout.NORTH);

        // Button to increase balance
        JButton increaseBalanceButton = new JButton("Increase Balance");
        increaseBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the amount to increase the balance
                String input = JOptionPane.showInputDialog("Enter the amount to increase the balance:");
                if (input != null && !input.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(input);
                        casino.addToBalance(amount);
                        balanceLabel.setText("Balance: $" + casino.getBalance()); // Update balance display
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                    }
                }
            }
        });
        balancePanel.add(increaseBalanceButton, BorderLayout.SOUTH);

        add(balancePanel, BorderLayout.WEST);

        // Label to prompt user to choose a game
        JLabel chooseGameLabel = new JLabel("Choose a game:");
        chooseGameLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseGameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Larger and bold font
        chooseGameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Add some padding
        add(chooseGameLabel, BorderLayout.CENTER);

        // Create a panel to hold the list of games
        gamesPanel = new JPanel();
        gamesPanel.setLayout(new BoxLayout(gamesPanel, BoxLayout.Y_AXIS));
        addGamesToList();

        // Customize the panel's appearance
        gamesPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        gamesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        JScrollPane scrollPane = new JScrollPane(gamesPanel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addGamesToList() {
        addGameButton("Blackjack", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBlackjackGame();
            }
        });
        addGameButton("Roulette", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRouletteGame();
            }
        });
    }

    private void addGameButton(String gameName, ActionListener actionListener) {
        JButton button = new JButton(gameName);
        button.addActionListener(actionListener);
        gamesPanel.add(button);
    }

    private void startBlackjackGame() {
        // Start the Blackjack game
        blackjackUI = new BlackjackGUI(); // Create an instance of BlackjackUI

    }

    public void startRouletteGame() {

        SwingUtilities.invokeLater(() -> {
            rouletteGUI = new RouletteGUI(casino, this.balanceLabel);
            Roulette game = new Roulette(casino, this.balanceLabel);
            game.runGame(rouletteGUI.getBetAmountField(), rouletteGUI.getBetTypeField());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
