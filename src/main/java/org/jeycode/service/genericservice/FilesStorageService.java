package org.jeycode.service.genericservice;

import java.io.File;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.jeycode.utilities.ApplicationConstants;
import org.jeycode.utilities.ApplicationExceptionUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService extends ApplicationConstants,ApplicationExceptionUtils
{

      void storeNewPdfRules(MultipartFile file);

      Set<File> loadLogFiles();

      void deleteAllLogs();

      CompletableFuture<Boolean> prepareLogsPackage();

      boolean deleteTempDir();

      File getFileToSend();

      ResponseEntity<Resource> serveApplicationLicenseFile(HttpServletRequest request);
}