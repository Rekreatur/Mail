package management.mail.repo;

import management.mail.domain.Post_Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Post_Office_Repo extends JpaRepository<Post_Office, Long> {
}
