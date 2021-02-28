package management.mail.repo;

import management.mail.domain.Post_Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Post_Office_Repo extends JpaRepository<Post_Office, Long> {
}
