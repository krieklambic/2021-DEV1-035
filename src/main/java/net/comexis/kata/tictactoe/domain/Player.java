package net.comexis.kata.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.PlayerType;

@Getter
@Setter
public class Player {

    private PlayerType type;

    public Player(){
        super();
    }

    public Player(PlayerType type){
        this.setType(type);
    }

}
