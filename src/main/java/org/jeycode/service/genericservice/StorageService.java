package org.jeycode.service.genericservice;

import java.io.File;
import java.util.Set;

import org.jeycode.constants.ApplicationConstants;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService extends ApplicationConstants
{

      String storeNewPdfRules(MultipartFile file);

      Set<File> loadLogFiles();

      void deleteAllLogs();

}