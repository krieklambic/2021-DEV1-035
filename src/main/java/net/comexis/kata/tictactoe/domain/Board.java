package net.comexis.kata.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.PlayerType;
import net.comexis.kata.tictactoe.enums.WinType;

import java.util.Arrays;

@Getter
@Setter
public class Board {

    public static final int boardSize = 3;

    Cell[] cells;

    public Board(){
        super();
        int cellNumber = boardSize * boardSize;
        this.setCells(new Cell[cellNumber]);
        for(int i=0 ; i<cellNumber; i++){
            this.getCells()[i] = new Cell(i+1, null);
        }
    }

    public Board(Game game) {
        this();
        game.getMoves().forEach( move -> {
            this.cells[move.getCellNumber()-1].setPlayerType(move.getPlayerType());
        }) ;

    }

    /**
     * Check if a player wins the specified element (winType : ROW / COLUMNS / DIAGONAL)
     * @param playerType
     * @param winType
     * @return The winning element (Row, Column, Diagonal) number, or 0 if no win
     */
    public int getWinningElement(PlayerType playerType, WinType winType) {
        switch (winType) {
            case ROW:
                return this.getWinningRow(playerType);
            case COLUMN:
                return this.getWinningColumn(playerType);
            case DIAGONAL:
                return this.getWinningDiagonal(playerType);
            default:
                return 0;
        }
    }

    /**
     * Get the winning row for a player
     * @param playerType
     * @return The winning row number. If none, return 0
     */
    private int getWinningRow(PlayerType playerType) {
        for(int y = 0; y < boardSize; y++){
            boolean winning = true;
            for(int x=0; x < boardSize; x++){
                if(!playerType.equals(this.getCells()[(y * boardSize) + x].getPlayerType())){
                    winning = false;
                    break;
                }
            }
            if(winning) return y +1;
        }
        return 0;
    }

    /**
     * Get the winning column for a player
     * @param playerType
     * @return The winning column number. If none, return 0
     */
    private int getWinningColumn(PlayerType playerType) {
        for(int x = 0; x < boardSize; x++){
            boolean winning = true;
            for(int y=0; y < boardSize; y++){
                if(!playerType.equals(this.getCells()[(y * boardSize) + x].getPlayerType())){
                    winning = false;
                    break;
                }
            }
            if(winning) return x + 1;
        }
        return 0;
    }

    /**
     * Get the winning diagonal for a player
     * @param playerType
     * @return The winning diagonal number. If none, return 0
     */
    private int getWinningDiagonal(PlayerType playerType) {
        for(int i = 0; i < boardSize; i++){
            if(!playerType.equals(this.getCells()[(i * boardSize) + i].getPlayerType())){
                return 0;
            }
        }
        return 1;
    }

    /**
     * Check if the board is full (All the cells are not empty)
     * @return
     */
    public boolean isFull() {
         return Arrays.stream(this.cells).anyMatch(Cell::isNotEmpty);
    }

}
