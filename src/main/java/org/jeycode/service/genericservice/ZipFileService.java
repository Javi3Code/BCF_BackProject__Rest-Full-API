package org.jeycode.service.genericservice;

import org.jeycode.constants.ApplicationConstants;
import org.jeycode.constants.ApplicationExceptionUtils;

public interface ZipFileService extends ApplicationConstants, ApplicationExceptionUtils
{

      boolean compress();

      boolean deleteTempDir();
}
