package management.mail.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity Office
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Entity
@Table
@Data
public class Office {

  /**
   * Поле со значением id
   */
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * Поле со значением индекса почтового отделения
   */
  @Column(name = "index")
  private String index;

  /**
   * Поле со значанием названия почтового отделения
   */
  @Column(name = "name")
  private String name;

  /**
   * Поле со значаением адреса почтового отделения
   */
  @Column(name = "address")
  private String address;
}
