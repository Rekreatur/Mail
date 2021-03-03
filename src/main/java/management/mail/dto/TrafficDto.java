package management.mail.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;
import management.mail.constants.TrafficOfficeStatusEnum;

/**
 * Dto для Entity Traffic
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Data
public final class TrafficDto {

  /**
   * Поле со значением id
   */
  private Long id;

  /**
   * Поле со значением id почтового отправления
   */
  private Long mail_id;

  /**
   * Поле со значением id почтового отделения
   */
  private Long post_office_id;

  /**
   * Поле со значением статуса почтового отправления
   */
  private TrafficOfficeStatusEnum status;

  /**
   * Поле со значением даты
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
}
