package management.mail.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import management.mail.constants.MailTypeEnum;

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
