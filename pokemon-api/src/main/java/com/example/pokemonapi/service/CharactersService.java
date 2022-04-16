package com.example.pokemonapi.service;


import com.example.pokemonapi.entity.PokemonCharacters;

import java.util.List;

public interface CharactersService {

    void persistTheExtractedRecords(List<PokemonCharacters> pokemonCharactersList);

}
