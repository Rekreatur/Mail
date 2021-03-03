package management.mail.constants;

/**
 * Константы со значениями маппинга для OfficeController
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
public final class OfficeMapConstant {

  /**
   * Поле со значением маппинга для метода findAll
   */
  public static final String OFFICES_ALL = "offices";

  /**
   * Поле со значением маппинга для метода getOne
   */
  public static final String OFFICE_ONE = "office/{id}";

  /**
   * Поле со значением маппинга для метода newOffice
   */
  public static final String OFFICE_NEW = "newoffice";

  /**
   * Поле со значением маппинга для метода edit
   */
  public static final String OFFICE_EDIT = "editoffice/{id}";

  /**
   * Поле со значением маппинга для метода delete
   */
  public static final String OFFICE_DELETE = "deleteoffice/{id}";
}
