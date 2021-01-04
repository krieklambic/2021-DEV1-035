package net.comexis.kata.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    Cell[] cells;

    public Board(){
        this.setCells(new Cell[9]);
    }

}
