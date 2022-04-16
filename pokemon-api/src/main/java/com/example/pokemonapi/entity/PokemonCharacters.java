package com.example.pokemonapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.CharacterType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonCharacters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //Ideally this should be moved to a new table characterType and relate it with many to many relationship , but with time constraint
    // i can't do the  filtering out type now to create a table and re use it again
    //private Set<CharacterType> types;

    private String typeOne;

    private String typeTwo;

    private Integer total;

    private Integer hp;

    private Integer attack;

    private Integer defense;

    private Integer specialAttack;

    private Integer specialDefense;

    private Integer speed;

    private Integer generation;

    private boolean legendary;

}
