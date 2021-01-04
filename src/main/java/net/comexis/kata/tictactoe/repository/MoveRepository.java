package net.comexis.kata.tictactoe.repository;

import net.comexis.kata.tictactoe.domain.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move, Long> {

    Iterable<Move> findAllByOrderByIdDesc();

}
