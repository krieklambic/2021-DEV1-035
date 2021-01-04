package net.comexis.kata.tictactoe.service;

import net.comexis.kata.tictactoe.domain.Game;

import java.util.Optional;

public interface GameService {

    Iterable<Game> list();

    Optional<Game> load(Long id);

    Game save(Game entity);

    void delete(Long id);
}
