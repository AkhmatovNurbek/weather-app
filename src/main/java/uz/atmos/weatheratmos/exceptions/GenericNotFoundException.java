package uz.atmos.weatheratmos.exceptions;

import lombok.Getter;

@Getter
public class GenericNotFoundException extends RuntimeException{
    private final Integer statusCode;

    public GenericNotFoundException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
