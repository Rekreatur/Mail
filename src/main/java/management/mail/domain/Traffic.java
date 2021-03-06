package management.mail.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import management.mail.constants.TrafficOfficeStatusEnum;

/**
 * Entity Traffic
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Entity
@Table
@Data
public class Traffic {

  /**
   * Поле со значением id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * Поле со значением id почтового отправления
   */
  @Column(name = "mail_id", nullable = false)
  private Long mail_id;

  /**
   * Поле со значением id почтового отделения
   */
  @Column(name = "post_office_id", nullable = false)
  private Long post_office_id;

  /**
   * Поле со значением статуса почтового отправления
   */
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private TrafficOfficeStatusEnum status;

  /**
   * Поле со значением даты
   */
  @Column(name = "date", nullable = false)
  private LocalDateTime date;
}
