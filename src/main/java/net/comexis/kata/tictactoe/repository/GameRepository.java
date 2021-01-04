package net.comexis.kata.tictactoe.repository;

import net.comexis.kata.tictactoe.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    Iterable<Game> findAllByOrderByIdDesc();

}
