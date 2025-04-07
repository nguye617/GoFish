/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofish;
import java.util.List;

/**
 *
 * @author Dong
 * @author Jiya
 */
public abstract class Game {
    protected String gameName;
    protected List<Player> players;

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public abstract void play();

    public abstract void declareWinner();
}
