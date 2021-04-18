package org.jeycode.service.components;

import java.net.MalformedURLException;

import org.jeycode.constants.ApplicationConstants;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
public class SendMailImages implements ApplicationConstants
{

      private Resource keylogResource;
      private Resource gmailResource;
      private Resource ytResource;
      private Resource fbResource;
      private Resource ghubResource;

      public SendMailImages()
      {
            try
            {
                  keylogResource = new UrlResource(KEY_LOG_IMAGE.toUri());
                  gmailResource = new UrlResource(GMAIL_IMAGE.toUri());
                  ytResource = new UrlResource(YT_IMAGE.toUri());
                  fbResource = new UrlResource(FACE_IMAGE.toUri());
                  ghubResource = new UrlResource(GITHUB_IMAGE.toUri());

            }
            catch (MalformedURLException ex)
            {
                  log.error("No se cargaron las imagenes de utilidades correctamente");
            }
      }
}
