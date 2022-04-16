package com.example.pokemonapi.service;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.mapper.EntityToDtoMapper;
import com.example.pokemonapi.model.PokemonCharacterDto;
import com.example.pokemonapi.model.PokemonCharacterPage;
import com.example.pokemonapi.model.PokemonCharacterSearchCriteria;
import com.example.pokemonapi.repository.PokemonCharacterCriteriaRepository;
import com.example.pokemonapi.repository.PokemonCharactersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private final PokemonCharacterCriteriaRepository pokemonCharacterCriteriaRepository;

    private final EntityToDtoMapper entityToDtoMapper;

    @Override
    public void persistTheExtractedRecords(List<PokemonCharacters> pokemonCharactersList) {
        if (CollectionUtils.isNotEmpty(pokemonCharactersList)) {
            pokemonCharactersRepository.saveAll(pokemonCharactersList);
        }
    }

    @Override
    public Page<PokemonCharacterDto> getCharacters(PokemonCharacterPage pokemonCharacterPage, PokemonCharacterSearchCriteria pokemonCharacterSearchCriteria) {
        Page<PokemonCharacters> pokemonCharactersPage = pokemonCharacterCriteriaRepository.findAllWithFilters(pokemonCharacterPage, pokemonCharacterSearchCriteria);
        return pokemonCharactersPage.map(x -> entityToDtoMapper.pokemonEntityToPokemonDto(x));
    }


    private Pageable getPageable(PokemonCharacterPage pokemonCharacterPage) {
        Sort sort = Sort.by(pokemonCharacterPage.getSortDirection(), pokemonCharacterPage.getSortBy());
        return PageRequest.of(pokemonCharacterPage.getPageNumber(),pokemonCharacterPage.getPageSize(), sort);
    }
}
