package br.com.rocha.API_Pedido.rest.exception;

import br.com.rocha.API_Pedido.exception.AuthorizationException;
import br.com.rocha.API_Pedido.exception.DataIntegratyException;
import br.com.rocha.API_Pedido.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

        @ExceptionHandler(ObjectNotFoundException.class)
        public ResponseEntity<StandardError> handlerObjectNotFoundException(ObjectNotFoundException e) {
                StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
                                System.currentTimeMillis());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
        }

        @ExceptionHandler(DataIntegratyException.class)
        public ResponseEntity<StandardError> handlerDataIntegratyException(DataIntegratyException e,
                        HttpServletRequest request) {
                StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
                                System.currentTimeMillis());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<StandardError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e,
                        HttpServletRequest request) {
                ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                                "Erro de validação nos campos", System.currentTimeMillis());

                e.getBindingResult().getFieldErrors()
                                .forEach(x -> validationError.addError(x.getField(), x.getDefaultMessage()));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        @ExceptionHandler(AuthorizationException.class)
        public ResponseEntity<StandardError> handlerAuthorizationException(AuthorizationException e,
                        HttpServletRequest request) {
                StandardError standardError = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(),
                                System.currentTimeMillis());

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
        }

}