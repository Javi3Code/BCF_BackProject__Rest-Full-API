package org.jeycode.execptionsmanaged;

@FunctionalInterface
public interface ThrowableMessage
{

      String getErrorMessage(Throwable throwable);
}
