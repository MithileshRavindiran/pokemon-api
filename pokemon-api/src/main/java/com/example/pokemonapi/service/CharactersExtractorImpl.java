package com.example.pokemonapi.service;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.exception.FileParserException;
import com.example.pokemonapi.service.factory.FileParserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Used to extract the characters of pokemon from the uploaded file
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CharactersExtractorImpl implements CharactersExtractor{

    private final FileParserFactory fileParserFactory;

    @Override
    public List<PokemonCharacters> extractCharacters(MultipartFile file) throws FileParserException {
        List<PokemonCharacters> pokemonCharacters = fileParserFactory.extract(file);
        return pokemonCharacters;
    }
}
