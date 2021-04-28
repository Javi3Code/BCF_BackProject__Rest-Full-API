package org.jeycode.utilities;

import java.time.DayOfWeek;
import java.util.Map;

import org.jeycode.dtos.playerdto.AbstractPlayerDto;
import org.jeycode.mappers.PlayerMapper;
import org.jeycode.models.ConcreteMatch;
import org.jeycode.models.Player;

public interface RestServiceUtils extends ApplicationExceptionUtils,ApplicationConstants
{

      public enum PlayerDto
      {

       SIMPLE
       {

             @Override
             public AbstractPlayerDto map(PlayerMapper mapper,Player player)
             {
                   return mapper.toSimplePlayerDto(player);
             }
       },
       SIMPLE_WITH_ID
       {

             @Override
             public AbstractPlayerDto map(PlayerMapper mapper,Player player)
             {
                   return mapper.toSimplePlayerDtoWithId(player);
             }
       },
       COMPLETE
       {

             @Override
             public AbstractPlayerDto map(PlayerMapper mapper,Player player)
             {
                   return mapper.toCompletePlayerDto(player);
             }
       };

            public abstract AbstractPlayerDto map(PlayerMapper mapper,Player player);
      }

      Map<DayOfWeek,String> mapOfDayOfWeek = Map.of(DayOfWeek.MONDAY,LUNES,DayOfWeek.TUESDAY,MARTES,DayOfWeek.WEDNESDAY,MIÉRCOLES,
                                                    DayOfWeek.THURSDAY,JUEVES,DayOfWeek.FRIDAY,VIERNES,DayOfWeek.SATURDAY,SÁBADO,
                                                    DayOfWeek.SUNDAY,DOMINGO);

      public enum FootballDayTypeOfData
      {

       PLAYERS_WITHOUT_RESULT,
       PLAYERS_WITH_RESULT_DATA_TABLE,
       ALL_DATA

      }

      default short getSign(short localGoals,short visitorGoals)
      {
            return localGoals > visitorGoals ? LOCAL_WINNER : localGoals == visitorGoals ? TIE : VISITOR_WINNER;
      }

      default short getBcfGoals(ConcreteMatch openConcreteMatch,short localGoals,short visitorGoals)
      {
            var bcfIsTheLocalTeam = openConcreteMatch.getLocalTeam()
                                                     .equals(BURGOS_CF);
            short bcfGoals = bcfIsTheLocalTeam ? localGoals : visitorGoals;
            return bcfGoals;
      }

}
