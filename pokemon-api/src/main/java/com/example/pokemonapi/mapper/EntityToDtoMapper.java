package com.example.pokemonapi.mapper;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.model.PokemonCharacterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToDtoMapper {

    PokemonCharacterDto pokemonEntityToPokemonDto(PokemonCharacters pokemonCharacters);
}
