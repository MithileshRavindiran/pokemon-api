package com.example.pokemonapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonCharacterPage {

    @Schema(description = "pagenumber of the pageable response", example = "0", required = true, defaultValue = "0")
    private int pageNumber = 0;

    @Schema(description = "total elements on the page of the pageable response", example = "10", required = true, defaultValue = "10")
    private int pageSize = 10;

    @Schema(description = "Sorting direction of the paged response", example = "ASC", required = true, defaultValue = "ASC")
    private Sort.Direction sortDirection = Sort.Direction.ASC;

    @Schema(description = "Field on which the sorting needs to be done", example = "name", required = true, defaultValue = "name")
    private String sortBy = "name";

}
