package management.mail.interservices;

import java.util.List;
import management.mail.dto.OfficeDto;

/**
 * Интерфейс с методами для работы с почтовыми отделениями
 *
 * @author Байрамов Искандер
 * @version 1.1
 */

public interface OfficeServiceInter {

  /**
   * Метод получения списка всех почтовых отделений
   *
   * @return список почтовых отделений
   */
  List<OfficeDto> findAll();

  /**
   * Метод получения почтового отделения по его id
   *
   * @param id это параметр, задающий id необходимого почтового отделения
   * @return почтовое отделение
   */
  OfficeDto getOne(Long id);

  /**
   * Метод добавления нового почтового отделения
   *
   * @param officeDto это параметр с данными для добавления почтового отделения
   * @return добавленное почтовое отделение
   */
  OfficeDto newOffice(OfficeDto officeDto);

  /**
   * Метод для изменения почтового отделения
   *
   * @param id        это параметр, задающий id почтового отделения, которое необходимо изменить
   * @param officeDto это параметр с данными, которые необходимо внести для изменения почтового
   *                  отделения
   * @return изменённое почтовое отделение
   */
  OfficeDto edit(Long id, OfficeDto officeDto);

  /**
   * Метод удаления почтового отделения
   *
   * @param id это параметр, задающий id почтового отделения, которое необходимо удалить
   */
  void delete(Long id);

}
