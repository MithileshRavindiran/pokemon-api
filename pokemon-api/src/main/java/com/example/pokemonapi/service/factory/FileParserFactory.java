package com.example.pokemonapi.service.factory;

import com.example.pokemonapi.entity.PokemonCharacters;
import com.example.pokemonapi.exception.FileParserException;
import com.example.pokemonapi.service.factory.parse.FileParseType;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@NoArgsConstructor
public class FileParserFactory {

    private final Map<String, FileParseType> fileParseTypeMap = new ConcurrentHashMap<>();

    @Autowired
    public FileParserFactory(List<FileParseType> fileParseTypeList) {
        fileParseTypeList.forEach(type -> fileParseTypeMap.put(type.getFileType(), type));
    }


    public List<PokemonCharacters> extract(MultipartFile file)  {
        FileParseType fileParseType = fileParseTypeMap.get(file.getContentType().toLowerCase());
        if (fileParseType != null) {
            return  fileParseType.extract(file);
        }

        throw new FileParserException("Unsupported File Type. File Type = " + fileParseType);
    }
}
