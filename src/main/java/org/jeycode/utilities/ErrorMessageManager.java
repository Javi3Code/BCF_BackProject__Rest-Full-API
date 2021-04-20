package org.jeycode.utilities;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;

public interface ErrorMessageManager extends ApplicationConstants
{

      default String getErrorMessage(Throwable throwable)
      {
            return mapOfErrorMessage.get(throwable.getClass())
                                    .getErrorMessage(throwable);
      }

      Map<Class<? extends Exception>,ThrowableMessage> mapOfErrorMessage = Map.of(ResponseStatusException.class,getRSExcepMessage(),
                                                                                  MethodArgumentNotValidException.class,
                                                                                  getMethArgNVExcepMessage(),
                                                                                  HttpMessageNotReadableException.class,
                                                                                  getMessNRExcepMessage(),
                                                                                  SQLIntegrityConstraintViolationException.class,
                                                                                  getSQLICVExcepMessage(),
                                                                                  HttpMediaTypeNotSupportedException.class,
                                                                                  getHttpMTNSExcepMessage(),NullPointerException.class,
                                                                                  getNPExcepMessage(),
                                                                                  MissingServletRequestPartException.class,
                                                                                  getMSRPExcepMessage());

      static ThrowableMessage getRSExcepMessage()
      {
            return throwable->
                  {
                        var responseStatusEx = (ResponseStatusException)throwable;
                        return responseStatusEx.getReason() == null ? EMPTY_STRING : responseStatusEx.getReason();

                  };
      }

      static ThrowableMessage getMSRPExcepMessage()
      {
            return throwable-> (((MissingServletRequestPartException)throwable).getRequestPartName()
                                    + " falta por añadir en los parámetros y es requerido para realizar la petición.");
      }

      static ThrowableMessage getNPExcepMessage()
      {
            return throwable-> "Se deben pasar los parámetros requeridos.";
      }

      static ThrowableMessage getHttpMTNSExcepMessage()
      {
            return throwable-> ((HttpMediaTypeNotSupportedException)throwable).getSupportedMediaTypes()
                                                                              .stream()
                                                                              .map(MediaType::toString)
                                                                              .collect(Collectors.joining(MSG_ERRS_DELIMITER));
      }

      static ThrowableMessage getSQLICVExcepMessage()
      {
            return throwable-> ((SQLIntegrityConstraintViolationException)throwable).getCause()
                                                                                    .getMessage();
      }

      static ThrowableMessage getMessNRExcepMessage()
      {
            return throwable-> ((HttpMessageNotReadableException)throwable).getMostSpecificCause()
                                                                           .getMessage();
      }

      static ThrowableMessage getMethArgNVExcepMessage()
      {
            return throwable-> ((MethodArgumentNotValidException)throwable).getAllErrors()
                                                                           .stream()
                                                                           .map(ObjectError::getDefaultMessage)
                                                                           .collect(Collectors.joining(MSG_ERRS_DELIMITER));
      }
}
