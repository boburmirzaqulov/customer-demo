package uz.najottalim.customer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.najottalim.customer.dto.ErrorDTO;

@ControllerAdvice
public class NoResourceFoundExceptionHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO> handler(NoResourceFoundException ex){
        return ResponseEntity.badRequest().body(
                ErrorDTO.builder().errors(ex.getMessage()).build()
        );
    }
}
