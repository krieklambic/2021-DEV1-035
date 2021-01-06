package net.comexis.kata.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.GameStatusType;
import net.comexis.kata.tictactoe.enums.PlayerType;
import net.comexis.kata.tictactoe.enums.WinType;
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

    @Transient
    public Board getBoard(){
        return new Board(this);
    }

    /**
     * Add a new move to the game
     * @param move
     */
    public void addMove(Move move){
        this.getMoves().add(move);
        this.updateStatus(move);
    }

    private void updateStatus(Move move) {
        Board board = this.getBoard();
        PlayerType playerType = move.getPlayerType();

        for(WinType winType: WinType.values()){
            if(playerWinsElement(board, playerType, winType)) return;
        }

        //Check if the board is full and there is no winner...
        checkBoardIsFull(board);

    }

    /**
     * Check if the player wins by taking all the cells of a row
     * @param board
     * @param playerType
     * @param winType
     * @return
     */
    private boolean playerWinsElement(Board board, PlayerType playerType, WinType winType) {
        int winningElement = board.getWinningElement(playerType, winType);
        if(winningElement > 0){
            this.setGameStatus(new GameStatus(GameStatusType.OVER, winType, winningElement, playerType));
            return true;
        }
        return false;
    }

    /**
     * Check if the board if full (A the cells have been played)
     * @param board
     */
    private void checkBoardIsFull(Board board) {
        if(board.isFull()){
            this.setGameStatus(new GameStatus(GameStatusType.DRAW, null, null, null));
        }
    }

    @Transient
    @JsonIgnore
    public Move getLastMove(){
        List<Move> moves = this.getMoves();
        Collections.sort(moves);
        return moves.get(moves.size() - 1);
    }

    /**
     * Get the Player Type (X or O) of the last move of the game
     * @return PlayerType
     */
    @Transient
    @JsonIgnore
    public PlayerType getLastMovePlayerType(){
        try {
            return this.getLastMove().getPlayerType();
        } catch (NullPointerException ne){
            return null;
        }
    }

    public boolean cellIsEmpty(Integer cellNumber) {
        Board board = this.getBoard();
        Cell playedCell = board.getCells()[cellNumber - 1];
        return playedCell.isEmpty();
    }
}
