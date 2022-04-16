package com.example.pokemonapi.service;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.exception.FileParserException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CharactersExtractor {

    /***
     * Method the extract  the characters form the MultipartFile
     * @param file of type MultiPartFile {@link MultipartFile}
     * @return List of PokemonCharacters to be uploaded in the database {@link PokemonCharacters}
     * @throws FileParserException
     */
    List<PokemonCharacters> extractCharacters(MultipartFile file) throws FileParserException;
}
