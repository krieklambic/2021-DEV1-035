package net.comexis.kata.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.comexis.kata.tictactoe.enums.PlayerType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//Lombok
@Getter
@Setter

@Entity
@Table(name = "move")
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_move")
    @SequenceGenerator(name = "seq_move", sequenceName = "move_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @Column(name = "cell_number")
    private Integer cellNumber;

    @Column(name = "player_type")
    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

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

}
