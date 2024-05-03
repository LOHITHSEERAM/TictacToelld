package com.project;

import com.project.controllers.GameController;
import com.project.models.Cell;
import com.project.models.GameState;
import com.project.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        //create game
        //intialize number of players
        //intialize winning strategies
        //change game status
        //make move
        //while(checkWinner(move, board))
        // make move
       Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to my game!");
        System.out.println("Enter number of players: ");
        int numberOfPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<>();
        for(int i=0;i<numberOfPlayers;i++){
            System.out.println("Player "+(i+1)+"Enter a symbol"+"+: ");
            Character symbol = scanner.next().charAt(0);
            Player player = new Player(i+1,symbol);
            players.add(player);
        }

        GameController gameController = new GameController(numberOfPlayers);
        int p=0;
        while(gameController.checkState()== GameState.Inprogess) {
            p = gameController.getPlayerIndex();
            System.out.println(p+"Make move");
            Scanner row = new Scanner(System.in);
            Scanner col = new Scanner(System.in);
            gameController.makeMove(new Cell(row.nextInt(),col.nextInt(),players.get(p)));
        }

        if(gameController.checkState().equals(GameState.Success)){
            System.out.println("****** GAME OVER *******");
            System.out.println("Winner is " + players.get(p).getId());
        } else {
            System.out.println(" ***** GAME OVER ******");
            System.out.println("Game ends in a DRAW");
        }
    }
}