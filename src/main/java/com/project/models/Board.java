package com.project.models;

import java.util.List;

public class Board {

    int size;

    public Board(int noOfPlayers) {
        this.size = noOfPlayers+1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getGrid() {
        return Grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.Grid = grid;
    }

    List<List<Cell>> Grid;
    // sample changes
}
