/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofish;
import java.util.ArrayList;


/**
 *
 * @author Dong Nguyen
 */


public class GoFishPlayer extends Player {
    public GoFishPlayer(String name) {
        super(name);
    }

    @Override
    public void play() {
       //Test demo
        System.out.println(name + " is playing their turn...");
      
    }

    public boolean hasCardOfRank(String rank) {
        for (Card card : hand) {
            if (card.getRank().equalsIgnoreCase(rank)) {
                return true;
            }
        }
        return false;
    }

    public int giveAllOfRank(String rank) {
        int count = 0;
        ArrayList<Card> toRemove = new ArrayList<>();
        for (Card card : hand) {
            if (card.getRank().equalsIgnoreCase(rank)) {
                toRemove.add(card);
                count++;
            }
        }
        hand.removeAll(toRemove);
        return count;
    }
}

