package management.mail.servicesinterface;

import java.util.List;
import management.mail.domain.Mail;
import management.mail.dto.MailDto;

/**
 * Интерфейс с методами для конвертации MailDto в Entity Mail и Entity Mail в MailDto
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
public interface MailConverterInterface {

  /**
   * Метод конвертации Entity Mail в MailDto
   *
   * @param mail это Entity Mail, которую необходимо конвертировать
   * @return MailDto, полученную из Entity Mail
   */
  MailDto entityToDto(Mail mail);

  /**
   * Метод конвертации List Entity Mail в List MailDto
   *
   * @param mail это List Entity Mail, который необходимо конвертировать
   * @return List MailDto, полученный из List Entity Mail
   */
  List<MailDto> entityToDto(List<Mail> mail);

  /**
   * Метод конвертации MailDto в Entity Mail
   *
   * @param mailDto это MailDto, который необходимо конвертировать
   * @return Entity Mail, полученный из MailDto
   */
  Mail dtoToEntity(MailDto mailDto);

  /**
   * Метод конвертации List MailDto в List Entity Mail
   *
   * @param dto это List MailDto, который необходимо конвертировать
   * @return List Entity Mail, полученный из List MailDto
   */
  List<Mail> dtoToEntity(List<MailDto> dto);

  /**
   * Метод, изменяющий Entity Mail путём передачи значений из MailDto в Entity Mail
   *
   * @param mailDto это MailDto со значениями, которые необходимо передать
   * @param mail    это Entity Mail, которую необходимо изменить
   * @return Entity Mail, которая была изменена
   */
  Mail dtoToEntityEdit(MailDto mailDto, Mail mail);
}
