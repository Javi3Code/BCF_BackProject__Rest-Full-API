package org.jeycode.execptionsmanaged;

import org.springframework.http.HttpStatus;

public class UniqueConstraintException extends GenericBackendException
{

      private static final long serialVersionUID = 5426864135829579021L;

      public UniqueConstraintException(String message)
      {
            super(HttpStatus.CONFLICT,message);
      }

}
