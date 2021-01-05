package net.comexis.kata.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.PlayerType;

@Getter
@Setter
public class Cell {

    //The cell number (from 1 to 9)
    private int number;

    //The occupying player
    private PlayerType playerType;

    public Cell(){
        super();
    }

    public Cell(int number, PlayerType playerType){
        this();
        this.setNumber(number);
        this.setPlayerType(playerType);
    }

    public boolean isEmpty() {
        return this.playerType == null;
    }

}
