package com.example.pokemonapi.controller;

import com.example.pokemonapi.exception.ErrorResponse;
import com.example.pokemonapi.model.PokemonCharacterDto;
import com.example.pokemonapi.model.PokemonCharacterPage;
import com.example.pokemonapi.model.PokemonCharacterSearchCriteria;
import com.example.pokemonapi.repository.PokemonCharacterCriteriaRepository;
import com.example.pokemonapi.service.CharactersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/pokemon")
public class PokemonController {

    private final CharactersService charactersService;

    @Operation(summary = "Get All the Pokemon Characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description =" Returns all of the posts",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PokemonCharacterPage.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request Exception",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Exception",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<PokemonCharacterDto>> getPokemonCharacters(@PageableDefault PokemonCharacterPage pokemonCharacterPage, PokemonCharacterSearchCriteria pokemonCharacterSearchCriteria) {
        return new ResponseEntity<>(charactersService.getCharacters(pokemonCharacterPage, pokemonCharacterSearchCriteria), HttpStatus.OK);
    }
}
