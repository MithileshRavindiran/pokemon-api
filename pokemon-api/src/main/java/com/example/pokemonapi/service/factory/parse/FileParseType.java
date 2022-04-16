package com.example.pokemonapi.service.factory.parse;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.exception.FileParserException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileParseType {
    String getFileType();
    List<PokemonCharacters> extract(MultipartFile file) throws FileParserException;
}
