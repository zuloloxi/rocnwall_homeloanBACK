package fr.bnp.homeloancalculator.exposition.exception;

import fr.bnp.homeloancalculator.domain.exception.MyAppHomeloanSimulationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "fr.bnp.homeloancalculator.exposition")
public class MyExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Set<String> handleSpringSurfaceException(MethodArgumentNotValidException exception) {
        Set<String> surfaceErrors = exception.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toSet());

        LOGGER.debug(surfaceErrors.toString());
        return new TreeSet<>(surfaceErrors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MyAppHomeloanSimulationException.class)
    public Set<String> handleBusinessException(MyAppHomeloanSimulationException exceptions) {
        Set<String> exceptionsErrors = exceptions.getCodeErreurs();

        LOGGER.debug(exceptionsErrors.toString());
        return new TreeSet<>(exceptionsErrors);
    }

}
