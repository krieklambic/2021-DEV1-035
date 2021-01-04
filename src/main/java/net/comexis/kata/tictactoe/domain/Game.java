package net.comexis.kata.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//Lombok
@Getter
@Setter

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_game")
    @SequenceGenerator(name = "seq_game", sequenceName = "game_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "prestation")
    private List<Move> moves = new ArrayList<>();

    @Column(name = "active")
    private Boolean active = true;

    @JsonIgnore
    @Column(name = "date_created", updatable = false)
    @CreationTimestamp
    private Timestamp dateCreated;

    @JsonIgnore
    @Column(name = "last_updated")
    @UpdateTimestamp
    private Timestamp lastUpdated;

    public Game(){
        super();
    }

}
