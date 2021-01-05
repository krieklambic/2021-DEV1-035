package net.comexis.kata.tictactoe.service;

import net.comexis.kata.tictactoe.domain.Game;
import net.comexis.kata.tictactoe.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    private final GameRepository repository;

    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Game> list() {
        return this.repository.findAllByOrderByIdDesc();
    }

    @Override
    public Optional<Game> load(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Game save(Game entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<Game> optional = this.repository.findById(id);
        if(optional.isPresent()) {
            Game entity = optional.get();
            entity.setActive(false);
            this.repository.save(entity);
        } else {
            logger.error("Cannot delete Game : entity not available.");
        }
    }
}
