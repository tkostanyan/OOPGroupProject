package Casino.casino.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJack extends BaseGame {


    public static class Card {
        private String suit;
        private String rank;
        private int value;

        public Card(String suit, String rank, int value) {
            this.suit = suit;
            this.rank = rank;
            this.value = value;
        }


        public String getRank() {
            return rank;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }

        public String getImagePath() {
            return "cards/" + rank.toLowerCase() + "_of_" + suit.toLowerCase() + ".png";
        }
    }


    public static class Deck {
        private List<Card> cards;

        public Deck() {
            cards = new ArrayList<>();
            String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
            String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
            int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11}; // Ace can be 1 or 11 but starts as 11

            for (String suit : suits) {
                for (int i = 0; i < ranks.length; i++) {
                    cards.add(new Card(suit, ranks[i], values[i]));
                }
            }

            Collections.shuffle(cards);
        }

        public Card dealCard() {
            return cards.remove(0);
        }
    }

    public static class Player {
        protected List<Card> hand;
        private int balance;
        private int bet;

        public Player(int balance) {
            this.balance = balance;
            hand = new ArrayList<>();
        }


        public void addCard(Card card) {
            hand.add(card);
        }

        public int getHandValue() {
            int handValue = 0;
            int aceCount = 0;
            for (Card card : hand) {
                if (card.getRank().equals("Ace")) {
                    aceCount++;
                }
                handValue += card.getValue();
            }

            while (handValue > 21 && aceCount > 0) {
                handValue -= 10; // Adjusting Ace from 11 to 1
                aceCount--;
            }

            return handValue;
        }

        public void clearHand() {
            hand.clear();
        }

        public Card getCard(int index) {
            if (index < hand.size()) {
                return hand.get(index);
            }
            return null;
        }

        public List<Card> getHand() {
            return new ArrayList<>(hand); // Returns a copy of the hand list
        }

        public void displayHand() {
            for (Card card : hand) {
                System.out.println(card);
            }
            System.out.println("Total Value: " + getHandValue());
        }
    }

    public static class Dealer extends Player {
        public Dealer() {
            super(0); // Casino.casino.games.BlackJack.Dealer doesn't need a balance
        }

        @Override
        public void displayHand() {
            // Display only the first card and hide the second card initially
            if (!hand.isEmpty()) {
                System.out.println(getCard(0) + " and [hidden]");
            }
        }

    }

    public BlackJack() {
        super("Blackjack");
    }
}