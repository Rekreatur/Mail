package management.mail.domain;

import lombok.*;
import management.mail.constants.MailTypeEnum;

import javax.persistence.*;

/**
 * Entity Mail
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Entity
@Table
@Data
public class Mail {

  /**
   * Поле со значением id
   */
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * Поле со значением типа посылки
   */
  @Column(name = "type")
  @Enumerated(EnumType.ORDINAL)
  private MailTypeEnum type;

  /**
   * Поле со значением индекса получателя
   */
  @Column(name = "index")
  private String index;

  /**
   * Поле со значанием адреса получателя
   */
  @Column(name = "address")
  private String address;

  /**
   * Поле со значением имени получателя
   */
  @Column(name = "name")
  private String name;
}
