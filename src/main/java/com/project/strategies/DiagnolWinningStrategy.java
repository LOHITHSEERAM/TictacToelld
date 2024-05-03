package com.project.strategies;

import com.project.models.Board;
import com.project.models.Move;

public class DiagnolWinningStrategy extends WinningStrategy{

    @Override
    public boolean checkWinner(Move move, Board board) {
        return false;
    }
}
