package management.mail.dto;

import lombok.Getter;

/**
 * Dto для Entity Office
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Getter
public final class OfficeDto {

  /**
   * Поле со значением id
   */
  private final Long id;

  /**
   * Поле со значением индекса почтового отделения
   */
  private final String index;

  /**
   * Поле со значанием названия почтового отделения
   */
  private final String name;

  /**
   * Поле со значаением адреса почтового отделения
   */
  private final String address;

  public OfficeDto(Long id, String index, String name, String address) {
    this.id = id;
    this.index = index;
    this.name = name;
    this.address = address;
  }
}
