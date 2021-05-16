package org.jeycode.service.genericservice;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.mail.MessagingException;

import org.jeycode.models.PlayerFootballMatch;
import org.jeycode.service.components.SendMailImages;
import org.jeycode.utilities.RestServiceUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Scope("prototype")
@RequiredArgsConstructor
@Slf4j
public class SendMailService implements RestServiceUtils
{

      private final JavaMailSender mailSender;
      private final SendMailImages mailImages;

      @Async(EXECUTOR_SEND_MAIL)
      public void sendRegistrationMail(String playerMail,String playerNick)
      {

            log.info("Se inicia servicio de mail para avisar de registro.");
            var message = mailSender.createMimeMessage();
            try
            {
                  var mail = new MimeMessageHelper(message,true);
                  mail.setTo(playerMail);
                  mail.setSubject(MAIL_REGISTRATION_SUBJECT);
                  mail.setText(MAIL_REGISTRATION_HEADER + MAIL_REGISTRATION_BODY_HEADER + playerNick + MAIL_REGISTRATION_BODY
                                          + MAIL_GENERIC_OWNER_INFO,
                               true);
                  addMailFormat(mail);
                  mail.addAttachment(PDF_RULES_NAME,PDF_APPLICATION_GAME_RULES);

                  mailSender.send(message);
                  log.info("Se envío mail de registro a " + playerNick + ".");
            }
            catch (MessagingException ex)
            {
                  log.error("Error al crear el mail de registro.",ex);
            }
      }

      @Async(EXECUTOR_SEND_MAIL)
      public void sendMailInfoStartNewFootballDay(String footballMatch,String[] allPlayerMail)
      {
            if (allPlayerMail.length != 0)
            {

                  try
                  {
                        log.info("Se inicia nueva jornada, se envía aviso a todos los jugadores.");
                        var message = mailSender.createMimeMessage();
                        var mail = new MimeMessageHelper(message,true);
                        mail.setSubject(MAIL_INIT_NEW_FB_DAY_SUBJECT);
                        var now = LocalDateTime.now();
                        mail.setText(MAIL_INIT_NEW_FB_DAY_HEADER + MAIL_INIT_NEW_FB_DAY_BODY_HEADER + footballMatch
                                                + MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE1 + mapOfDayOfWeek.get(now.getDayOfWeek())
                                                + MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE1_1 + now.getHour() + SPACE + HOURS + SPACE + now.getMinute()
                                                + SPACE + MINUTES + MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE2 + MAIL_GENERIC_OWNER_INFO,
                                     true);
                        mail.setTo(allPlayerMail);
                        addMailFormat(mail);
                        mailSender.send(message);
                        log.info("Se envío mail de aviso a todos los usuarios.");
                  }
                  catch (MessagingException ex)
                  {
                        log.error("Error al crear el mail de inicio de jornada.",ex);
                  }
            }
            else
            {
                  log.info("Aún no existen usuarios a los que avisar.");
            }
      }

      @Async(EXECUTOR_SEND_MAIL)
      public void sendSeasonRestartInfoMail(String[] allPlayerMail,String playerWinnerNick)
      {

            
                  try
                  {
                        log.info("Se resetea la temporada, se envía aviso a todos los jugadores.");
                        var message = mailSender.createMimeMessage();
                        var mail = new MimeMessageHelper(message,true);
                        mail.setSubject(MAIL_END_SEASON_SUBJECT);
                        mail.setText(MAIL_END_SEASON_HEADER + MAIL_END_SEASON_BODY_HEADER + playerWinnerNick
                                                + MAIL_END_SEASON_BODY_PREDICATE + MAIL_GENERIC_OWNER_INFO,
                                     true);

                        mail.setTo(allPlayerMail);
                        addMailFormat(mail);

                        mailSender.send(message);
                        log.info("Se envío mail de aviso a todos los usuarios.");
                  }
                  catch (MessagingException ex)
                  {
                        log.error("Error al crear el mail de reseteo de temporada.",ex);
                  }
      }

