package net.comexis.kata.tictactoe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.comexis.kata.tictactoe.domain.Game;
import net.comexis.kata.tictactoe.domain.Player;
import net.comexis.kata.tictactoe.enums.PlayerType;
import net.comexis.kata.tictactoe.service.GameService;
import net.comexis.kata.tictactoe.service.MoveService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TictactoeApplicationTests {

    private final Long newGameId = 1234567890L;
    private final Player playerX = new Player("foo", PlayerType.X);
    private final Player playerO = new Player("bar", PlayerType.O);

    private Game game;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GameService gameService;

    @MockBean
    MoveService moveService;

    @BeforeEach
    public void setUp(){
        Game game = new Game(newGameId, null);
    }

    @AfterEach
    public void tearDown(){

    }

    // New Game
    @Test
    public void testNewGame() throws Exception {
        throw new RuntimeException();
    }


    // X must play first
    @Test
    public void testXPlaysFirst() throws Exception{
        throw new RuntimeException();
    }

    @Test
    public void testOPlaysFirst() throws Exception{
        throw new RuntimeException();
    }


    // Players must play alternatively ( X, then O, then X, then 0....
    @Test
    public void testPlaysSamePlayer() throws Exception {
        throw new RuntimeException();
    }


    @Test
    public void testPlaysAlternatively() throws Exception {
        throw new RuntimeException();
    }

    // Players are playing by specifying a valid cell number ( 1 .. 9 )
    @Test
    public void testValidCellNumber() throws Exception {
        throw new RuntimeException();
    }

    @Test
    public void testInvalidValidCellNumber() throws Exception {
        throw new RuntimeException();
    }

    // Players must play in an empty cell.
    @Test
    public void testPlayedCellIsNotEmpty() throws Exception {
        throw new RuntimeException();
    }

    @Test
    public void testPlayedCellIsEmpty() throws Exception {
        throw new RuntimeException();
    }

    // Game is over if all cells in the board are filled
    @Test
    public void testGameOverForFilledBoard() throws Exception {
        throw new RuntimeException();
    }

    // Game is over if all cells in a row are taken by a same player
    @Test
    public void testGameOverForRowTaken() throws Exception {
        throw new RuntimeException();
    }

    // Game is over if all cells in a column are taken by a same player
    @Test
    public void testGameOverForColumnTaken() throws Exception {
        throw new RuntimeException();
    }

    // Game is over if all cells in a diagonal are taken by a same player
    @Test
    public void testGameOverForDiagonalTaken() throws Exception {
        throw new RuntimeException();
    }

    /**
     * Converts an object to its JSON String representation
     * @param o Object to convert
     * @return String JSON representation of the object
     * @throws JsonProcessingException
     */
    private String toJSON(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }



}
