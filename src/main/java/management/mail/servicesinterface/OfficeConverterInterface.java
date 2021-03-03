package management.mail.servicesinterface;

import java.util.List;
import management.mail.domain.Office;
import management.mail.dto.OfficeDto;

/**
 * Интерфейс с методами для конвертации OfficeDto в Entity Office и Entity Office в OfficeDto
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
public interface OfficeConverterInterface {

  /**
   * Метод конвертации Entity Office в OfficeDto
   *
   * @param office это Entity Office, которую необходимо конвертировать
   * @return OfficeDto, полученную из Entity Office
   */
  OfficeDto entityToDto(Office office);

  /**
   * Метод конвертации List Entity Office в List OfficeDto
   *
   * @param offices это List Entity Office, который необходимо конвертировать
   * @return List OfficeDto, полученный из List Entity Office
   */
  List<OfficeDto> entityToDto(List<Office> offices);

  /**
   * Метод конвертации OfficeDto в Entity Office
   *
   * @param officeDto это OfficeDto, который необходимо конвертировать
   * @return Entity Office, полученный из OfficeDto
   */
  Office dtoToEntity(OfficeDto officeDto);

  /**
   * Метод конвертации List OfficeDto в List Entity Office
   *
   * @param officeDtos это List OfficeDto, который необходимо конвертировать
   * @return List Entity Office, полученный из List OfficeDto
   */
  List<Office> dtoToEntity(List<OfficeDto> officeDtos);

  /**
   * Метод, изменяющий Entity Office путём передачи значений из OfficeDto в Entity Office
   *
   * @param officeDto это OfficeDto со значениями, которые необходимо передать
   * @param office    это Entity Office, которую необходимо изменить
   * @return Entity Office, которая была изменена
   */
  Office dtoToEntityEdit(OfficeDto officeDto, Office office);
}
