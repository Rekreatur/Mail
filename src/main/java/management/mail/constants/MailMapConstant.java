package management.mail.constants;

/**
 * Константы со значениями маппинга для MailController
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
public final class MailMapConstant {

  /**
   * Поле со значением маппинга для метода findAll
   */
  public static final String MAILS_ALL = "mails";

  /**
   * Поле со значением маппинга для метода getOne
   */
  public static final String MAIL_ONE = "mail/{id}";

  /**
   * Поле со значением маппинга для метода registration
   */
  public static final String MAIL_REGISTRATION = "registration";

  /**
   * Поле со значением маппинга для метода edit
   */
  public static final String MAIL_EDIT = "edit/{id}";

  /**
   * Поле со значением маппинга для метода delete
   */
  public static final String MAIL_DELETE = "delete/{id}";
}
