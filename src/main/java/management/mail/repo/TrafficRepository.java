package management.mail.repo;

import management.mail.domain.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с Entity Traffic
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

}
