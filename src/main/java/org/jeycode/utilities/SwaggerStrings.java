package org.jeycode.utilities;

public interface SwaggerStrings
{

      String LICENSE_URL = "http://localhost:8080/license";
      String LICENSE_NAME = "GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007";
      String API_REST_VERSION = "1.0";
      String API_REST_TITLE = "Burgos C.F. Full Rest Api";
      String AUTHOR_URL_YT = "https://www.youtube.com/channel/UC5xqFtQ9o3gNIGABG3s3Yyw";
      String AUTHOR_MAIL = "j.code.initialize@gmail.com";
      String AUTHOR = "Javier Pérez Alonso";
      String API_DESCRIPTION = "Buenas Coders, esta API Rest conforma el Backend para las aplicaciones de desktop, mobile o web realizadas por Jey Code para la gestión"
                              + " de porras legales y no lucrativas de grupos o peñas asociadas al Burgos C.F, sobre el cual gira la aplicación.";

      /*
       * *********************************Code responses****************************
       */

      int CODE_200 = 200;
      int CODE_500 = 500;

      /*
       * ****************************Type of Produces or Consumes***************+
       */

      String Json = "Application/Json";
      String MultipartFile = "multipart/form-data";
      String COMMA = ", ";

      /*
       * ****************************Rules Controller****************************
       */

      String RULES_CONTROLLER_TAG = "Controlador de reglas de la aplicación";

      String GET_RULES_SET = "Obtener las Reglas de puntuación actuales";
      String GET_RULES_SET_NOTE = "Request de tipo GET, no requiere de ningún parámetro. A través de RulesService retornamos los datos de las puntuaciones establecidas actualmente.";
      String RULES_GET_RULES_SET_OK = "Resultado esperado - Json con los datos de las reglas establecidas actualmente.";

      /*
       * ************Error responses*************
       */

      String DEFAULT_ERROR_JSON_RESPONSE = "Resultado inesperado - Json con mensaje de error, fecha más estado de error.";

      String ERR_500 = "Error en el Servidor - ";

}
