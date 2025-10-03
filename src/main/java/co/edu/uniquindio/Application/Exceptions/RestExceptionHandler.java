package co.edu.uniquindio.Application.Exceptions;

import co.edu.uniquindio.Application.DTO.ResponseDTO;
import co.edu.uniquindio.Application.DTO.ValidationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseDTO<String>> noResourceFoundExceptionHandler(NoResourceFoundException ex){
        return ResponseEntity.status(404).body( new ResponseDTO<>(true, "El recurso solicitado no existe") );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO<String>> notFoundExceptionHandler(NotFoundException ex){
        return ResponseEntity.status(404).body( new ResponseDTO<>(true, ex.getMessage()) );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseDTO<String>> validationExceptionHandler(ValidationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ResponseDTO<>(true, ex.getMessage()) );
    }

    @ExceptionHandler(ValueConflictException.class)
    public ResponseEntity<ResponseDTO<String>> valueConflictExceptionHandler(ValueConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body( new ResponseDTO<>(true, ex.getMessage()) );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> exceptionHandler (Exception e){
        return ResponseEntity.internalServerError().body( new ResponseDTO<>(true, e.getMessage()) );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<List<ValidationDTO>>> validationExceptionHandler ( MethodArgumentNotValidException ex ) {
        List<ValidationDTO> errors = new ArrayList<>();
        BindingResult results = ex.getBindingResult();
        for (FieldError e: results.getFieldErrors()) {
            errors.add( new ValidationDTO(e.getField(), e.getDefaultMessage()) );
        }
        return ResponseEntity.badRequest().body( new ResponseDTO<>(true, errors) );
    }

    @ExceptionHandler(OperacionInvalidaException.class)
    public ResponseEntity<Map<String, Object>> manejarOperacionInvalida(OperacionInvalidaException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("error", "Operación inválida");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("error", "Recurso no encontrado");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}