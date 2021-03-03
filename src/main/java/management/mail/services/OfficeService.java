package management.mail.services;

import java.util.List;
import management.mail.converter.OfficeConverter;
import management.mail.domain.Office;
import management.mail.dto.OfficeDto;
import management.mail.interservices.OfficeConverterInter;
import management.mail.interservices.OfficeServiceInter;
import management.mail.repo.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с почтовыми отделениями
 *
 * @author Байрамов Искандер
 * @version 1.1
 */

@Service
public class OfficeService implements OfficeServiceInter {

  /**
   * Репозиторий для работы с Entity Office
   */
  @Autowired
  private OfficeRepository officeRepository;

  /**
   * Сервис для конвертации OfficeDto в Entity Office и Entity Office в OfficeDto
   */
  @Autowired
  private OfficeConverterInter officeConverter;

  /**
   * Метод получения списка всех почтовых отделений
   *
   * @return список почтовых отделений
   */
  public List<OfficeDto> findAll() {
    return officeConverter.entityToDto(officeRepository.findAll());
  }

  /**
   * Метод получения почтового отделения по его id
   *
   * @param id это параметр, задающий id необходимого почтового отделения
   * @return почтовое отделение
   */
  public OfficeDto getOne(Long id) {
    return officeConverter.entityToDto(officeRepository.findById(id).get());
  }

  /**
   * Метод добавления нового почтового отделения
   *
   * @param officeDto это параметр с данными для добавления почтового отделения
   * @return добавленное почтовое отделение
   */
  public OfficeDto newOffice(OfficeDto officeDto) {
    Office office = officeConverter.dtoToEntity(officeDto);
    return officeConverter.entityToDto(officeRepository.save(office));
  }

  /**
   * Метод для изменения почтового отделения
   *
   * @param id        это параметр, задающий id почтового отделения, которое необходимо изменить
   * @param officeDto это параметр с данными, которые необходимо внести для изменения почтового
   *                  отделения
   * @return изменённое почтовое отделение
   */
  public OfficeDto edit(Long id, OfficeDto officeDto) {
    Office officeEdit = officeRepository.findById(id).get();
    officeConverter.dtoToEntityEdit(officeDto, officeEdit);
    return officeConverter.entityToDto(officeRepository.saveAndFlush(officeEdit));
  }

  /**
   * Метод удаления почтового отделения
   *
   * @param id это параметр, задающий id почтового отделения, которое необходимо удалить
   */
  public void delete(Long id) {
    officeRepository.delete(officeRepository.findById(id).get());
  }
}