      @Async(EXECUTOR_SEND_MAIL)
      public void sendEndTheFootballDayMail(List<PlayerFootballMatch> lstOfPlayerFootBallMatch,String[] allPlayerMail)
      {
            log.info("Se inicia servicio de mail para notificar el fin de la jornada.");
            var message = mailSender.createMimeMessage();
            try
            {
                  var mail = new MimeMessageHelper(message,true);
                  mail.setSubject(MAIL_END_FOOTBALLDAY_SUBJECT);
                  Collections.sort(lstOfPlayerFootBallMatch,(plfm,plfmO)-> plfm.getMatchPoints() > plfmO.getMatchPoints() ? -1 : 1);
                  var rowCountHelper = new RowCountHelper();
                  var htmlTableContent = lstOfPlayerFootBallMatch.stream()
                                                                 .map(plfm->
                                                                       {
                                                                             var player = plfm.getPlayer();
                                                                             return htmlTableRow(player,plfm.getMatchPoints(),
                                                                                                 rowCountHelper);
                                                                       })
                                                                 .reduce(new StringBuilder(),StringBuilder::append);
                  mail.setText(MAIL_END_FOOTBALLDAY_HEADER + MAIL_END_FOOTBALLDAY_BODY_HEADER + htmlTableContent.toString()
                                          + MAIL_END_FUTBALLDAY_BODY_END + MAIL_GENERIC_OWNER_INFO,
                               true);
                  mail.setTo(allPlayerMail);
                  addMailFormat(mail);
                  mailSender.send(message);
                  log.info("Se enviaron los mails de notificación, fin de jornada.");
            }
            catch (MessagingException ex)
            {
                  log.error("Error al crear el mail de fin de jornada.",ex);
            }
      }

      @Async(EXECUTOR_SEND_MAIL)
      public CompletableFuture<Boolean> sendLogsToApplicationSupport(String errorNotificationRequest,File fileToSend)
      {
            return CompletableFuture.supplyAsync(()->
                  {
                        log.info("Se inicia servicio de mail para enviar los logs a soporte.");
                        var message = mailSender.createMimeMessage();
                        try
                        {
                              var mail = new MimeMessageHelper(message,true);
                              mail.setTo(MAIL_OWNER);
                              mail.setSubject(MAIL_LOGS_SUPPORT_MSG);
                              mail.setText(errorNotificationRequest,true);
                              var actualDateTime = LocalDateTime.now()
                                                                .toString();
                              log.info("Se añade al correo de soporte los files comprimidos en: " + fileToSend);
                              mail.addAttachment(LOGS_GZIP_NAME + actualDateTime,fileToSend);

                              mailSender.send(message);
                              log.info("Se envió el correo con los logs a soporte.");
                              return true;
                        }
                        catch (MessagingException ex)
                        {
                              var reason = "Error al enviar los logs a soporte.";
                              log.error(reason,ex);
                              throw new ResponseStatusException(HttpStatus.CONFLICT,reason);
                        }
                  });
      }

      public void sendNewRulesNotificationMail(String[] allPlayerMails)
      {
            log.info("Se inicia servicio de mail para notificar nuevas reglas establecidas.");
            var message = mailSender.createMimeMessage();
            try
            {
                  var mail = new MimeMessageHelper(message,true);
                  mail.setSubject(MAIL_NEW_RULES);
                  mail.setText(MAIL_NEW_RULES_HEADER + MAIL_GENERIC_OWNER_INFO,true);
                  mail.setTo(allPlayerMails);
                  addMailFormat(mail);
                  mail.addAttachment(PDF_RULES_NAME,PDF_APPLICATION_GAME_RULES);
                  mailSender.send(message);
                  log.info("Se enviaron los mails de notificación, nuevas reglas.");
            }
            catch (MessagingException ex)
            {
                  log.error("Error al crear el mail de nuevas rerglas.",ex);
            }
      }

      private void addMailFormat(MimeMessageHelper mail) throws MessagingException
      {
            mail.setSentDate(Date.valueOf(LocalDate.now()));
            mail.addInline(CID_GMAIL,mailImages.getGmailResource());
            mail.addInline(CID_YT,mailImages.getYtResource());
            mail.addInline(CID_FACE,mailImages.getFbResource());
            mail.addInline(CID_GITHUB,mailImages.getGhubResource());
            mail.addInline(CID_KEYLOGO,mailImages.getKeylogResource());
      }

}
