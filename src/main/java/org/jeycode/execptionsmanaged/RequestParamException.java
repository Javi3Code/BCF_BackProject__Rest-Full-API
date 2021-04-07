package org.jeycode.execptionsmanaged;

import org.springframework.http.HttpStatus;

public class RequestParamException extends GenericBackendException
{

      private static final long serialVersionUID = -7640551355509170170L;

      public RequestParamException(String message)
      {
            super(HttpStatus.BAD_REQUEST,message);
      }

}
