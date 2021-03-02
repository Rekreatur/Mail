package management.mail.services;

import management.mail.converter.ConverterMail;
import management.mail.domain.Mail;
import management.mail.dto.MailDto;
import management.mail.interservices.MailServiceInter;
import management.mail.repo.MailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для регистрации почтовых отправлений
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Service
public class MailService implements MailServiceInter {

  /**
   * Репозиторий для работы с Entity Mail
   */
  @Autowired
  private MailRepo mailRepo;

  /**
   * Сервис для конвертации MailDto в Entity Mail и Entity Mail в MailDto
   */
  @Autowired
  ConverterMail converterMail;

  /**
   * Метод получения списка всех зарегистрированных почтовых отправлений
   *
   * @return список почтовых отправлений
   */
  public List<MailDto> findAll() {
    List<Mail> findAll = mailRepo.findAll();
    return converterMail.entityToDto(findAll);
  }

  /**
   * Метод получения почтового отправления по его id
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return почтовое отправление
   */
  public MailDto getOne(Long id) {
    Mail mail = mailRepo.findById(id).get();
    return converterMail.entityToDto(mail);
  }

  /**
   * Метод регистрации нового почтового отправления
   *
   * @param mailDto это параметр с данными для регистрации
   * @return зарегистрированное почтовое отправление
   */
  public MailDto registration(MailDto mailDto) {
    Mail mail = converterMail.dtoToEntity(mailDto);
    mail = mailRepo.save(mail);
    return converterMail.entityToDto(mail);
  }

  /**
   * Метод для изменения зарегистрированного почтового отправления
   *
   * @param id      это параметр, задающий id почтового отправления, которое необходимо изменить
   * @param mailDto это параметр с данными, которые необходимо внести для изменения в почтовое
   *                отправления
   * @return изменённое почтовое отправление
   */
  public MailDto edit(Long id, MailDto mailDto) {
    Mail mailEdit = mailRepo.findById(id).get();
    converterMail.dtoToEntityEdit(mailDto, mailEdit);
    return converterMail.entityToDto(mailRepo.saveAndFlush(mailEdit));
  }

  /**
   * Метод для удаления почтового отправления
   *
   * @param id это параметр, задающий id почтового отправления, которое необходимо удалить
   */
  public void delete(Long id) {
    mailRepo.delete(mailRepo.findById(id).get());
  }
}
