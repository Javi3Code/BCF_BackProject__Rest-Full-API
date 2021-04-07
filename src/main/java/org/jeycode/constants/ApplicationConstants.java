package org.jeycode.constants;

import java.io.File;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

import org.jeycode.models.Player;

public interface ApplicationConstants
{

      String EXECUTOR_SEND_MAIL = "sendMailServiceTaskExecutor";

      /*
       * Strings
       */

      String DOMINGO = "domingo";
      String SÁBADO = "sábado";
      String VIERNES = "viernes";
      String JUEVES = "jueves";
      String MIÉRCOLES = "miércoles";
      String MARTES = "martes";
      String LUNES = "lunes";

      String EMPTY_STRING = "";
      String BURGOS_CF = "Burgos C.F.";

      String RESULT_DELIMITER = "-";
      String VERSUS = " vs ";

      /*
       * Map Data Keys
       */
      String FOOTBALLDAY_PWT_DATA = "playersWithoutResult";
      String FOOTBALLDAY_DT_DATA = "playersDataWithResult";

      /*
       * ApiError
       */
      String MSG_ERRS_DELIMITER = ":err:";
      String MESSAGE = "message";
      String DATE = "date";
      String STATUS = "status";
      String TWO_POINTS = ":";

      /*
       * numbers
       */
      short DFL_RESULT_POINTS = 6;
      short DFL_GOALS_POINTS = 3;
      short DFL_SIGN_POINTS = 1;

      short LOCAL_WINNER = 1;
      short VISITOR_WINNER = 2;
      short TIE = 1;

      /*
       * Mail-const
       */

      String CID_GMAIL = "imgGmail";
      Path GMAIL_IMAGE = Path.of("src\\main\\resources\\image_mail\\gmail.png");
      String CID_KEYLOGO = "imglogo";
      Path KEY_LOG_IMAGE = Path.of("src\\main\\resources\\image_mail\\keylog.png");
      String CID_YT = "imgYT";
      Path YT_IMAGE = Path.of("src\\main\\resources\\image_mail\\youtube.png");
      String CID_FACE = "imgFB";
      Path FACE_IMAGE = Path.of("src\\main\\resources\\image_mail\\face.png");
      String CID_GITHUB = "imgGHub";
      Path GITHUB_IMAGE = Path.of("src\\main\\resources\\image_mail\\github.png");

      String PDF_RULES = "Reglas_del_juego.pdf";
      File PDF_APPLICATION_GAME_RULES = new File("src\\main\\resources\\pdf_files\\Reglas_del_juego.pdf");

      String MAIL_OWNER = "devhelloworld4@gmail.com";

      String MAIL_REGISTRATION_SUBJECT = "Registro completado con éxito.";
      String MAIL_REGISTRATION_HEADER = "<html><h2 align=\"center\">Confirmación de su registro en Porras Burgos C.F. Application</h2>";
      String MAIL_REGISTRATION_BODY_HEADER = "<body align=\"center\"><div><p style=\"font-size:15px;\">Bienvenido ";
      String MAIL_REGISTRATION_BODY = " a la mejor aplicación de porras de tu equipo favorito.<br>Esperamos que disfrutes de la aplicación, puedes ver un pdf adjunto con las reglas, mucha suerte!"
                              + "<br></p></div>";

      String MAIL_INIT_NEW_FB_DAY_SUBJECT = "Se ha iniciado una nueva jornada.";
      String MAIL_INIT_NEW_FB_DAY_HEADER = "<html><h2 align=\"center\">Ha dado comienzo una nueva jornada, hagan sus apuestas!!</h2>";
      String MAIL_INIT_NEW_FB_DAY_BODY_HEADER = "<body align=\"center\"><div><p style=\"font-size:15px;\">El partido ";
      String MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE1 = " ha sido abierto hoy, ";
      String MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE1_1 = ", a las ";
      String MAIL_INIT_NEW_FB_DAY_BODY_PREDICATE2 = ", piense bien su apuesta. Desde Porras Burgos C.F. Application le deseamos lo mejor.<br></p></div>";

