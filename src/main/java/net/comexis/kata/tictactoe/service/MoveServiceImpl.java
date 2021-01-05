package net.comexis.kata.tictactoe.service;

import net.comexis.kata.tictactoe.domain.Move;
import net.comexis.kata.tictactoe.repository.MoveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoveServiceImpl implements MoveService {

    private static final Logger logger = LoggerFactory.getLogger(MoveServiceImpl.class);

    private final MoveRepository repository;

    public MoveServiceImpl(MoveRepository repository) {
        this.repository = repository;
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
    public Move save(Move entity) {
        return this.repository.save(entity);
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
