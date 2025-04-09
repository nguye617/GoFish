/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofish;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Dong
 * @author Jiya
 */
public class GoFishGame extends Game {
    
    private GroupOfCards deck;
    private ArrayList<GoFishPlayer> playersList;
    private ArrayList<String> books;
    private final int MAX_ROUNDS = 4;

    public GoFishGame(String gameName) {
        super(gameName);
        this.playersList = new ArrayList<>();
        this.books = new ArrayList<>();
        this.deck = new GroupOfCards(52);
    }

    @Override
    public void play() {
        Scanner k = new Scanner(System.in);

        // 1. Setup players
        System.out.print("Enter number of players (must be 2-4): ");
        int numPlayers = Integer.parseInt(k.nextLine());

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = k.nextLine();
            playersList.add(new GoFishPlayer(name));
        }

        // 2. Initialize and shuffle deck
        deck.initializeStandardDeck();
        deck.shuffle();

        // 3. Deal 5 cards to each player
        for (GoFishPlayer player : playersList) {
            for (int i = 0; i < 5; i++) {
                player.addCard(deck.dealCard());
            }
        }

        // 4. Game Loop – 4 rounds
        for (int round = 1; round <= MAX_ROUNDS; round++) {
            System.out.println("\n--- Round " + round + " ---");

            for (GoFishPlayer currentPlayer : playersList) {
                currentPlayer.play();

                System.out.println(currentPlayer.getName() + ", enter a rank to ask for: ");
                String rankAsked = k.nextLine();

                boolean found = false;
                for (GoFishPlayer otherPlayer : playersList) {
                    if (otherPlayer != currentPlayer && otherPlayer.hasCardOfRank(rankAsked)) {
                        int cardsGiven = otherPlayer.giveAllOfRank(rankAsked);
                        for (int i = 0; i < cardsGiven; i++) {
                            currentPlayer.addCard(new Card(rankAsked, "Unknown")); // simplified
                        }
                        found = true;
                        System.out.println(otherPlayer.getName() + " gave " + cardsGiven + " card(s) of " + rankAsked);
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Go Fish!");
                    Card drawn = deck.dealCard();
                    if (drawn != null) {
                        currentPlayer.addCard(drawn);
                        System.out.println(currentPlayer.getName() + " drew: " + drawn);
                    }
                }

                // Check for books
                checkAndStoreBooks(currentPlayer);
            }
        }

        k.close();
    }

    private void checkAndStoreBooks(GoFishPlayer player) {
        ArrayList<String> ranksFound = new ArrayList<>();
        for (Card card : player.getHand()) {
            int count = 0;
            for (Card c : player.getHand()) {
                if (card.getRank().equals(c.getRank())) {
                    count++;
                }
            }

            if (count >= 4 && !ranksFound.contains(card.getRank())) {
                ranksFound.add(card.getRank());
                books.add(player.getName() + " collected a book of " + card.getRank());
                player.getHand().removeIf(c -> c.getRank().equals(card.getRank()));
            }
        }

        for (String book : ranksFound) {
            System.out.println(player.getName() + " completed a book of " + book);
        }
    }

    @Override
    public void declareWinner() {
        System.out.println("\n--- Game Over ---");
        int maxBooks = 0;
        String winner = "No one";

        // Count books per player
        for (GoFishPlayer player : playersList) {
            int playerBooks = (int) books.stream().filter(b -> b.contains(player.getName())).count();
            System.out.println(player.getName() + " has " + playerBooks + " book(s)");
            if (playerBooks > maxBooks) {
                maxBooks = playerBooks;
                winner = player.getName();
            }
        }

        System.out.println("🏆 Winner: " + winner + " with " + maxBooks + " book(s)!");
    }
}
