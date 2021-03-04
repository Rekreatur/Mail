package management.mail.dto;

import lombok.Getter;
import management.mail.constants.MailTypeEnum;

/**
 * Dto для Entity Mail
 *
 * @author Байрамов Искандер
 * @version 1.1
 */

@Getter
public final class MailDto {

  /**
   * Поле со значением id
   */
  private final Long id;
  /**
   * Поле со значением типа посылки
   */
  private final MailTypeEnum type;
  /**
   * Поле со значением индекса получателя
   */
  private final String index;
  /**
   * Поле со значанием адреса получателя
   */
  private final String address;
  /**
   * Поле со значением имени получателя
   */
  private final String name;

  public MailDto(Long id, MailTypeEnum type, String index, String address, String name) {
    this.id = id;
    this.type = type;
    this.index = index;
    this.address = address;
    this.name = name;
  }
}
