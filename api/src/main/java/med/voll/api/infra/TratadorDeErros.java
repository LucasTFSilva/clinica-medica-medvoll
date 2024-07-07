package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//O Sprint segue um padrão para lidar com erros, mas podemos configurar esse comportamento

//A anotação RestControllerAdvice sinaliza ao Spring que essa será uma classe de tratamento
@RestControllerAdvice
public class TratadorDeErros {

    //Criamos um método para tratar um erro específico sinalizado pelo comentário.
    //Podemos criar quantos ExceptionHandlers forem necessários.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErros(){
        return ResponseEntity.notFound().build();
    }
}
