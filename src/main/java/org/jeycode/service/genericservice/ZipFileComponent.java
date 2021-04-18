package org.jeycode.service.genericservice;

import java.io.File;

import org.jeycode.constants.ApplicationConstants;
import org.jeycode.constants.ApplicationExceptionUtils;

public interface ZipFileComponent extends ApplicationConstants, ApplicationExceptionUtils
{

      boolean compress();

      boolean deleteTempDir();

      File getFileToSend();
}
