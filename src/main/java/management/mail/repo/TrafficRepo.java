package management.mail.repo;

import management.mail.domain.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficRepo extends JpaRepository<Traffic, Long> {
}
