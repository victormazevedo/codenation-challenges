package br.com.movile.exception.handler;

import br.com.movile.exception.model.CustomerTooFarException;
import br.com.movile.exception.model.ElementAlreadyExistException;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import br.com.movile.exception.model.dto.MapFoodException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class MapFoodExceptionHandler {

    @ExceptionHandler(NoMotoboyAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    MapFoodException noMotoboyAvailableExceptionHandler(Exception exception) {

        return buildMapFoodException(exception);
    }

    @ExceptionHandler(CustomerTooFarException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    MapFoodException customerTooFarExceptionHandler(Exception exception) {

        return buildMapFoodException(exception);
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            NoSuchElementException.class,
            ElementAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    MapFoodException validationExceptionHandler(Exception exception) {

        return buildMapFoodException(exception);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    MapFoodException runtimeExceptionHandler(RuntimeException runtimeException) {

        return buildMapFoodException(runtimeException);
    }

    private MapFoodException buildMapFoodException(Exception exception) {
        return new MapFoodException(
                exception.getMessage(),
                exception.getClass().getName()
        );
    }
}
