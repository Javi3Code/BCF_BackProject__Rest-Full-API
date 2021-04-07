package org.jeycode.execptionsmanaged;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.jeycode.constants.ApplicationConstants;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ApiErrorAttributes extends DefaultErrorAttributes implements ApplicationConstants
{

      @Override
      public Map<String,Object> getErrorAttributes(WebRequest webRequest,ErrorAttributeOptions options)
      {
            var statuscode = super.getErrorAttributes(webRequest,options).get(STATUS);
            var throwable = getError(webRequest);
            var message = getMessage(throwable);
            Map<String,Object> apiErrorAttributes = Map.of(STATUS,HttpStatus.valueOf((int)statuscode),DATE,LocalDateTime.now()
                                                                                                                        .format(FORMATTER_PATTERN),
                                                           MESSAGE,message);
            return apiErrorAttributes;
      }

      private String getMessage(Throwable throwable)
      {
            if (throwable != null)
            {
                  if (throwable instanceof ResponseStatusException)
                  {
                        var responseStatusEx = (ResponseStatusException)throwable;
                        return responseStatusEx.getReason() == null ? EMPTY_STRING : responseStatusEx.getReason();

                  }
                  if (throwable instanceof MethodArgumentNotValidException)
                  {
                        return ((MethodArgumentNotValidException)throwable).getAllErrors()
                                                                           .stream()
                                                                           .map(ObjectError::getDefaultMessage)
                                                                           .collect(Collectors.joining(MSG_ERRS_DELIMITER));
                  }
                  if (throwable instanceof HttpMessageNotReadableException)
                  {
                        return ((HttpMessageNotReadableException)throwable).getMostSpecificCause()
                                                                           .getMessage();
                  }

                  if (throwable instanceof SQLIntegrityConstraintViolationException)
                  {
                        return ((SQLIntegrityConstraintViolationException)throwable).getCause()
                                                                                    .getMessage();
                  }
                  var throwableCause = throwable.getCause();
                  if (throwableCause != null)
                  {
                        var throwableCauseMessage = throwableCause.getMessage();
                        return throwableCauseMessage == null ? EMPTY_STRING : throwableCauseMessage;
                  }
            }
            return EMPTY_STRING;
      }
}
