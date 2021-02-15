package management.mail.services;

import management.mail.domain.Post_Office;
import management.mail.repo.Post_Office_Repo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Post_Office_Service {
    private final Post_Office_Repo post_office_repo;

    public Post_Office_Service(Post_Office_Repo post_office_repo) {
        this.post_office_repo = post_office_repo;
    }

    public List<Post_Office> find_all() {
        return post_office_repo.findAll();
    }

    public Post_Office new_office(Post_Office post_office) {
        return post_office_repo.save(post_office);
    }

    public Post_Office edit( Post_Office post_office_frombd, Post_Office post_office) {
        BeanUtils.copyProperties(post_office, post_office_frombd, "id");
        return post_office_repo.save(post_office_frombd);
    }

    public void delete(Post_Office post_office) {
        post_office_repo.delete(post_office);
    }
}
