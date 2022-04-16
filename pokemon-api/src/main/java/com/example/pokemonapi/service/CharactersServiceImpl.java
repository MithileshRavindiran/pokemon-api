package com.example.pokemonapi.service;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.repository.PokemonCharactersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class to persist the extracted records from the uploaded file to the database.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CharactersServiceImpl implements CharactersService{

    private final PokemonCharactersRepository pokemonCharactersRepository;

    @Override
    public void persistTheExtractedRecords(List<PokemonCharacters> pokemonCharactersList) {
        if (CollectionUtils.isNotEmpty(pokemonCharactersList)) {
            pokemonCharactersRepository.saveAll(pokemonCharactersList);
        }
    }
}
