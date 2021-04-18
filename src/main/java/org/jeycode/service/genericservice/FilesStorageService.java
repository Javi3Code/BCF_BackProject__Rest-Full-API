package org.jeycode.service.genericservice;

import java.io.File;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.jeycode.constants.ApplicationConstants;
import org.jeycode.constants.ApplicationExceptionUtils;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService extends ApplicationConstants,ApplicationExceptionUtils
{

      String storeNewPdfRules(MultipartFile file);

      Set<File> loadLogFiles();

      void deleteAllLogs();

      CompletableFuture<Boolean> prepareLogsPackage();

      boolean deleteTempDir();

      File getFileToSend();

}