package med.voll.api.domain;

public class ValidationException extends RuntimeException {
    public ValidationException(String mensagem){
        super(mensagem);
    }

}
