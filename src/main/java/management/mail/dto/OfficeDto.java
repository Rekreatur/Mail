package management.mail.dto;

import lombok.Data;

/**
 * Dto для Entity Office
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Data
public class OfficeDto {

  /**
   * Поле со значением id
   */
  private Long id;

  /**
   * Поле со значением индекса почтового отделения
   */
  private String index;

  /**
   * Поле со значанием названия почтового отделения
   */
  private String name;

  /**
   * Поле со значаением адреса почтового отделения
   */
  private String address;
}
