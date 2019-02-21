package br.com.movile.exception.handler;

import br.com.movile.exception.model.BusinessException;
import br.com.movile.exception.model.dto.MapFoodException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class MapFoodExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    MapFoodException businessExceptionHandler(BusinessException businessException){

        return new MapFoodException(
                businessException.getMessage(),
                businessException.getClass().getName()
        );
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    MapFoodException validationExceptionHandler(Exception exception){

        return new MapFoodException(
                exception.getMessage(),
                exception.getClass().getName()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    MapFoodException runtimeExceptionHandler(RuntimeException runtimeException) {
        return new MapFoodException(
                runtimeException.getMessage(),
                runtimeException.getClass().getName()
        );
    }
}
