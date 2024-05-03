package com.project.controllers;

import com.project.models.*;

import java.util.List;

public class GameController {

    Game game;


    public GameController(int noOfPlayers) {
        this.game = new Game(0,"TicTacToe",noOfPlayers);
    }

    public void makeMove(Cell cell) {
        game.makeMove(cell);
    }

    public void displayBoard() {

    }

    public GameState checkState() {
          return game.getGameState();
    }

    public int getPlayerIndex() {
        return game.getNextPlayerIndex();
    }
}
