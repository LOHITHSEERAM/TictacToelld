package com.project.strategies;

import com.project.models.Board;
import com.project.models.Move;

public abstract class WinningStrategy {

     public abstract boolean checkWinner(Move move, Board board);

}
