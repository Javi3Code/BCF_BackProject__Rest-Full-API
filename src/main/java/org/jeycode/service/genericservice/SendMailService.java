package org.jeycode.service.genericservice;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;

import org.jeycode.models.PlayerFootballMatch;
import org.jeycode.service.genericservice.utils.RestServiceUtils;
import org.jeycode.service.genericservice.utils.SendMailImages;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMailService implements RestServiceUtils
{

      private final JavaMailSender mailSender;
      private final SendMailImages mailImages;

      @Async(EXECUTOR_SEND_MAIL)
      public void sendRegistrationMail(String playerMail,String playerNick)
      {

            log.info("Se inicia servicio de mail para avisar de registro");
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
                  mail.addAttachment(PDF_RULES,PDF_APPLICATION_GAME_RULES);

                  mailSender.send(message);
                  log.info("Se envío mail de registro a " + playerNick);
            }
            catch (MessagingException ex)
            {
                  log.error("Error al crear el mail de registro");
            }
      }

      @Async(EXECUTOR_SEND_MAIL)
      public void sendMailInfoStartNewFootballDay(String footballMatch,String[] allPlayerMail)
      {
            if (allPlayerMail.length != 0)
            {

                  try
                  {
                        log.info("Se inicia nueva jornada, se envía aviso a todos los jugadores ");
                        var message = mailSender.createMimeMessage();
                        var mail = new MimeMessageHelper(message,true);
                        mail.setSubject(MAIL_INIT_NEW_FB_DAY_SUBJECT);
                        var now = LocalDateTime.now();
                        mail.setText(MAIL_INIT_NEW_FB_DAY_HEADER + MAIL_INIT_NEW_FB_DAY_BODY_HEADER + footballMatch
                                                + MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE1 + mapOfDayOfWeek.get(now.getDayOfWeek())
                                                + MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE1_1 + now.getHour() + TWO_POINTS + now.getMinute()
                                                + MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE2 + MAIL_GENERIC_OWNER_INFO,
                                     true);
                        mail.setTo(allPlayerMail);
                        addMailFormat(mail);

                        mailSender.send(message);
                        log.info("Se envío mail de aviso a todos los usuarios");
                  }
                  catch (MessagingException ex)
                  {
                        log.error("Error al crear el mail de inicio de jornada");
                  }
            }
            else
            {
                  log.info("Aún no existen usuarios a los que avisar");
            }
      }

      @Async(EXECUTOR_SEND_MAIL)
      public void sendSeasonRestartInfoMail(String[] allPlayerMail,String playerWinnerNick)
      {

            if (allPlayerMail.length != 0)
            {
                  try
                  {
                        log.info("Se resetea la temporada, se envía aviso a todos los jugadores");
                        var message = mailSender.createMimeMessage();
                        var mail = new MimeMessageHelper(message,true);
                        mail.setSubject(MAIL_END_SEASON_SUBJECT);
                        mail.setText(MAIL_END_SEASON_HEADER + MAIL_END_SEASON_BODY_HEADER + playerWinnerNick
                                                + MAIL_END_SEASON_BODY_PREDICATE + MAIL_GENERIC_OWNER_INFO,
                                     true);

                        mail.setTo(allPlayerMail);
                        addMailFormat(mail);

                        mailSender.send(message);
                        log.info("Se envío mail de aviso a todos los usuarios");
                  }
                  catch (MessagingException ex)
                  {
                        log.error("Error al crear el mail de reseteo de temporada");
                  }
            }
            else
            {
                  log.info("Aún no existen usuarios a los que avisar");
            }
      }

      @Async(EXECUTOR_SEND_MAIL)
      public void sendEndTheFootballDayMail(List<PlayerFootballMatch> lstOfPlayerFootBallMatch,String[] allPlayerMail)
      {
            log.info("Se inicia servicio de mail para notificar el fin de la jornada");
            var message = mailSender.createMimeMessage();
            try
            {
                  var mail = new MimeMessageHelper(message,true);
                  mail.setSubject(MAIL_END_FOOTBALLDAY_SUBJECT);
                  Collections.sort(lstOfPlayerFootBallMatch,(plfm,plfmO)-> plfm.getMatchPoints() > plfmO.getMatchPoints() ? -1 : 1);
                  var htmlTableContent = lstOfPlayerFootBallMatch.stream()
                                                                 .map(plfm->
                                                                       {
                                                                             var player = plfm.getPlayer();
                                                                             return htmlTableRow(player,plfm.getMatchPoints());
                                                                       })
                                                                 .reduce(new StringBuilder(),StringBuilder::append);
                  mail.setText(MAIL_END_FOOTBALLDAY_HEADER + MAIL_END_FOOTBALLDAY_BODY_HEADER + htmlTableContent.toString()
                                          + MAIL_END_FUTBALLDAY_BODY_END + MAIL_GENERIC_OWNER_INFO,
                               true);
                  mail.setTo(allPlayerMail);
                  addMailFormat(mail);
                  mailSender.send(message);
                  log.info("Se enviaron los mails de notificación, fin de jornada");
            }
            catch (MessagingException ex)
            {
                  log.error("Error al crear el mail de fin de jornada");
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