      String MAIL_END_SEASON_SUBJECT = "Ha finalizado la temporada.";
      String MAIL_END_SEASON_HEADER = "<html><h2 align=\"center\">Ha finalizado la temporada y se han restablecido los valores predeterminados.</h2>";
      String MAIL_END_SEASON_BODY_HEADER = "<body align=\"center\"><div><p style=\"font-size:15px;\">Enhorabuena al ganador ";
      String MAIL_END_SEASON_BODY_PREDICATE = ". Suerte para el próximo año a los demás...HASTA PRONTO!! ";

      String MAIL_END_FOOTBALLDAY_SUBJECT = "Jornada finalizada";
      String MAIL_END_FOOTBALLDAY_HEADER = "<html><h2 align=\"center\">Ha finalizado la jornada, puedes consultar la clasificación actual y los puntos obtenidos en la jornada en la tabla adjunta.</h2>"
                              + "<h3 align=\"center\"> CLASIFICACIÓN </h3>";
      String MAIL_END_FOOTBALLDAY_BODY_HEADER = "<body align=\"center\"><div border=\"5\" style=\"bordercolor:#155B0E\"><table border=\"3\" style=\"bordercolor:#3B6937\"><thead>"
                              + "<tr bgcolor=\"#90EA87\" valign=\"center\" align=\"center\"><th height=\"30\" width=\"150\" >Nick</th><th height=\"50\" width=\"150\">Puntos</th>"
                              + "<th height=\"50\" width=\"150\">Puntos de la Jornada</th></tr></thead><tbody>";

      String MAIL_END_FUTBALLDAY_BODY_END = "</tbody></table></div>";

      String MAIL_GENERIC_OWNER_INFO = "<br><br><div><p style=\"font-size:10px;\"><i>Aplicación desarrollada por JeyCode, más información en:<br><br>"
                              + "  <a href=\"mailto:j.code.initialize@gmail.com\" style=\"margin-right:20px\" ><img src=\"cid:" + CID_GMAIL
                              + "\" height\"40\" width=\"40\"/></a>"
                              + "<a href=\"https://www.youtube.com/channel/UC5xqFtQ9o3gNIGABG3s3Yyw\" style=\"margin-right:20px\"><img src=\"cid:"
                              + CID_YT + "\" height\"40\" width=\"40\"/></a>"
                              + "<a href=\"https://www.facebook.com/jeycode.cursosdeprogramacion/\" style=\"margin-right:20px\"><img src=\"cid:"
                              + CID_FACE + "\" height=\"40\" width=\"40\"/></a>"
                              + "<a href=\"https://github.com/Javi3Code\" style=\"margin-right:20px\"><img src=\"cid:" + CID_GITHUB
                              + "\" height=\"40\" width=\"40\"/></a><br>" + "</i></p><img src=\"cid:" + CID_KEYLOGO
                              + "\" height=\"180\" width=\"180\"/></div></body></html>";

      /*
       * Formatter
       */
      DateTimeFormatter FORMATTER_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm:ss");

      /*
       * PDF fileservice
       */

      String RULES_PDF_NAME = "Reglas_del_juego";
      String FILE_LOCATION_VAR = "${upload.root-location}";

      String FILE_READ_ERROR = "Falló al leer los ficheros";
      String SERVE_FILE_ERROR = "Error al procesar el fichero";

      class RowCountHelper
      {

            protected boolean isEvenRow = true;
      }

      RowCountHelper rowCountHelper = new RowCountHelper();

      default StringBuilder htmlTableRow(Player player,short matchPoints)
      {
            var isEvenRow = rowCountHelper.isEvenRow;
            var playerNick = player.getPlayerNick();
            var playerTotalPoints = player.getPlayerTotalPoints();
            var bgColor = isEvenRow ? "\"#D8F2D6\"" : "\"#2A3D28\"";
            var fontColor = isEvenRow ? "#0F0F0F\"" : "#D4E8D2\"";
            StringBuilder body = new StringBuilder("<tr bgcolor=" + bgColor + "style=\"color:" + fontColor + ">"
                                    + "<td valign=\"bottom\" align=\"center\">" + playerNick + "</td>"
                                    + "<td valign=\"bottom\" align=\"center\">" + playerTotalPoints + "</td>"
                                    + "<td valign=\"bottom\" align=\"center\">" + matchPoints + "</td></tr>");
            rowCountHelper.isEvenRow = !isEvenRow;
            return body;
      }

}