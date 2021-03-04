package management.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Приложение для работы с почтовыми отправлениями
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@SpringBootApplication
public class MailApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(MailApplication.class, args);
  }
}

