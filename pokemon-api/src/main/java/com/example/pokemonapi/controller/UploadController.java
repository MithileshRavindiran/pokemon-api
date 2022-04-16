package com.example.pokemonapi.controller;

import com.example.pokemonapi.service.CharactersExtractor;
import com.example.pokemonapi.service.CharactersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class UploadController {

   private final CharactersExtractor charactersExtractor;

   private final CharactersService charactersService;

    @Operation(summary = "upload the pokemon characters list and save these records at database after filtering out some of the records based on some business logics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully saved the characters at database"),
            @ApiResponse(responseCode = "400", description = "Wrong format File uploaded/Exception while processing files"),
            @ApiResponse(responseCode = "500", description = "Internal Server Exception")
    })
    @PostMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> uploadPokemonCharacters(@RequestParam(value = "file")MultipartFile file) {
        charactersService.persistTheExtractedRecords(charactersExtractor.extractCharacters(file));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
