package com.project.strategies;

import com.project.models.Board;
import com.project.models.Move;

import java.util.HashMap;

public class ColoumnWinningStrategy extends WinningStrategy{

    HashMap<Integer,HashMap<Character,Integer>> coloumnWinningMap;
    @Override
    public boolean checkWinner(Move move, Board board) {

        int col = move.getCell().getCol();
        char symbol  = move.getCell().getPlayer().getSymbol();
        if(coloumnWinningMap.containsKey(col)){
            if(coloumnWinningMap.get(col).containsKey(symbol)) {
                if (coloumnWinningMap.get(col).get(symbol) == board.getSize())
                    return true;
            }
            coloumnWinningMap.get(col).put(symbol,coloumnWinningMap.get(col).getOrDefault(symbol,0) + 1);
        }
        else {
            coloumnWinningMap.put(col,new HashMap<>());
            coloumnWinningMap.get(col).put(symbol,1);
        }
        return false;
    }

    public void handleUndo(Move move, Board board) {
        int col = move.getCell().getCol();
        char symbol = move.getCell().getPlayer().getSymbol();
        coloumnWinningMap.get(col).remove(symbol);
    }


}
