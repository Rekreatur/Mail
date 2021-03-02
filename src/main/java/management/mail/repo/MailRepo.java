package management.mail.repo;

import management.mail.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с Entity Mail
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Repository
public interface MailRepo extends JpaRepository<Mail, Long> {

}
