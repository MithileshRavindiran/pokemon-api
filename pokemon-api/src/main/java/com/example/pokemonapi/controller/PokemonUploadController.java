package com.example.pokemonapi.controller;

import com.example.pokemonapi.service.CharactersExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/pokemon")
public class PokemonUploadController {

   private final CharactersExtractor charactersExtractor;

    @PostMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> uploadPokemonCharacters(@RequestParam(value = "file")MultipartFile file) {
        charactersExtractor.extractCharacters(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
