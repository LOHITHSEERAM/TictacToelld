package com.project.strategies;

import com.project.models.Board;
import com.project.models.Move;

import java.util.HashMap;

public class RowWinningStrategy extends WinningStrategy {
    HashMap<Integer, HashMap<Character,Integer>> rowWinningMap;
    @Override
    public boolean checkWinner(Move move, Board board) {

        int row = move.getCell().getRow();
        char symbol  = move.getCell().getPlayer().getSymbol();
        if(rowWinningMap.containsKey(row)){
            if(rowWinningMap.get(row).containsKey(symbol)) {
                if (rowWinningMap.get(row).get(symbol) == board.getSize())
                    return true;
            }
            rowWinningMap.get(row).put(symbol, rowWinningMap.get(row).getOrDefault(symbol,0) + 1);
        }
        else {
            rowWinningMap.put(row,new HashMap<>());
            rowWinningMap.get(row).put(symbol,1);
        }
        return false;
    }

    public void handleUndo(Move move, Board board) {
        int row = move.getCell().getRow();
        char symbol = move.getCell().getPlayer().getSymbol();
        rowWinningMap.get(row).remove(symbol);
    }
}
