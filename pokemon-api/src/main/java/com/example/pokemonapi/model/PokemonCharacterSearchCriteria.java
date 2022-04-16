package com.example.pokemonapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonCharacterSearchCriteria {

    private Integer hp;

    private Integer attack;

    private Integer defense;

}
