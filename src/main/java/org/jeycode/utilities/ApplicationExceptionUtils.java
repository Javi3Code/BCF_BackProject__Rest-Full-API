package org.jeycode.utilities;

public interface ApplicationExceptionUtils
{

      String STORE_PDF_ERR = "Error al guardar el pdf de reglas actuales. Imposible actualizar las reglas. ";
      String NOT_VALID_EXT_PDF = "El fichero no tiene una extensión válida(.pdf). ";
      String PART_PDF_NO_NULL_OR_EMPTY = "Falta el pdf adjunto con las reglas. ";

      String LOGS_REQUEST_DEFAULT_MSG = "Error en la aplicación, revisar los logs. ";
      String LOGS_EMPTY = "No existen logs a enviar. ";
      String COPY_LOGS_ERROR = "Ocurrió un error al intentar copiar los logs al directorio de compresión. ";
      String SEND_LOGS_ERROR = "Error en el envío de logs a soporte. ";
      String CREATE_DIR_LOGZIP_ERR = "No se pudo crear el directorio para comprimir los logs. ";
      String COMPRESSOR_ERROR_MSG = "No se pudieron comprimir los ficheros de log a enviar. ";
      String DELETE_TEMP_DIR_COMPRESSION = "Error al borrar el directorio temporal usado en la compresión. ";
      /*
       * ============================Exception's message===========================
       * 
       */

      String NOT_FOUND_MSG = " no se encuentra. ";
      String BAD_REQUEST_MSG = ", la petición no se ha realizado correctamente. ";

      String UP_RULES_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de actualizar las reglas. ";
      String GET_RULES_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de obtener las reglas. ";
      String UPDATE_RULES_NOT_VALID = "Estas reglas no se pueden establecer. Los puntos por resultado deben ser mayores al resto y los puntos por goles del Burgos C.F. deben ser mayores a los puntos por signo. ";

      String INSERT_TEAM_PK_VIOLATED = "El equipo ya se encontraba guardado en la base de datos, no puede haber dos equipos con el mismo nombre. ";
      String UP_TEAM_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de actualizar el equipo. ";
      String GET_TEAM_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de obtener el equipo. ";
      String GET_TEAMS_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de obtener los equipos. ";
      String INSERT_TEAM_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de añadir el equipo. ";
      String DELETE_TEAM_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de borrar el equipo. ";
      String DELETE_TEAMS_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de borrar los equipos. ";
      String DELETE_TEAMS_REQUEST_PARAM_CONFLICT = "Conflicto de intereses, se está tratando de borrar todos los equipos y a su vez se pasa un equipo a borrar, asegurese de realizar la operación correctamente. ";
      String DELETE_TEAM_REQUEST_PARAM_NULL = "El equipo a borrar no puede ser nulo. ";
      String DELETE_TEAM_REQUEST_PARAM_NOT_EQ_BCF = "El equipo a borrar no puede ser el Burgos C.F. ";
      String DELETE_TEAM_REQUEST_PARAM_NOT_EXISTS = "El equipo a borrar no existe. ";

      String INSERT_OR_UPDATE_PLAYER_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de añadir o actualizar el jugador. ";
      String INSERT_PLAYER_SERV_NICK_REPEATED = "Nick no válido, ya existe un usuario con ese mismo nick. ";
      String UPDATE_PLAYER_SERV_NOT_EXISTS = "Imposible actualizar el jugador, no existe ningún jugador con esa id. ";
      String UPDATE_PLAYER_SERV_NICK_REPEATED = "Imposible actualizar el jugador, ya existe un usuario con ese mismo nick. ";
      String GET_PLAYERS_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de obtener los jugadores. ";
      String GET_PLAYERS_PARAM_LEVEL_NOT_VALID = "El parámetro de nivel de datos(level) no es válido, solo se aceptan 0, 1 y 2. ";
      String UPDATE_PLAYERS_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de resetear los jugadores. ";
//      String INSERT_OR_UPDATE_PLAYER_REQUEST_PARAM_NULL = "El jugador a insertar o actualizar no puede ser nulo. ";
      String DELETE_PLAYER_REQUEST_NO_EXISTS = "El jugador que se requiere borrar no existe. ";
//      String DELETE_PLAYER_REQUEST_PARAM_NULL = "El jugador a borrar no puede ser nulo. ";

      String INSERT_CONCRETEMATCH_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de añadir un partido. ";
      String INSERT_CONCRETEMATCH_THERE_IS_AN_OPEN_MATCH = "Ya existe un partido abierto. ";
      String INSERT_CONCRETEMATCH_WAS_PLAYED = "No es posible guardar este partido porque ya se jugó. ";
      String INSERT_CONCRETEMATCH_TEAMS_ARE_EQUALS = "No puede enfrentarse un equipo consigo mismo. ";
      String INSERT_CONCRETEMATCH_TEAMS_NO_BCF = "Uno de los dos equipos debe ser el Burgos. ";
      String INSERT_CONCRETEMATCH_TEAMS_NO_EXISTS = " no es un equipo participante, no se pudo crear el partido. ";
      String GET_OPEN_CONCRETEMATCH_NOT_EXISTS = "Aún no existe ningún partido abierto. ";
      String GET_OPEN_CONCRETEMATCH_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al tratar de obtener el partido abierto. ";
      String DELETE_OPEN_CONCRETEMATCH_NOT_EXISTS = "No existe ningún partido abierto a borrar. ";
      String DELETE_CONCRETEMATCH_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado en la operación de borrado. ";

