 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gofish;

/**
 *
 * @Dong Nguyen
 * @Id 991756472
 * @Jiya
 * @Id 991755188
 */
public class GoFish {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Launching Go Fish Game...");

        // Create an instance of GoFishGame
        GoFishGame game = new GoFishGame("Go Fish");

        // Start the game
        game.play();

        // Declare the winner at the end
        game.declareWinner();
    }
    
}
