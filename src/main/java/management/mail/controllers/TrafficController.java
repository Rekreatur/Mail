package management.mail.controllers;

import java.util.List;
import management.mail.constants.TrafficMapConstant;
import management.mail.dto.TrafficDto;
import management.mail.servicesinterface.TrafficServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для регистрации передвижений почтовых отправлений
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@RestController
public class TrafficController {

  /**
   * Сервис, содержащий методы для работы с передвижениями почтовых отправлений
   */
  @Autowired
  private TrafficServiceInterface trafficService;

  /**
   * Метод получения списка всех передвижений всех почтовых отправлений
   *
   * @return список передвижений почтовых отправлений
   */
  @GetMapping(value = TrafficMapConstant.TRAFFIC)
  public List<TrafficDto> findAll() {
    return trafficService.findAll();
  }

  /**
   * Метод получения конкретного передвижения почтового отправления
   *
   * @param id это параметр, задающий id необходимого передвижения
   * @return передвижение почтового отправления
   */
  @GetMapping(value = TrafficMapConstant.TRAFFIC_ONE)
  public TrafficDto getOne(@PathVariable(name = "id") Long id) {
    return trafficService.getOne(id);
  }

  /**
   * Метод получения полного пути передвижения конкретного почтового отправления
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return список передвижений
   */
  @GetMapping(value = TrafficMapConstant.TRAFFIC_PATH)
  public List<TrafficDto> getPath(@PathVariable("id") Long id) {
    return trafficService.getPath(id);
  }

  /**
   * Метод получения статуса почтового отправления
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return статус почтового отправления
   */
  @GetMapping(value = TrafficMapConstant.TRAFFIC_STATUS)
  public String getStatus(@PathVariable("id") Long id) {
    return trafficService.getStatus(id);
  }

  /**
   * Метод регистрации передвижения почтового отправления
   *
   * @param trafficDto это параметр, задающий информацию о передвижении
   * @return информация о том, удалось ли зарегистрировать передвижение
   */
  @PostMapping(value = TrafficMapConstant.TRAFFIC_NEW)
  public String newTraffic(@RequestBody TrafficDto trafficDto) {
    return trafficService.newTraffic(trafficDto);
  }
}
