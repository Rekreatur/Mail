package management.mail.converter;

import java.util.List;
import java.util.stream.Collectors;
import management.mail.domain.Office;
import management.mail.dto.OfficeDto;
import management.mail.servicesinterface.OfficeConverterInterface;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации OfficeDto в Entity Office и Entity Office в OfficeDto
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Service
public class OfficeConverter implements OfficeConverterInterface {

  /**
   * Метод конвертации Entity Office в OfficeDto
   *
   * @param office это Entity Office, которую необходимо конвертировать
   * @return OfficeDto, полученную из Entity Office
   */
  public OfficeDto entityToDto(Office office) {
    OfficeDto dto = new OfficeDto();
    dto.setId(office.getId());
    dto.setIndex(office.getIndex());
    dto.setName(office.getName());
    dto.setAddress(office.getAddress());
    return dto;
  }

  /**
   * Метод конвертации List Entity Office в List OfficeDto
   *
   * @param offices это List Entity Office, который необходимо конвертировать
   * @return List OfficeDto, полученный из List Entity Office
   */
  public List<OfficeDto> entityToDto(List<Office> offices) {
    return offices.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
  }

  /**
   * Метод конвертации OfficeDto в Entity Office
   *
   * @param officeDto это OfficeDto, который необходимо конвертировать
   * @return Entity Office, полученный из OfficeDto
   */
  public Office dtoToEntity(OfficeDto officeDto) {
    Office office = new Office();
    office.setId(officeDto.getId());
    office.setIndex(officeDto.getIndex());
    office.setName(officeDto.getName());
    office.setAddress(officeDto.getAddress());
    return office;
  }

  /**
   * Метод конвертации List OfficeDto в List Entity Office
   *
   * @param officeDtos это List OfficeDto, который необходимо конвертировать
   * @return List Entity Office, полученный из List OfficeDto
   */
  public List<Office> dtoToEntity(List<OfficeDto> officeDtos) {
    return officeDtos.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
  }

  /**
   * Метод, изменяющий Entity Office путём передачи значений из OfficeDto в Entity Office
   *
   * @param officeDto это OfficeDto со значениями, которые необходимо передать
   * @param office    это Entity Office, которую необходимо изменить
   * @return Entity Office, которая была изменена
   */
  public Office dtoToEntityEdit(OfficeDto officeDto, Office office) {
    office.setIndex(officeDto.getIndex());
    office.setName(officeDto.getName());
    office.setAddress(officeDto.getAddress());
    return office;
  }
}
