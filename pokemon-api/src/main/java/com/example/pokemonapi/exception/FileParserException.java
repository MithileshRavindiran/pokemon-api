package com.example.pokemonapi.exception;

/**
 * Created by mravindran on 08/04/20.
 */
public class FileParserException extends RuntimeException {


	public FileParserException(Throwable excption) {
		super(excption);
	}
	
	public FileParserException(String message) {
		super(message);
	}

	public FileParserException(String message, Throwable cause) {
		super(message, cause);
	}

}