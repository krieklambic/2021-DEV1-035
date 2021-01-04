package net.comexis.kata.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {

    //The cell number (from 1 to 9)
    private int number;

    //The occupying player
    private Player player;

}
