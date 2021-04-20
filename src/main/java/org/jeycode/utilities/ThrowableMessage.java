package org.jeycode.utilities;

@FunctionalInterface
public interface ThrowableMessage
{

      String getErrorMessage(Throwable throwable);
}
