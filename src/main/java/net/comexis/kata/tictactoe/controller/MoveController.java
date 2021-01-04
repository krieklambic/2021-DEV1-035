package net.comexis.kata.tictactoe.controller;

import net.comexis.kata.tictactoe.domain.Move;
import net.comexis.kata.tictactoe.service.MoveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/moves")
@RestController
public class MoveController {

    private final MoveService service;

    public MoveController(MoveService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Iterable<Move> list() {
        return this.service.list();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Optional<Move> load(@PathVariable("id") Long id) {
        return this.service.load(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Move create(@RequestBody Move entity) {
        return this.service.save(entity);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
    }
}
