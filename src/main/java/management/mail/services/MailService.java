package management.mail.services;

import java.util.List;
import management.mail.converter.MailConverter;
import management.mail.domain.Mail;
import management.mail.dto.MailDto;
import management.mail.interservices.MailServiceInter;
import management.mail.repo.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  private MailRepository mailRepository;

  /**
   * Сервис для конвертации MailDto в Entity Mail и Entity Mail в MailDto
   */
  @Autowired
  MailConverter mailConverter;

  /**
   * Метод получения списка всех зарегистрированных почтовых отправлений
   *
   * @return список почтовых отправлений
   */
  public List<MailDto> findAll() {
    return mailConverter.entityToDto(mailRepository.findAll());
  }

  /**
   * Метод получения почтового отправления по его id
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return почтовое отправление
   */
  public MailDto getOne(Long id) {
    return mailConverter.entityToDto(mailRepository.findById(id).get());
  }

  /**
   * Метод регистрации нового почтового отправления
   *
   * @param mailDto это параметр с данными для регистрации
   * @return зарегистрированное почтовое отправление
   */
  public MailDto registration(MailDto mailDto) {
    Mail mail = mailConverter.dtoToEntity(mailDto);
    return mailConverter.entityToDto(mailRepository.save(mail));
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
    Mail mailEdit = mailRepository.findById(id).get();
    mailConverter.dtoToEntityEdit(mailDto, mailEdit);
    return mailConverter.entityToDto(mailRepository.saveAndFlush(mailEdit));
  }

  /**
   * Метод для удаления почтового отправления
   *
   * @param id это параметр, задающий id почтового отправления, которое необходимо удалить
   */
  public void delete(Long id) {
    mailRepository.delete(mailRepository.findById(id).get());
  }
}
