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

      String RESULT_OK = "Resultado esperado - Json ";
      String REQUEST_TYPE = "Request de tipo ";
      String POST = "POST, ";
      String GET = "GET, ";
      String PUT = "PUT, ";
      String DELETE = "DELETE, ";
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

      String LICENSE_CONTROLLER_TAG = "Controlador para operar con la licencia de la aplicación";
      String GET_LICENSE = "Obtén la licencia del proyecto.";
      String GET_LICENSE_NOTES = REQUEST_TYPE + GET + "sin parámetros.";
      String GET_LICENSE_OK = "Resultado esperado - Resource, el fichero requerido.";

      /*
       * ************ Logs Controller **************
       */

      String LOGS_CONTROLLER_TAG = "Controlador de operaciones con logs";
      String GET_SEND_LOGS = "Envía un mail a soporte con un mensaje y los 5 últimos archivos de log comprimidos.";
      String GET_SEND_LOGS_NOTES = REQUEST_TYPE + GET + "se puede pasar un mensaje que se adhiere al mail-content.";
      String GET_SEND_LOGS_OK = RESULT_OK + "con la confirmación del éxito en la operación.";
      String GET_SEND_LOGS_DESC = "Opcional. String que contiene un mensaje.";

      /*
       * ********** jouney controller ******
       */

      String JOURNEY_CONTROLLER_TAG = "Controlador de operaciones relacionadas con la jornada y sus apuestas";
      String POST_BET_IN = "Inserta la apuesta de un jugador en concreto sobre el partido abierto.";
      String POST_BET_IN_NOTES = REQUEST_TYPE + POST + "requiere de un Json con los datos de la apuesta.";
      String POST_BET_IN_OK = RESULT_OK + "con los datos de la apuesta realizada.";
      String BET_ADD_DESC = "Requerido. Apuesta DTO a ingresar.";
      String BET_ADD_DTO = "Apuesta concreta.";

      String GET_BET = "Obtén la data relacionada con las apuestas sobre el partido actual. Existen tres formatos de data, obtener data de jugadores sin apuesta, obtener data de jugadores con apuesta u obtener todo.";
      String GET_BET_NOTES = REQUEST_TYPE + GET + "se puede pasar un entero que determina el tipo de data a devolver.";
      String GET_BET_OK = RESULT_OK + "con los datos necesarios relacionados a las apuestas o jugadores.";
      String BET_GET_DESC = "Opcional. Determina nivel de datos a obtener. Por defecto es 0 y devuelve una lista de Jugador DTO.";

      String PUT_BET_UP = "Finaliza la jornada y actualiza el partido abierto.";
      String PUT_BET_UP_NOTES = REQUEST_TYPE + PUT + "requiere un Json con el partido abierto ya con el resultado final.";
      String PUT_BET_UP_OK = RESULT_OK + "con los datos del partido finalizado más las apuestas relacionadas.";
      String BET_UP_DESC = "Requerido. PartidoDTO a actualizar.";
      String BET_UP_DTO = "Partido abierto finalizado.";

      /*
       * ******************* concreteMatch Controller ************
       */
      String CONCRETEMATCH_CONTROLLER_TAG = "Controlador de operaciones sobre partidos";

      String POST_CONCRETEMATCH_IN = "Inserta un partido en concreto.";
      String POST_CONCRETEMATCH_IN_NOTES = REQUEST_TYPE + POST + "requiere de un Json con los datos del partido a ingresar.";
      String POST_CONCRETEMATCH_IN_OK = RESULT_OK + "con los datos del partido guardado.";
      String CONCRETEMATCH_ADD_DESC = "Requerido. Partido DTO a ingresar.";
      String CONCRETEMATCH_ADD_DTO = "Partido concreto.";

      String GET_CONCRETEMATCH = "Obtén el único partido abierto.";
      String GET_CONCRETEMATCH_NOTES = REQUEST_TYPE + GET + "no requiere ningún dato.";
      String GET_CONCRETEMATCH_OK = RESULT_OK + "con los datos del partido abierto.";

      String DELETE_CONCRETEMATCH_IN = "Borra el partido abierto o todos los partidos de la database.";
      String DELETE_CONCRETEMATCH_IN_NOTES = REQUEST_TYPE + DELETE
                              + "se puede pasar un boolean para determinar que operación de borrado realizar.";
      String DELETE_CONCRETEMATCH_IN_OK = RESULT_OK + "con un boolean que determina si se ha realizado la operación de borrado.";
      String CONCRETEMATCH_DEL_DESC = "Opcional. Boolean que determina si borrar todos los partidos.";

      /*
       * ******************* players Controller ************
       */

      String PLAYER_CONTROLLER_TAG = "Controlador de operaciones sobre usuarios/jugadores";

      String POST_PLAYER_IN = "Inserta un jugador en la base de datos.";
      String POST_PLAYER_IN_NOTES = REQUEST_TYPE + POST + "requiere de un Json con los datos del jugador a ingresar.";
      String POST_PLAYER_IN_OK = RESULT_OK + "con los datos del jugador insertado.";
      String PLAYER_ADD_DESC = "Requerido. Jugador DTO a insertar.";

      String PUT_PLAYER_UP = "Actualiza los datos de un jugador en concreto.";
      String PUT_PLAYER_UP_NOTES = REQUEST_TYPE + PUT + "requiere de un Json con los datos del jugador a actualizar.";
      String PUT_PLAYER_UP_OK = RESULT_OK + "con los datos del jugador actualizado.";
      String PLAYER_UP_DESC = "Requerido. Jugador DTO a actualizar.";

      String GET_PLAYERS = "Obtén los datos de los jugadores. Existen tres formatos de data obtenida.";
      String GET_PLAYERS_NOTES = REQUEST_TYPE + GET + "se puede pasar un valor entero que determina el formato de datos que se requiere.";
      String GET_PLAYERS_OK_0 = RESULT_OK + "con los datos de los jugadores según el formato por defecto. Nivel - 0.";
      String GET_PLAYERS_OK_1 = "";
      String GET_PLAYERS_OK_2 = "";
      String PLAYERS_GET_DESC = "Opcional. Entero que determina el formato de la data que se quiere obtener.";
      String PLAYER_LEVEL_DATA_NAME = "Nivel de datos";
      String PLAYERS_GET_ALOWABLES_VALUES = "0,1,2";

      String DELETE_PLAYER_IN = "Borra un jugador de la base de datos.";
      String DELETE_PLAYER_IN_NOTES = REQUEST_TYPE + DELETE + "requiere de un Json con los datos del jugador a borrar.";
      String DELETE_PLAYER_IN_OK = RESULT_OK + "con un valor boolean.";
      String PLAYER_DEL_DESC = "Requerido. Jugador DTO a borrar.";

      /*
       * ********************* teams Controller *****************++
       */

      String TEAM_CONTROLLER_TAG = " Controlador de los equipos";

      String POST_TEAM_IN = "Inserta un equipo en la base de datos.";
      String POST_TEAM_IN_NOTES = REQUEST_TYPE + POST + "requiere de un Json con los datos del equipo a insertar.";
      String POST_TEAM_IN_OK = RESULT_OK + "con los datos del equipo insertado.";
      String TEAM_DTO_ADD_DESC = "Requerido. Equipo DTO a insertar.";

      String GET_TEAMS = "Obtén los datos de los equipos almacenados.";
      String GET_TEAMS_NOTES = REQUEST_TYPE + GET + "no requiere parámetros.";
      String GET_TEAMS_OK = RESULT_OK + "con los datos de todos los equipos persistentes.";

      String ALL_PARAM_DESC = "Parámetro no requerido, determina si borrar todos o solo el partido abierto.";
      String DELETE_TEAM_IN = "Borra un equipo en concreto o todos los equipos persistentes.";
      String DELETE_TEAM_IN_NOTES = REQUEST_TYPE + DELETE
                              + ", se pueden pasar dos parámetros opcionales según lo que se quiera realizar. Un boolean para borrar todos y un Json con los datos del equipo a borrar.";
      String DELETE_TEAM_IN_OK = RESULT_OK + ", con un valor boolean.";
      String TEAM_DTO_DEL_DESC = "Parámetro no requerido, Equipo DTO con los datos a borrar.";

      /*
       * ****************************Rules Controller****************************
       */

      String RULES_CONTROLLER_TAG = "Controlador de reglas de la aplicación";

      String GET_RULES_SET = "Obtén las Reglas de puntuación actuales.";
      String GET_RULES_SET_NOTE = REQUEST_TYPE + GET
                              + "no requiere de ningún parámetro. A través de RulesService retornamos los datos de las puntuaciones establecidas actualmente.";
      String RULES_GET_RULES_SET_OK = RESULT_OK + "con los datos de las reglas establecidas actualmente.";

      String UPDATE_RULES = "Actualiza los valores de las reglas de puntuación establecidas.";
      String UPDATE_RULES_NOTE = REQUEST_TYPE + PUT
                              + "requiere de dos parámetros Multipart/form-data. El primero un fichero pdf, el segundo un Json con los datos a actualizar (no es obligatorio).";
      String RULESPDF_DESC = "PDF con las nuevas reglas a establecer";
      String RULESDTO_DESC = "DTO con los nuevos valores de reglas a actualizar.";

      /*
       * ******************** Season Controller******************
       */
      String SEASONS_CONTROLLER_TAG = "Controlador del ciclo de vida de la temporada";
      String PUT_SEASON_INIT = "Resetea la base de datos, eliminando todos los datos irrelevantes para la siguiente temporada, pero manteniendo datos de los jugadores.";
      String PUT_SEASON_INIT_NOTES = REQUEST_TYPE + PUT + "no requiere parámetros. Devuelve si la operación se ha completado con éxito.";
      String PUT_SEASON_INIT_OK = "Resultado esperado - True";
}
