package management.mail.controllers;

import java.util.List;
import management.mail.constants.OfficeMapConstant;
import management.mail.dto.OfficeDto;
import management.mail.interservices.OfficeServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с почтовыми отделениями
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@RestController
public class OfficeController {

  /**
   * Сервис, содержащий методы для работы с почтовыми отделениями
   */
  @Autowired
  private OfficeServiceInter officeService;

  /**
   * Метод получения списка всех почтовых отделений
   *
   * @return список почтовых отделений
   */
  @GetMapping(value = OfficeMapConstant.OFFICES_ALL)
  public List<OfficeDto> findAll() {
    return officeService.findAll();
  }

  /**
   * Метод получения почтового отделения по его id
   *
   * @param id это параметр, задающий id необходимого почтового отделения
   * @return почтовое отделение
   */
  @GetMapping(value = OfficeMapConstant.OFFICE_ONE)
  public OfficeDto getOne(@PathVariable(name = "id") Long id) {
    return officeService.getOne(id);
  }

  /**
   * Метод добавления нового почтового отделения
   *
   * @param officeDto это параметр с данными для добавления почтового отделения
   * @return добавленное почтовое отделение
   */
  @PostMapping(value = OfficeMapConstant.OFFICE_NEW)
  public OfficeDto newOffice(@RequestBody OfficeDto officeDto) {
    return officeService.newOffice(officeDto);
  }

  /**
   * Метод для изменения почтового отделения
   *
   * @param id        это параметр, задающий id почтового отделения, которое необходимо изменить
   * @param officeDto это параметр с данными, которые необходимо внести для изменения почтового
   *                  отделения
   * @return изменённое почтовое отделение
   */
  @PutMapping(value = OfficeMapConstant.OFFICE_EDIT)
  public OfficeDto edit(@PathVariable(name = "id") Long id, @RequestBody OfficeDto officeDto) {
    return officeService.edit(id, officeDto);
  }

  /**
   * Метод удаления почтового отделения
   *
   * @param id это параметр, задающий id почтового отделения, которое необходимо удалить
   */
  @DeleteMapping(value = OfficeMapConstant.OFFICE_DELETE)
  public void delete(@PathVariable(name = "id") Long id) {
    officeService.delete(id);
  }
}
