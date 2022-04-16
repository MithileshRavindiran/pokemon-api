package com.example.pokemonapi.service.factory.parse;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.exception.FileParserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.pokemonapi.util.ApiConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CSVFileParser implements  FileParseType{

    public static final String CONTENT_TYPE_TEXT_CSV = "text/csv";

    @Override
    public String getFileType() {
        return CONTENT_TYPE_TEXT_CSV;
    }

    /***
     * Method to parse the CSV multipart file and get the characters from the uploaded file
     * @param file of type MultipartFile {@link MultipartFile}
     * @return List of PokemonCharacters {@link PokemonCharacters}
     * @throws FileParserException
     */
    @Override
    public List<PokemonCharacters> extract(MultipartFile file) throws FileParserException {
        List<PokemonCharacters> pokemonCharactersList;
        log.info(PROCESSING_CSV_FILE);
        try(Reader fileReader = new InputStreamReader(file.getInputStream())) {
            List<CSVRecord> csvRecord = CSVFormat.DEFAULT
                    .withHeader(CSV_DEFAULT_HEADER_MAPPING).parse(fileReader).getRecords();
            pokemonCharactersList = csvRecord.stream().skip(CSV_HEADER_LINE_NUMBER)
                    .map(getPokemonCharactersFunction())
                    .collect(Collectors.toList());
            return pokemonCharactersList;
        } catch (Exception ex) {
            log.error(EXCEPTION_IN_PROCESSING_CSV_FILE);
            throw new FileParserException(EXCEPTION_IN_PROCESSING_CSV_FILE ,ex);
        }
    }

    private Function<CSVRecord, PokemonCharacters> getPokemonCharactersFunction() {
        return record -> PokemonCharacters.builder()
                .name(record.get(NAME))
                .typeOne(record.get(TYPE_ONE))
                .typeTwo(record.get(TYPE_TWO))
                .total(Integer.valueOf(TOTAL))
                .hp(Integer.valueOf(HIT_POINTS))
                .attack(Integer.valueOf(ATTACK))
                .defense(Integer.valueOf(DEFENSE))
                .specialAttack(Integer.valueOf(SPECIAL_ATTACK))
                .specialDefense(Integer.valueOf(SPECIAL_DEFENSE))
                .speed(Integer.valueOf(SPEED))
                .generation(Integer.valueOf(GENERATION))
                .legendary(Integer.valueOf(LEGENDARY) == 1 ? Boolean.TRUE : Boolean.FALSE)
                .build();
    }
}
