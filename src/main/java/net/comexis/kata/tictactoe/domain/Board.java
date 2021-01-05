package net.comexis.kata.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Board {

    public static final int cellNumber = 9;

    Cell[] cells;

    public Board(){
        super();
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
}
