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
       * ***************** PARAM Names ***********++
       */

      String RULES_DTO = "RulesDto";

      String LOGS_REQUEST_DEFAULT_MSG_NAME = "";

      /*
       * ****************************Type of Produces or Consumes***************+
       */

      String COMMA = ", ";

      /*
       * ********** License Controller *******
       */

      String LICENSE_CONTROLLER_TAG = "";
      String GET_LICENSE = "";
      String GET_LICENSE_NOTES = "";
      String GET_LICENSE_OK = "";
      

      /*
       * ************ Logs Controller **************
       */

      String LOGS_CONTROLLER_TAG = "";
      String GET_SEND_LOGS = "";
      String GET_SEND_LOGS_NOTES = "";
      String GET_SEND_LOGS_OK = "";
      String GET_SEND_LOGS_DESC = "";
      /*
       * ******************* concreteMatch Controller ************
       */

      String POST_CONCRETEMATCH_IN = "";
      String POST_CONCRETEMATCH_IN_NOTES = "";
      String POST_CONCRETEMATCH_IN_OK = "";
      String CONCRETEMATCH_ADD_DESC = "";
      String CONCRETEMATCH_ADD_DTO = "Partido concreto";

      String GET_CONCRETEMATCH = "";
      String GET_CONCRETEMATCH_NOTES = "";
      String GET_CONCRETEMATCH_OK = "";

      String DELETE_CONCRETEMATCH_IN = "";
      String DELETE_CONCRETEMATCH_IN_NOTES = "";
      String DELETE_CONCRETEMATCH_IN_OK = "";
      String CONCRETEMATCH_DEL_DESC = "";

      /*
       * ******************* players Controller ************
       */

      String PLAYER_CONTROLLER_TAG = "";

      String POST_PLAYER_IN = "";
      String POST_PLAYER_IN_NOTES = "";
      String POST_PLAYER_IN_OK = "";
      String PLAYER_ADD_DESC = "";

      String PUT_PLAYER_UP = "";
      String PUT_PLAYER_UP_NOTES = "";
      String PUT_PLAYER_UP_OK = "";
      String PLAYER_UP_DESC = "";

      String GET_PLAYERS = "";
      String GET_PLAYERS_NOTES = "";
      String GET_PLAYERS_OK_0 = "";
      String GET_PLAYERS_OK_1 = "";
      String GET_PLAYERS_OK_2 = "";
      String PLAYERS_GET_DESC = "";
      String PLAYER_LEVEL_DATA_NAME = "Nivel de datos";
      String PLAYERS_GET_ALOWABLES_VALUES = "0,1,2";

      String DELETE_PLAYER_IN = "";
      String DELETE_PLAYER_IN_NOTES = "";
      String DELETE_PLAYER_IN_OK = "";
      String PLAYER_DEL_DESC = "";

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
