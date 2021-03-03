package management.mail.repo;

import management.mail.domain.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с Entity Office
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

}
