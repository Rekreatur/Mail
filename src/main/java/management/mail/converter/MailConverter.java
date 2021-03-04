package management.mail.converter;

import java.util.List;
import java.util.stream.Collectors;
import management.mail.domain.Mail;
import management.mail.dto.MailDto;
import management.mail.servicesinterface.MailConverterInterface;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации MailDto в Entity Mail и Entity Mail в MailDto
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Service
public class MailConverter implements MailConverterInterface {

  /**
   * Метод конвертации Entity Mail в MailDto
   *
   * @param mail это Entity Mail, которую необходимо конвертировать
   * @return MailDto, полученную из Entity Mail
   */
  public MailDto entityToDto(Mail mail) {
    MailDto dto = new MailDto(mail.getId(), mail.getType(), mail.getIndex(), mail.getAddress(),
        mail.getName());
    return dto;
  }

  /**
   * Метод конвертации List Entity Mail в List MailDto
   *
   * @param mail это List Entity Mail, который необходимо конвертировать
   * @return List MailDto, полученный из List Entity Mail
   */
  public List<MailDto> entityToDto(List<Mail> mail) {
    return mail.stream().map(this::entityToDto).collect(Collectors.toList());
  }

  /**
   * Метод конвертации MailDto в Entity Mail
   *
   * @param mailDto это MailDto, который необходимо конвертировать
   * @return Entity Mail, полученный из MailDto
   */
  public Mail dtoToEntity(MailDto mailDto) {
    Mail mail = new Mail();
    mail.setId(mailDto.getId());
    mail.setType(mailDto.getType());
    mail.setIndex(mailDto.getIndex());
    mail.setAddress(mailDto.getAddress());
    mail.setName(mailDto.getName());
    return mail;
  }

  /**
   * Метод конвертации List MailDto в List Entity Mail
   *
   * @param dto это List MailDto, который необходимо конвертировать
   * @return List Entity Mail, полученный из List MailDto
   */
  public List<Mail> dtoToEntity(List<MailDto> dto) {
    return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
  }

  /**
   * Метод, изменяющий Entity Mail путём передачи значений из MailDto в Entity Mail
   *
   * @param mailDto это MailDto со значениями, которые необходимо передать
   * @param mail    это Entity Mail, которую необходимо изменить
   * @return Entity Mail, которая была изменена
   */
  public Mail dtoToEntityEdit(MailDto mailDto, Mail mail) {
    mail.setType(mailDto.getType());
    mail.setIndex(mailDto.getIndex());
    mail.setAddress(mailDto.getAddress());
    mail.setName(mailDto.getName());
    return mail;
  }
}
