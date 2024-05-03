package com.project.models;

public class Move {

    Cell cell;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void resetMove() {

        cell.setCellType(CellType.Empty);
        cell.setPlayer(null);
    }
}
