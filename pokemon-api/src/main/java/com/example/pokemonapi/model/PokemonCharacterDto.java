package com.example.pokemonapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonCharacterDto {

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
}
