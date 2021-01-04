package net.comexis.kata.tictactoe.controller;

import net.comexis.kata.tictactoe.domain.Game;
import net.comexis.kata.tictactoe.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/games")
@RestController
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Iterable<Game> list() {
        return this.service.list();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Optional<Game> load(@PathVariable("id") Long id) {
        return this.service.load(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Game create(@RequestBody Game entity) {
        return this.service.save(entity);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
    }
}
