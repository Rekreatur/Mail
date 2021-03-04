package management.mail.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import management.mail.constants.TrafficOfficeStatusEnum;

/**
 * Dto для Entity Traffic
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Getter
public final class TrafficDto {

  /**
   * Поле со значением id
   */
  private final Long id;

  /**
   * Поле со значением id почтового отправления
   */
  private final Long mail_id;

  /**
   * Поле со значением id почтового отделения
   */
  private final Long post_office_id;

  /**
   * Поле со значением статуса почтового отправления
   */
  private final TrafficOfficeStatusEnum status;

  /**
   * Поле со значением даты
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime date;

  public TrafficDto(Long id, Long mail_id, Long post_office_id,
      TrafficOfficeStatusEnum status, LocalDateTime date) {
    this.id = id;
    this.mail_id = mail_id;
    this.post_office_id = post_office_id;
    this.status = status;
    this.date = date;
  }
}
