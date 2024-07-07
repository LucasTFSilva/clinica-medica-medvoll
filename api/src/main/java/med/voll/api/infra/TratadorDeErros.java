package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//O Sprint segue um padrão para lidar com erros, mas podemos configurar esse comportamento

//A anotação RestControllerAdvice sinaliza ao Spring que essa será uma classe de tratamento
@RestControllerAdvice
public class TratadorDeErros {

    //Criamos um método para tratar um erro específico sinalizado pelo comentário.
    //Podemos criar quantos ExceptionHandlers forem necessários.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErros404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErros400(MethodArgumentNotValidException ex){
        var erro = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erro.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
