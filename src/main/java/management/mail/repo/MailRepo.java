package management.mail.repo;

import management.mail.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepo extends JpaRepository<Mail, Long> {
}
