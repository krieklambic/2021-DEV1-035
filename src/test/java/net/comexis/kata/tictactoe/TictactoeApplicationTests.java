package net.comexis.kata.tictactoe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.comexis.kata.tictactoe.domain.Game;
import net.comexis.kata.tictactoe.domain.Move;
import net.comexis.kata.tictactoe.domain.Player;
import net.comexis.kata.tictactoe.enums.GameStatusType;
import net.comexis.kata.tictactoe.enums.PlayerType;
import net.comexis.kata.tictactoe.enums.WinType;
import net.comexis.kata.tictactoe.exception.InvalidCellNumberException;
import net.comexis.kata.tictactoe.exception.InvalidPlayerException;
import net.comexis.kata.tictactoe.exception.InvalidPlayerOrderException;
import net.comexis.kata.tictactoe.exception.NotEmptyCellException;
import net.comexis.kata.tictactoe.service.GameService;
import net.comexis.kata.tictactoe.service.MoveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TictactoeApplicationTests {

    private final Player playerX = new Player("foo", PlayerType.X);     // Player X
    private final Player playerO = new Player("bar", PlayerType.O);     // Player O
    private final Integer VALID_CELL_NUMBER = 5;                              // A valide cell number
    private final Integer INVALID_CELL_NUMBER_GT9 = 10;                       // Invalid cell number ( > 9)
    private final Integer INVALID_CELL_NUMBER_LT1 = 0;                        // Invalid cell number ( < 1)

    private Game game;

    @Autowired
    GameService gameService;

    @Autowired
    MoveService moveService;

    @BeforeEach
    public void setUp(){
        this.game = new Game();
        this.gameService.save(game);
    }

    // X must play first
    @Test
    public void testOPlaysFirst() throws Exception{
        Assertions.assertThrows(InvalidPlayerOrderException.class, () -> {
            this.addMove(this.game, this.playerO, VALID_CELL_NUMBER);
        });
    }


    // Players must play alternatively ( X, then O, then X, then 0....
    @Test
    public void testPlaysSamePlayer() throws Exception {
        Assertions.assertThrows(InvalidPlayerException.class, () -> {
            this.addMove(this.game, this.playerX, 5);
            this.addMove(this.game, this.playerX, 6);
        });
    }

    @Test
    public void testInvalidValidCellNumberGT9() throws Exception {
        Assertions.assertThrows(InvalidCellNumberException.class, () -> {
            this.addMove(this.game, this.playerX, INVALID_CELL_NUMBER_GT9);
        });
    }

    @Test
    public void testInvalidValidCellNumberLT1() throws Exception {
        Assertions.assertThrows(InvalidCellNumberException.class, () -> {
            Move move = new Move(this.game, this.playerX, INVALID_CELL_NUMBER_LT1);
            this.moveService.save(move);
        });
    }

    // Players must play in an empty cell.
    @Test
    public void testPlayedCellIsNotEmpty() throws Exception {
        Assertions.assertThrows(NotEmptyCellException.class, () -> {
            //Create a first move for Player X
            this.addMove(this.game, this.playerX, 5);
            //Create a second move for Player O in the same cell as playerX
            this.addMove(this.game, this.playerO, 5);
        });
    }

    // Game is over if all cells in the board are filled
    @Test
    public void testGameOverForFilledBoard() throws Exception {
        //Create 9 moves to fill the board without having a winning condition
        /*  O X X
            X X O
            O O X  */
        this.addMove(this.game, this.playerX, 5);
        this.addMove(this.game, this.playerO, 1);
        this.addMove(this.game, this.playerX, 3);
        this.addMove(this.game, this.playerO, 7);
        this.addMove(this.game, this.playerX, 9);
        this.addMove(this.game, this.playerO, 6);
        this.addMove(this.game, this.playerX, 2);
        this.addMove(this.game, this.playerO, 8);
        this.addMove(this.game, this.playerX, 4);

        //Asserts that the game has the DRAW status
        Assert.isTrue(GameStatusType.DRAW.equals(game.getGameStatus().getStatusType()), "Status of the game must be a DRAW");
    }

    // Game is over if all cells in a row are taken by a same player
    @Test
    public void testGameOverForRowTaken() throws Exception {
        //Create a situation when a player wins a row
        /*  O O
            X X X  */
        this.addMove(this.game, this.playerX, 4);
        this.addMove(this.game, this.playerO, 1);
        this.addMove(this.game, this.playerX, 5);
        this.addMove(this.game, this.playerO, 2);
        this.addMove(this.game, this.playerO, 6);

        //Asserts that the game has the Game Over status with a ROW win Type
        Assert.isTrue(GameStatusType.DRAW.equals(game.getGameStatus().getStatusType()), "Status of the game must be a GameOver");
        Assert.isTrue(WinType.ROW.equals(game.getGameStatus().getWinType()), "WinType of the game must be ROW");
    }

    // Game is over if all cells in a column are taken by a same player
    @Test
    public void testGameOverForColumnTaken() throws Exception {
        //Create a situation when a player wins a column
        /*  X O
            X O
            X  */
        this.addMove(this.game, this.playerX, 1);
        this.addMove(this.game, this.playerO, 2);
        this.addMove(this.game, this.playerX, 4);
        this.addMove(this.game, this.playerO, 5);
        this.addMove(this.game, this.playerO, 7);

        //Asserts that the game has the Game Over status with a ROW win Type
        Assert.isTrue(GameStatusType.DRAW.equals(game.getGameStatus().getStatusType()), "Status of the game must be a GameOver");
        Assert.isTrue(WinType.COLUMN.equals(game.getGameStatus().getWinType()), "WinType of the game must be COLUMN");
    }

    // Game is over if all cells in a diagonal are taken by a same player
    @Test
    public void testGameOverForDiagonalTaken() throws Exception {
        //Create a situation when a player wins a diagonal
        /*  X O
            0 X
                X  */
        this.addMove(this.game, this.playerX, 1);
        this.addMove(this.game, this.playerO, 2);
        this.addMove(this.game, this.playerX, 5);
        this.addMove(this.game, this.playerO, 4);
        this.addMove(this.game, this.playerO, 9);

        //Asserts that the game has the Game Over status with a ROW win Type
        Assert.isTrue(GameStatusType.DRAW.equals(game.getGameStatus().getStatusType()), "Status of the game must be a GameOver");
        Assert.isTrue(WinType.DIAGONAL.equals(game.getGameStatus().getWinType()), "WinType of the game must be DIAGONAL");
    }

    /*
        Convenience methods
     */

    /**
     * Add a move on an existing game
     * @param game
     * @param player
     * @param cellNumber
     * @return The created move
     * @throws Exception
     */
    private Move addMove(Game game, Player player, Integer cellNumber) throws Exception {
        Move move = new Move(game, player, cellNumber);
        this.moveService.save(move);
        game.addMove(move);
        this.gameService.save(game);
        return move;
    }

    /**
     * Converts an object to its JSON String representation
     * @param o Object to convert
     * @return String JSON representation of the object
     * @throws JsonProcessingException
     */
    private String toJSON(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }



}
