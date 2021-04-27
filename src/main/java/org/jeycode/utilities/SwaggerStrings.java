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
      int CODE_400 = 400;
      int CODE_409 = 409;
      int CODE_415 = 415;
      int CODE_500 = 500;

      /*
       * **********************Generic Response Message*************************
       */

      String UPDATE_OK_MSG = "La request con un resultado positivo devuelve un json con los datos post actualización.";

      /*
       * ************Error responses*************
       */

      String DEFAULT_ERROR_JSON_RESPONSE = " - Resultado inesperado - Json con mensaje de error, fecha más estado de error.";

      String ERR_500 = "Error en el Servidor";
      String ERR_400 = "Petición mal realizada";
      String ERR_409 = "Conflicto con campos únicos";
      String ERR_415 = "Petición mal realizada - Media_Type pasado no soportado";

      /*
       * ***************** Dto Names ***********++
       */

      String RULES_DTO = "RulesDto";

      /*
       * ****************************Type of Produces or Consumes***************+
       */

      String COMMA = ", ";

      /*
       * ********************* teams Controller *****************++
       */

      String TEAM_CONTROLLER_TAG = "";

      String POST_TEAM_IN = "";
      String POST_TEAM_IN_NOTES = "";
      String POST_TEAM_IN_OK = "";
      String TEAM_DTO_ADD_DESC = "";

      String GET_TEAMS = "";
      String GET_TEAMS_NOTES = "";
      String GET_TEAMS_OK = "";

      String ALL_PARAM_DESC = "";
      String DELETE_TEAM_IN = "";
      String DELETE_TEAM_IN_NOTES = "";
      String DELETE_TEAM_IN_OK = "";
      String TEAM_DTO_DEL_DESC = "";

      /*
       * ****************************Rules Controller****************************
       */

      String RULES_CONTROLLER_TAG = "Controlador de reglas de la aplicación";

      String GET_RULES_SET = "Obtener las Reglas de puntuación actuales";
      String GET_RULES_SET_NOTE = "Request de tipo GET, no requiere de ningún parámetro. A través de RulesService retornamos los datos de las puntuaciones establecidas actualmente.";
      String RULES_GET_RULES_SET_OK = "Resultado esperado - Json con los datos de las reglas establecidas actualmente.";

      String UPDATE_RULES = "Actualizar los valores de las reglas de puntuación establecidas.";
      String UPDATE_RULES_NOTE = "Request de tipo PUT, requiere de dos parámetros Multipart/form-data. El primero un fichero pdf";
      String RULESPDF_DESC = "PDF con las nuevas reglas a establecer";
      String RULESDTO_DESC = "DTO con los nuevos valores de reglas a actualizar.";

      /*
       * ******************** Season Controller******************
       */
      String SEASONS_CONTROLLER_TAG = "Controlador del ciclo de vida de la temporada";
      String PUT_SEASON_INIT = "";
      String PUT_SEASON_INIT_NOTES = "";
      String PUT_SEASON_INIT_OK = "";
}
