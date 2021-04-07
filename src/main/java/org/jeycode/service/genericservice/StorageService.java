package org.jeycode.service.genericservice;

import java.nio.file.Path;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.jeycode.constants.ApplicationConstants;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService extends ApplicationConstants
{

      void init();

      String store(MultipartFile file);

      Stream<Path> loadAll();

      Path load(String filename);

      Resource loadAsResource(String filename);

      void delete(String filename);

      void deleteAll();

      ResponseEntity<Resource> serveFile(String filename,HttpServletRequest request);

}