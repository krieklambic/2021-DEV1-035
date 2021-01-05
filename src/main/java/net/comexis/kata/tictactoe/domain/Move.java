package net.comexis.kata.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.PlayerType;
import net.comexis.kata.tictactoe.exception.InvalidCellNumberException;
import net.comexis.kata.tictactoe.exception.InvalidPlayerException;
import net.comexis.kata.tictactoe.exception.InvalidPlayerOrderException;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

//Lombok
@Getter
@Setter

@Entity
@Table(name = "move")
public class Move implements Comparable<Move> {

    private static final Integer CELL_NUMBER_MIN = 1;
    private static final Integer CELL_NUMBER_MAX = 9;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_move")
    @SequenceGenerator(name = "seq_move", sequenceName = "move_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @Column(name = "cell_number")
    private Integer cellNumber;

    @Column(name = "player_type")
    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    @Column(name = "active")
    private Boolean active = true;

    @JsonIgnore
    @Column(name = "date_created", updatable = false)
    @CreationTimestamp
    private Timestamp dateCreated;

    @JsonIgnore
    @Column(name = "last_updated")
    @UpdateTimestamp
    private Timestamp lastUpdated;

    public Move(){
        super();
    }

    public Move(Game game, Player player, Integer cellNumber){
        //Check if the current player is valid
        this.checkPlayer(game, player);

        this.setGame(game);
        this.setPlayerType(player.getType());
        this.setCellNumber(cellNumber);
    }

    /**
     * Check is the paying player is valid. To be valid the following conditions must be respected :
     *      - Player X must play first
     *      - The same player cannot play 2 times in a row
     * @param game
     * @param player
     * @throws InvalidPlayerException
     */
    private void checkPlayer(Game game, Player player) throws InvalidPlayerException, InvalidPlayerOrderException {
        List<Move> moves = game.getMoves();
        if(moves.size() == 0){
            if(!PlayerType.X.equals(player.getType())){
                throw new InvalidPlayerOrderException("Invalid player order : " + player.getType().toString() + ". Player X must play first.");
            }
        } else {
            PlayerType lastPlayerType =  game.getLastMovePlayerType();
            if(player.getType().equals(lastPlayerType)){
                throw new InvalidPlayerException("Invalid player  : " + player.getType().toString() + ". The same player cannot do 2 moves in a row.");
            }
        }
    }

    public void setCellNumber(Integer cellNumber){
        if(cellNumber < CELL_NUMBER_MIN || cellNumber > CELL_NUMBER_MAX){
            throw new InvalidCellNumberException("Invalic Cell number :  " + cellNumber + ". Celle number must be comprised between 1 and 9");
        }
        this.cellNumber = cellNumber;
    }

    @Override
    public int compareTo(Move m) {
        try {
            return this.getId().compareTo(m.getId());
        } catch (NullPointerException ne){
            return 1;
        }
    }
}
