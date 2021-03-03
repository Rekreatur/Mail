package management.mail.interservices;

import java.util.List;
import management.mail.domain.Traffic;
import management.mail.dto.TrafficDto;

/**
 * Интерфейс с методами для конвертации TrafficDto в Entity Traffic и Entity Traffic в TrafficDto
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
public interface TrafficConverterInter {

  /**
   * Метод конвертации Entity Traffic в TrafficDto
   *
   * @param traffic это Entity Traffic, которую необходимо конвертировать
   * @return TrafficDto, полученную из Entity Traffic
   */
  public TrafficDto entityToDto(Traffic traffic);

  /**
   * Метод конвертации List Entity Traffic в List TrafficDto
   *
   * @param traffic это List Entity Traffic, который необходимо конвертировать
   * @return List TrafficDto, полученный из List Entity Traffic
   */
  public List<TrafficDto> entityToDto(List<Traffic> traffic);

  /**
   * Метод конвертации TrafficDto в Entity Traffic
   *
   * @param trafficDto это TrafficDto, который необходимо конвертировать
   * @return Entity Traffic, полученный из TrafficDto
   */
  public Traffic dtoToEntity(TrafficDto trafficDto);

  /**
   * Метод конвертации List TrafficDto в List Entity Traffic
   *
   * @param trafficDtos это List TrafficDto, который необходимо конвертировать
   * @return List Entity Traffic, полученный из List TrafficDto
   */
  public List<Traffic> dtoToEntity(List<TrafficDto> trafficDtos);

  /**
   * Метод, изменяющий Entity Traffic путём передачи значений из TrafficDto в Entity Traffic
   *
   * @param trafficDto это TrafficDto со значениями, которые необходимо передать
   * @param traffic    это Entity Traffic, которую необходимо изменить
   * @return Entity Traffic, которая была изменена
   */
  public Traffic dtoToEntityEdit(TrafficDto trafficDto, Traffic traffic);

}
