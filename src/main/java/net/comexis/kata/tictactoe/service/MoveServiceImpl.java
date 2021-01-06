package net.comexis.kata.tictactoe.service;

import net.comexis.kata.tictactoe.domain.Game;
import net.comexis.kata.tictactoe.domain.Move;
import net.comexis.kata.tictactoe.domain.Player;
import net.comexis.kata.tictactoe.exception.InvalidGameException;
import net.comexis.kata.tictactoe.repository.MoveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoveServiceImpl implements MoveService {

    private static final Logger logger = LoggerFactory.getLogger(MoveServiceImpl.class);

    private final MoveRepository repository;
    private final GameService gameService;

    public MoveServiceImpl(MoveRepository repository, GameService gameService) {
        this.repository = repository;
        this.gameService = gameService;
    }

    @Override
    public Iterable<Move> list() {
        return this.repository.findAllByOrderByIdDesc();
    }

    @Override
    public Optional<Move> load(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Move save(Move entity) throws RuntimeException {
        Optional<Game> optional = this.gameService.load(entity.getGame().getId());
        if(optional.isPresent()) {
            Game game = optional.get();
            Move move = new Move(game, new Player(entity.getPlayerType()), entity.getCellNumber());
            return this.repository.save(move);
        } else {
            throw new InvalidGameException("Specified game id (" + entity.getGame().getId() + " does not exist !");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Move> optional = this.repository.findById(id);
        if(optional.isPresent()) {
            Move entity = optional.get();
            entity.setActive(false);
            this.repository.save(entity);
        } else {
            logger.error("Cannot delete Move : entity not found.");
        }
    }
}
