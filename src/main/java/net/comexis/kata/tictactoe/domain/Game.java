package net.comexis.kata.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.PlayerType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

//Lombok
@Getter
@Setter

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_game")
    @SequenceGenerator(name = "seq_game", sequenceName = "game_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    //the list of game moves
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "game")
    private List<Move> moves;

    //The status of the game
    @Embedded
    private GameStatus gameStatus;

    //The entity status for logical delete
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

    //Constructors
    public Game(){
        super();
        this.setMoves(new ArrayList<>());
        this.setGameStatus(new GameStatus());
    }

    /**
     * Add a new move to the game
     * @param move
     */
    public void addMove(Move move){
        this.getMoves().add(move);
    }

    /**
     * Get the Player Type (X or O) of the last move of the game
     * @return PlayerType
     */
    public PlayerType getLastMovePlayerType(){
        List<Move> moves = this.getMoves();
        Collections.sort(moves);
        Move move = moves.get(moves.size() - 1);
        return move.getPlayerType();
    }

}
