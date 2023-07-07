package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

@RestControllerAdvice
public class TratadorErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao:: new).toList());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratarError400(ValidationException ex){

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    private record DadosErrosValidacao(String campo, String mensagem){
        public DadosErrosValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
