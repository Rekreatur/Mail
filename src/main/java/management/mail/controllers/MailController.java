package management.mail.controllers;

import java.util.List;
import management.mail.constants.MailMapConstant;
import management.mail.dto.MailDto;
import management.mail.interservices.MailServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для регистрации почтовых отправлений
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@RestController
public class MailController {

  /**
   * Сервис, содержащий методы обработки почтовых отправлений
   */
  @Autowired
  private MailServiceInter mailService;

  /**
   * Метод получения списка всех зарегистрированных почтовых отправлений
   *
   * @return список почтовых отправлений
   */
  @GetMapping(value = MailMapConstant.MAILS_ALL)
  public List<MailDto> findAll() {
    return mailService.findAll();
  }

  /**
   * Метод получения почтового отправления по его id
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return почтовое отправление
   */
  @GetMapping(value = MailMapConstant.MAIL_ONE)
  public MailDto getOne(@PathVariable(name = "id") Long id) {
    return mailService.getOne(id);
  }

  /**
   * Метод регистрации нового почтового отправления
   *
   * @param mailDto это параметр с данными для регистрации
   * @return зарегистрированное почтовое отправление
   */
  @PostMapping(value = MailMapConstant.MAIL_REGISTRATION)
  public MailDto registration(@RequestBody MailDto mailDto) {
    return mailService.registration(mailDto);
  }

  /**
   * Метод для изменения зарегистрированного почтового отправления
   *
   * @param id      это параметр, задающий id почтового отправления, которое необходимо изменить
   * @param mailDto это параметр с данными, которые необходимо внести для изменения в почтовое
   *                отправления
   * @return изменённое почтовое отправление
   */
  @PutMapping(value = MailMapConstant.MAIL_EDIT)
  public MailDto edit(@PathVariable(name = "id") Long id, @RequestBody MailDto mailDto) {
    return mailService.edit(id, mailDto);
  }

  /**
   * Метод для удаления почтового отправления
   *
   * @param id это параметр, задающий id почтового отправления, которое необходимо удалить
   */
  @DeleteMapping(value = MailMapConstant.MAIL_DELETE)
  public void delete(@PathVariable(name = "id") Long id) {
    mailService.delete(id);
  }
}
