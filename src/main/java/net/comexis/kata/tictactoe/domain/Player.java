package net.comexis.kata.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.PlayerType;

@Getter
@Setter
public class Player {

    private String name;

    private PlayerType type;

    public Player(){
        super();
    }

    public Player(String name, PlayerType type){
        this.setName(name);
        this.setType(type);
    }

}
