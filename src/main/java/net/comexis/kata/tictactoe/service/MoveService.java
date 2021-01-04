package net.comexis.kata.tictactoe.service;

import net.comexis.kata.tictactoe.domain.Move;

import java.util.Optional;

public interface MoveService {

    Iterable<Move> list();

    Optional<Move> load(Long id);

    Move save(Move entity);

    void delete(Long id);
}
