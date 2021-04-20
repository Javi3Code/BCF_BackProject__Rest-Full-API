package org.jeycode.execptionsmanaged;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiErrorAttributes extends DefaultErrorAttributes implements ErrorMessageManager
{

      @Override
      public Map<String,Object> getErrorAttributes(WebRequest webRequest,ErrorAttributeOptions options)
      {
            log.error("Ha ocurrido un error, obteniendo atributos y mensajes.");
            var statuscode = super.getErrorAttributes(webRequest,options).get(STATUS);
            log.error("Status: " + statuscode);
            var throwable = getError(webRequest);
            log.error("Throwable: " + throwable);
            var message = getMessage(throwable);
            log.error("Message: " + message);
            var dateFormatted = LocalDateTime.now()
                                             .format(FORMATTER_PATTERN);
            Map<String,Object> apiErrorAttributes = Map.of(STATUS,HttpStatus.valueOf((int)statuscode),DATE,dateFormatted,MESSAGE,message);
            return apiErrorAttributes;
      }

      private String getMessage(Throwable throwable)
      {
            if (throwable != null)
            {
                  var message = getErrorMessage(throwable);
                  if (message != null)
                  {
                        log.error("Mensaje a devolver: " + message);
                        return message;
                  }
                  var throwableCause = throwable.getCause();
                  if (throwableCause != null)
                  {
                        var throwableCauseMessage = throwableCause.getMessage();
                        log.error("Mensaje a devolver: " + throwableCauseMessage);
                        return throwableCauseMessage == null ? EMPTY_STRING : throwableCauseMessage;
                  }
            }
            return EMPTY_STRING;
      }
}
