package com.example.pokemonapi.service;


import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.model.PokemonCharacterDto;
import com.example.pokemonapi.model.PokemonCharacterPage;
import com.example.pokemonapi.model.PokemonCharacterSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CharactersService {

    void persistTheExtractedRecords(List<PokemonCharacters> pokemonCharactersList);

    Page<PokemonCharacterDto> getCharacters(PokemonCharacterPage pokemonCharacterPage, PokemonCharacterSearchCriteria pokemonCharacterSearchCriteria);
}
