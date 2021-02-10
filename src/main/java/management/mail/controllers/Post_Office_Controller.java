package management.mail.controllers;

import management.mail.domain.Post_Office;
import management.mail.repo.Post_Office_Repo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Post_Office_Controller {
    private final Post_Office_Repo post_office_repo;

    @Autowired
    public Post_Office_Controller(Post_Office_Repo post_office_repo) {
        this.post_office_repo = post_office_repo;
    }

    @GetMapping(value = "offices")                                  //Получение списка всех почтовых отделений
    public List<Post_Office> list() {
        return post_office_repo.findAll();
    }

    @GetMapping(value = "office/{id}")                              //Получение почтового отделения по его id
    public Post_Office getOne(@PathVariable("id") Post_Office post_office) {
        return post_office;
    }

    @PostMapping(value = "newoff")                                  //Добавление почтового отделения
    public Post_Office new_office(@RequestBody Post_Office post_office) {
        return post_office_repo.save(post_office);
    }

    @PutMapping(value = "editoff/{id}")                             //Редактирование информации о почтовом отделении
    public Post_Office edit(
            @PathVariable("id") Post_Office post_office_frombd,
            @RequestBody Post_Office post_office
    ) {
        BeanUtils.copyProperties(post_office, post_office_frombd, "id");
        return post_office_repo.save(post_office_frombd);
    }

    @DeleteMapping(value = "deloff/{id}")                           //Удаление почтового отделения
    public void delete(@PathVariable("id") Post_Office post_office) {
        post_office_repo.delete(post_office);
    }
}
