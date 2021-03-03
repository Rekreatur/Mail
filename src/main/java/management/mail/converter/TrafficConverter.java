package management.mail.converter;

import java.util.List;
import java.util.stream.Collectors;
import management.mail.domain.Traffic;
import management.mail.dto.TrafficDto;
import management.mail.interservices.TrafficConverterInter;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации TrafficDto в Entity Traffic и Entity Traffic в TrafficDto
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Service
public class TrafficConverter implements TrafficConverterInter {

  /**
   * Метод конвертации Entity Traffic в TrafficDto
   *
   * @param traffic это Entity Traffic, которую необходимо конвертировать
   * @return TrafficDto, полученную из Entity Traffic
   */
  public TrafficDto entityToDto(Traffic traffic) {
    TrafficDto dto = new TrafficDto();
    dto.setId(traffic.getId());
    dto.setMail_id(traffic.getMail_id());
    dto.setPost_office_id(traffic.getPost_office_id());
    dto.setStatus(traffic.getStatus());
    dto.setDate(traffic.getDate());
    return dto;
  }

  /**
   * Метод конвертации List Entity Traffic в List TrafficDto
   *
   * @param traffic это List Entity Traffic, который необходимо конвертировать
   * @return List TrafficDto, полученный из List Entity Traffic
   */
  public List<TrafficDto> entityToDto(List<Traffic> traffic) {
    return traffic.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
  }

  /**
   * Метод конвертации TrafficDto в Entity Traffic
   *
   * @param trafficDto это TrafficDto, который необходимо конвертировать
   * @return Entity Traffic, полученный из TrafficDto
   */
  public Traffic dtoToEntity(TrafficDto trafficDto) {
    Traffic traffic = new Traffic();
    traffic.setId(trafficDto.getId());
    traffic.setMail_id(trafficDto.getMail_id());
    traffic.setPost_office_id(trafficDto.getPost_office_id());
    traffic.setStatus(trafficDto.getStatus());
    traffic.setDate(trafficDto.getDate());
    return traffic;
  }

  /**
   * Метод конвертации List TrafficDto в List Entity Traffic
   *
   * @param trafficDtos это List TrafficDto, который необходимо конвертировать
   * @return List Entity Traffic, полученный из List TrafficDto
   */
  public List<Traffic> dtoToEntity(List<TrafficDto> trafficDtos) {
    return trafficDtos.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
  }

  /**
   * Метод, изменяющий Entity Traffic путём передачи значений из TrafficDto в Entity Traffic
   *
   * @param trafficDto это TrafficDto со значениями, которые необходимо передать
   * @param traffic    это Entity Traffic, которую необходимо изменить
   * @return Entity Traffic, которая была изменена
   */
  public Traffic dtoToEntityEdit(TrafficDto trafficDto, Traffic traffic) {
    traffic.setMail_id(trafficDto.getMail_id());
    traffic.setPost_office_id(trafficDto.getPost_office_id());
    traffic.setStatus(trafficDto.getStatus());
    traffic.setDate(trafficDto.getDate());
    return traffic;
  }
}
