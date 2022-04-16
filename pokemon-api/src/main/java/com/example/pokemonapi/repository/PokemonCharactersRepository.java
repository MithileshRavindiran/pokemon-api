package com.example.pokemonapi.repository;

import com.example.pokemonapi.entity.PokemonCharacters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCharactersRepository extends JpaRepository<PokemonCharacters, Long> {
}
