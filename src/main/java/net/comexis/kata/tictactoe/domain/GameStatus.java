package net.comexis.kata.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.GameStatusType;
import net.comexis.kata.tictactoe.enums.PlayerType;
import net.comexis.kata.tictactoe.enums.WinType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Embeddable
public class GameStatus {

    //The status of the game (Running, Game Over or Draw game)
    @Column(name = "game_status_type")
    @Enumerated(EnumType.STRING)
    private GameStatusType statusType = GameStatusType.RUNNING;

    //The type of win (by taking a row, a columns or a diagonal)
    @Column(name = "win_type")
    @Enumerated(EnumType.STRING)
    private WinType winType;

    //The the row, colomn or diagonal number taken by the winning player
    @Column(name = "win_type_number")
    private Integer winTypeNumber;

    //The winning player  (X or O)
    @Column(name = "player_type")
    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

}
