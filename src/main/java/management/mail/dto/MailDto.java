package management.mail.dto;

import lombok.Data;
import management.mail.constants.MailTypeEnum;

/**
 * Dto для Entity Mail
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Data
public final class MailDto {

  /**
   * Поле со значением id
   */
  private Long id;
  /**
   * Поле со значением типа посылки
   */
  private MailTypeEnum type;
  /**
   * Поле со значением индекса получателя
   */
  private String index;
  /**
   * Поле со значанием адреса получателя
   */
  private String address;
  /**
   * Поле со значением имени получателя
   */
  private String name;
}
