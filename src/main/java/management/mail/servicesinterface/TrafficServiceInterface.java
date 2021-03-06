package management.mail.servicesinterface;

import java.util.List;
import management.mail.dto.TrafficDto;

/**
 * Интерфейс с методами для работы с передвижениями почтовых отправлений
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
public interface TrafficServiceInterface {

  /**
   * Метод получения списка всех передвижений всех почтовых отправлений
   *
   * @return список передвижений почтовых отправлений
   */
  List<TrafficDto> findAll();

  /**
   * Метод получения конкретного передвижения почтового отправления
   *
   * @param id это параметр, задающий id необходимого передвижения
   * @return передвижение почтового отправления
   */
  TrafficDto getOne(Long id);

  /**
   * Метод получения полного пути передвижения конкретного почтового отправления
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return список передвижений
   */
  List<TrafficDto> getPath(Long id);

  /**
   * Метод получения статуса почтового отправления
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return статус почтового отправления
   */
  String getStatus(Long id);

  /**
   * Метод регистрации передвижения почтового отправления
   *
   * @param trafficDto это параметр, задающий информацию о передвижении
   * @return информация о том, удалось ли зарегистрировать передвижение
   */
  String newTraffic(TrafficDto trafficDto);
}
