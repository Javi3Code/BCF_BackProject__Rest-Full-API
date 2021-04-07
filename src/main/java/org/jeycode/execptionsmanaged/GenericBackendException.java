package org.jeycode.execptionsmanaged;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class GenericBackendException extends RuntimeException
{

      @Getter
      private final HttpStatus status;

      public GenericBackendException(HttpStatus status,String message)
      {
            super(message);
            this.status = status;
      }

      private static final long serialVersionUID = -8825977780562123120L;

}
