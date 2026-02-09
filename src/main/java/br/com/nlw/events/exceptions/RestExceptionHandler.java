package br.com.nlw.events.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.nlw.events.domain.dto.ExceptionDTO;

/**
 * Global Controller Exception
 * 
 * com RestControllerAdvice
 * 
*/
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Generic exception for Exception.class
     * 
     * @param Exception.class
     * @return ResponseEntity exceptionDTO
     * @return ResponseEntity HttpStatus.INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> genericException(Exception e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                                        HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                                        HttpStatus.INTERNAL_SERVER_ERROR.name(), 
                                        List.of(e.getMessage()), 
                                        LocalDateTime.now()
                                        );

        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Not Found Exception
     * 
     * @param RuntimeException.class
     * @return ResponseEntity exceptionDTO
     * @return ResponseEntity HttpStatus.NOT_FOUND
     */
    @ExceptionHandler({
        NotFoundException.class
    })
    public ResponseEntity<ExceptionDTO> notFoundException(RuntimeException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                                        HttpStatus.NOT_FOUND.value(), 
                                        HttpStatus.NOT_FOUND.name(), 
                                        List.of(e.getMessage()), 
                                        LocalDateTime.now()
                                        );

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * Already Exists Exception
     * 
     * @param RuntimeException.class
     * @return ResponseEntity exceptionDTO
     * @return ResponseEntity HttpStatus.CONFLICT
     */
    @ExceptionHandler({
        AlreadyExistsException.class
    })
    public ResponseEntity<ExceptionDTO> alreadyExistsException(RuntimeException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                                        HttpStatus.CONFLICT.value(), 
                                        HttpStatus.CONFLICT.name(), 
                                        List.of(e.getMessage()), 
                                        LocalDateTime.now()
                                        );

        return new ResponseEntity<>(exceptionDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> message = e.getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.toList());

        ExceptionDTO exceptionDTO = new ExceptionDTO(
                                        HttpStatus.BAD_REQUEST.value(),
                                        HttpStatus.BAD_REQUEST.name(),
                                        message,
                                        LocalDateTime.now());

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
}
