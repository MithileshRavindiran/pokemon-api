package com.example.pokemonapi.service.factory.parse;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.exception.FileParserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CSVFileParser implements  FileParseType{

    public static final String CONTENT_TYPE_TEXT_CSV = "text/csv";

    @Override
    public String getFileType() {
        return CONTENT_TYPE_TEXT_CSV;
    }

    @Override
    public List<PokemonCharacters> extract(MultipartFile file) throws FileParserException {
        return null;
    }
}
