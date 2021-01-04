package net.comexis.kata.tictactoe;

import net.comexis.kata.tictactoe.domain.Game;
import net.comexis.kata.tictactoe.service.GameService;
import net.comexis.kata.tictactoe.service.MoveService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TictactoeApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GameService gameService;

    @MockBean
    MoveService moveService;

    // New Game
    @Test
    public void testGameCreation() throws Exception {

    }

    // X plays first.
    @Test
    public void testXPlaysFirst() throws Exception{

    }

    // Players must play alternatively ( X, then O, then X, then 0....)
    @Test
    public void testPlaysAlternatively() throws Exception {

    }

    // Players are playing by specifying a valid cell number ( 1 .. 9 )
    @Test
    public void testValidCellNumber() throws Exception {

    }

    // Players must play in an empty cell.
    @Test
    public void testPlayedCellIsEmpty() throws Exception {

    }

    // Game is over if all cells in the board are filled
    @Test
    public void testGameOverForFilledBoard() throws Exception {

    }

    // Game is over if all cells in a row are taken by a same player
    @Test
    public void testGameOverForRowTaken() throws Exception {

    }

    // Game is over if all cells in a column are taken by a same player
    @Test
    public void testGameOverForColumnTaken() throws Exception {

    }

    // Game is over if all cells in a diagonal are taken by a same player
    @Test
    public void testGameOverForDiagonalTaken() throws Exception {

    }



}