      String INSERT_PLFM_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al intentar insertar una porra. ";
      String INSERT_PLFM_FORBIDDEN = "Imposible agregar una apuesta ya que aún no existe un partido abierto. ";
      String INSERT_PLFM_PLAYERNICK_NOT_EXISTS = "El nick del jugador asociado a la apuesta no es válido. ";
      String INSERT_PLFM_PLAYER_HAS_RESULT = " ya tiene una apuesta asociada a este partido. ";
      String GET_PLFM_DATA_FORBIDDEN = "Imposible obtener los datos de la jornada ya que aún no existe un partido abierto. ";
      String GET_PLFM_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al intentar obtener los datos. ";
      String UPDATE_FOOTBALLDAY_CM_NOT_VALID = "El partido a actualizar no coincide con el único partido abierto. ";
      String END_FOOTBALLDAY_CM_NOT_VALID = "Para dar por finalizada la jornada deben agregarse todas las apuestas. ";
      String UPDATE_FOOTBALLDAY_SERV_UNKNOWN_ERROR = "Ocurrió un error no identificado al intentar finalizar la jornada. ";

      /*
       * ============================Validation's message==========================
       * 
       */

      // Generic Message
      String NONE_CAN_BE_NULL = "Ninguno de los valores pasados puede ser null.";
      String NON_VALID_ID_NULL = "La id recibida no puede ser nula";
      String NON_VALID_ID_VALUE = "La id recibida no puede ser un valor negativo o 0.";
      String MAIL_REGEXP = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*";
      String MATCH_RESULT_REGEXP = "^((20)|20|((1\\d|[0-9])(1\\d{1,2})?))\\-+((20)|20|((1\\d|[0-9])(1\\d{1,2})?))$";

      // Especific message

      String RULES_POINTS_NON_VALID = "El mínimo a puntuar por categoría es de 0 puntos.";

      String TEAMNAME_NON_NULL = "El nombre del equipo no puede ser nulo.";
      String TEAMNAME_NON_BLANK = "El nombre del equipo no puede estar en blanco.";
      String TEAMNAME_NON_VALID_SIZE = "Sólo se aceptan 60 caracteres máximo para el nombre de un equipo, mínimo 3 para identificarlo.";

      String PLAYERNAME_NON_NULL = "El nick del jugador no puede ser nulo";
      String PLAYERNAME_NON_BLANK = "El nick del jugador no puede estar en blanco.";
      String PLAYERNAME_NON_VALID_SIZE = "Sólo se aceptan 20 caracteres máximo para el nick de un jugador, mínimo 1 para identificarlo.";
      String PLAYERMAIL_NON_NULL = "El email del jugador no puede ser nulo.";
      String PLAYERMAIL_NON_BLANK = "El email del jugador no puede estar en blanco.";
      String PLAYERMAIL_NON_VALID_PATTERN = "El email del jugador debe tener un formato de email válido.";
      String PLAYERMAIL_NON_VALID_SIZE = " El mail puede contener 50 caracteres máximo.";

      String MATCHRESULT_NON_NULL = "El resultado a actualizar no puede ser nulo.";
      String MATCHRESULT_NON_BLANK = "El resultado a actualizar no puede estar en blanco";
      String MATCHRESULT_NON_VALID_PATTERN = "El formato válido para incluir el resultado debe ser [goles(0-20)][-][goles(0-20)].";

      String PFM_PLAYERNICK_CANT_BE_NULL = "El nick del jugador no puede ser nulo.";
      String PFM_PLAYERNICK_CANT_BE_BLANK = "El nick del jugador no puede estar en blanco y debe ser un nick valido.";

      String PFMPLAYER_CANT_BE_NULL = "El jugador referenciado no puede ser nulo.";
      String PFMCONCMATCH_CANT_BE_NULL = "El partido referenciado no puede ser nulo.";
      String PFMSIGN_NON_VALID = "Los únicos valores aceptados para el signo del partido son: 1(Local), 2(Visitante) y 0(Empate).";
      String PFMSIGN_CANT_BE_NULL = "El signo no puede ser nulo.";
      String PFMBCFGOALS_NON_VALID = "El número de goles del Burgos debe estar entre 0-20.";
      String PFMBCFGOALS_CANT_BE_NULL = "El número de goles del Burgos no puede ser nulo.";
      String PFMPOINTS_NON_VALID = "Los puntos de partido introducidos no son válidos.";
      String PFMPOINTS_CANT_BE_NULL = "El campo de los puntos conseguidos no puede ser nulo.";

      String FDSERVICE_PARAM_TYPE_OF_DATA_NOT_VALID = "El atributo tipo de data sólo acepta 0, 1 y 2 como valores.";
      String PLSERVICE_PARAM_DTOLEVEL_NOT_VALID = "El atributo nivel de datos(level) sólo acepta 0, 1 y 2 como valores.";

      String FILESERVICE_LICENSE_NOT_FOUND = "No se ha encontrado el archivo de la licencia de la aplicación.";

}
