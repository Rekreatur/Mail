package management.mail.constants;

/**
 * Константы со значениями маппинга для TrafficController
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
public final class TrafficMapConstant {

  /**
   * Поле со значением мапинга для метода findAll
   */
  public static final String TRAFFIC = "traffic";

  /**
   * Поле со значением мапинга для метода getOne
   */
  public static final String TRAFFIC_ONE = "traffic/{id}";

  /**
   * Поле со значением мапинга для метода getPath
   */
  public static final String TRAFFIC_PATH = "fullpath/{id}";

  /**
   * Поле со значением мапинга для метода getStatus
   */
  public static final String TRAFFIC_STATUS = "status/{id}";

  /**
   * Поле со значением мапинга для метода newTraffic
   */
  public static final String TRAFFIC_NEW = "newtraffic";
}
