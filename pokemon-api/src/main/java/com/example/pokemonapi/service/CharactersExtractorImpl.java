package com.example.pokemonapi.service;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.exception.FileParserException;
import com.example.pokemonapi.service.factory.FileParserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Used to extract the characters of pokemon and filtering the necessary characters from the uploaded file
 *
 * This class can also be used at later point of time to do more validations
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CharactersExtractorImpl implements CharactersExtractor{

    private final FileParserFactory fileParserFactory;

    private static  final  String EXCLUDING_CHARACTER_TYPE = "Ghost";

    private static final  String STEEL_CHARACTER_TYPE = "Steel";

    private static final String FIRE_CHARACTER_TYPE = "Fire";

    private static final String BUG_CHARACTER_TYPE = "Bug";

    private static final String FLYING_CHARACTER_TYPE = "Flying";

    private static final String NAME_STARTS_WITH_G = "g";

    private static final String REGEX_SPACE_REMOVER = "\\s+";

    private static final String EMPTY_STRING = "";

    @Override
    public List<PokemonCharacters> extractCharacters(MultipartFile file) throws FileParserException {
        List<PokemonCharacters> pokemonCharacters = fileParserFactory.extract(file);
        return pokemonCharacters.stream()
                 // excluding the legedenary pokeman characters
                .filter(characters -> !characters.isLegendary())
                //exclude pokemon of type ghost
                .filter(pokemonCharacter ->
                        (!EXCLUDING_CHARACTER_TYPE.equalsIgnoreCase(pokemonCharacter.getTypeOne()) &&
                                !EXCLUDING_CHARACTER_TYPE.equalsIgnoreCase(pokemonCharacter.getTypeTwo())))
                .map(this::applyPokemonRulesForExtractedCharacters)
                .collect(Collectors.toList());
    }

    private PokemonCharacters applyPokemonRulesForExtractedCharacters(PokemonCharacters x) {
        //For Pokémon of Type: Steel, double their HP
        if (STEEL_CHARACTER_TYPE.equalsIgnoreCase(x.getTypeOne()) || STEEL_CHARACTER_TYPE.equalsIgnoreCase(x.getTypeTwo())) {
            x.setHp(x.getHp() * 2);
        }

        // For Pokémon of Type: Fire, lower their Attack by 10%
        if (FIRE_CHARACTER_TYPE.equalsIgnoreCase(x.getTypeOne()) || FIRE_CHARACTER_TYPE.equalsIgnoreCase(x.getTypeTwo())) {
            x.setAttack( (int) Math.round(x.getAttack()*0.9));
        }

        //For Pokémon of Type: Bug & Flying, increase their Attack Speed by 10%
        if ((BUG_CHARACTER_TYPE.equalsIgnoreCase(x.getTypeOne()) &&
                FLYING_CHARACTER_TYPE.equalsIgnoreCase(x.getTypeTwo())) || (BUG_CHARACTER_TYPE.equalsIgnoreCase(x.getTypeTwo()) &&
                FLYING_CHARACTER_TYPE.equalsIgnoreCase(x.getTypeOne()))) {
            x.setSpecialAttack( (int) Math.round(x.getSpecialAttack()*1.1));
        }

        //For Pokémon that start with the letter G, add +5 Defense for every letter in their name (excluding G)
        if (x.getName().toLowerCase().startsWith(NAME_STARTS_WITH_G)) {
            int nameLength = x.getName().substring(1).replaceAll(REGEX_SPACE_REMOVER, EMPTY_STRING).length();
            x.setDefense(x.getDefense() + (nameLength*5));
        }

        return x;
    }
}
