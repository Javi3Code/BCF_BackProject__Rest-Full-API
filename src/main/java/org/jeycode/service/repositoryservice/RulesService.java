package org.jeycode.service.repositoryservice;

import org.jeycode.dtos.rulesdto.RulesDto;
import org.jeycode.execptionsmanaged.GenericBackendException;
import org.jeycode.execptionsmanaged.RequestParamException;
import org.jeycode.mappers.RulesMapper;
import org.jeycode.models.Rules;
import org.jeycode.repositories.RulesRepository;
import org.jeycode.utilities.RestServiceUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RulesService implements RestServiceUtils
{

      private final RulesMapper rulesMapper;
      private final RulesRepository rulesRepository;

      public ResponseEntity<?> updateRules(RulesDto rulesDto)
      {
            try
            {
                  var resultPoints = rulesDto.getResultPoints();
                  var bcfGoalsPoints = rulesDto.getGoalsBCFPoints();
                  var signPoints = rulesDto.getSignPoints();
                  var areValidRules = resultPoints > bcfGoalsPoints && bcfGoalsPoints > signPoints;
                  if (!areValidRules)
                  {
                        throw new RequestParamException(UPDATE_RULES_NOT_VALID);
                  }
                  var rules = rulesMapper.toRules(rulesDto);
                  rules.setRulesId(1);
                  RulesDto rulesDtoUpdated = rulesMapper.toRulesDto(rulesRepository.save(rules));
                  log.info("Se actualizaron las reglas correctamente");
                  return ResponseEntity.ok(rulesDtoUpdated);
            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(UP_RULES_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,UP_RULES_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> updateToDefaultRules()
      {

            try
            {
                  String logMsg = null;
                  var defaultRules = getDefaultRulesObj();

                  var rules = rulesRepository.findById(1L)
                                             .get();
                  if (!defaultRules.equals(rules))
                  {
                        rules.setResultPoints(DFL_RESULT_POINTS);
                        rules.setSignPoints(DFL_SIGN_POINTS);
                        rules.setGoalsBCFPoints(DFL_GOALS_POINTS);
                        var rulesUpdated = rulesRepository.save(rules);
                        logMsg = "Se actualizaron las reglas correctamente: " + rulesUpdated.toString();
                  }
                  else
                  {
                        logMsg = "Las reglas ya se encontraban con los valores por defecto";
                  }
                  log.info(logMsg);
                  var rulesDtoUpdated = rulesMapper.toRulesDto(rules);
                  return ResponseEntity.ok(rulesDtoUpdated);
            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(UP_RULES_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,UP_RULES_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> getRulesSet()
      {
            try
            {
                  return ResponseEntity.ok(rulesMapper.toRulesDto(rulesRepository.getOne(1L)));
            }
            catch (Exception ex)
            {
                  log.error(GET_RULES_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,GET_RULES_SERV_UNKNOWN_ERROR);
            }
      }

      /*
       * =============================private method=============================
       */

      private Rules getDefaultRulesObj()
      {
            var defaultRules = new Rules();
            defaultRules.setRulesId(1L);
            defaultRules.setResultPoints(DFL_RESULT_POINTS);
            defaultRules.setSignPoints(DFL_SIGN_POINTS);
            defaultRules.setGoalsBCFPoints(DFL_GOALS_POINTS);
            return defaultRules;
      }

}
