package com.project.models;

import com.project.strategies.ColoumnWinningStrategy;
import com.project.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    List<Player> players;

    public void setTitle(String title) {
        this.title = title;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Stack<Move> getMoves() {
        return moves;
    }

    public void setMoves(Stack<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategyList() {
        return winningStrategyList;
    }

    public void setWinningStrategyList(List<WinningStrategy> winningStrategyList) {
        this.winningStrategyList = winningStrategyList;
    }

    private int id;

    private String title;

    public GameState getGameState() {

        if(!moves.isEmpty() && moves.size()==board.getSize()*board.getSize())
                return GameState.Draw;
        for(int i=0;i<getWinningStrategyList().size();i++) {
            if(!winningStrategyList.get(0).checkWinner(moves.peek(),board))
                    return GameState.Inprogess;
        }
        return GameState.Success;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    GameState gameState;

    Board board;

    int nextPlayerIndex;

    Stack<Move> moves;

    List<WinningStrategy> winningStrategyList;

    public Game(int nextPlayerIndex, String title, int noOfPlayers) {
        this.nextPlayerIndex = nextPlayerIndex;
        this.title = title;
        this.board = new Board(noOfPlayers);
        winningStrategyList = new ArrayList<>();
        moves= new Stack<>();
        winningStrategyList.add(new ColoumnWinningStrategy());
    }
    public boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()){
            return false;
        }

        if(!board.getGrid().get(row).get(col).getCellType().equals(CellType.Empty))
            return false;
        return true;

    }
    public void makeMove(Cell cell) {
        Move move = new Move();
        move.setCell(cell);
        if(!validateMove(move)){
            System.out.println("Invalid Move! Please try again");
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cellToChange =  board.getGrid().get(row).get(col);
        cellToChange.setCellType(CellType.Filled);

        move.setCell(cellToChange);
//        Move finalMoveObject = new Move(cellToChange , currentPlayer);
        moves.add(move);

        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();

        if(checkWinner(board , move)){
            gameState = GameState.Success;
           Player winner = cell.getPlayer();
        } else if (moves.size() == board.getSize() * board.getSize()){
            gameState = GameState.Draw;
        }

    }

    public boolean checkWinner(Board board , Move move){
        for(WinningStrategy winningStrategy : winningStrategyList){
            if(winningStrategy.checkWinner(move, board)){
                return true;
            }
        }
        return false;
    }

    public void undo(){
        // whatever you did while making the move, you need to reverse it
        if(moves.isEmpty()){
            System.out.println("Nothing to Undo!");
            return;
        }
        Move lastMove = moves.get(moves.size() - 1);
        moves.remove(moves.size() - 1);

        lastMove.getCell().setCellType(CellType.Empty);
        lastMove.getCell().setPlayer(null);

        nextPlayerIndex -= 1;
        nextPlayerIndex = (nextPlayerIndex + players.size()) % players.size();

    }
}
