package management.mail.controllers;

import management.mail.domain.Post_Office;
import management.mail.services.Post_Office_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Post_Office_Controller {
    @Autowired
    private Post_Office_Service post_office_service;

    @GetMapping(value = "offices")                                  //Получение списка всех почтовых отделений
    public List<Post_Office> find_all() { return post_office_service.find_all(); }

    @GetMapping(value = "office/{id}")                              //Получение почтового отделения по его id
    public Post_Office getOne(@PathVariable("id") Post_Office post_office) {
        return post_office;
    }

    @PostMapping(value = "newoff")                                  //Добавление почтового отделения
    public Post_Office new_office(@RequestBody Post_Office post_office) {
        return post_office_service.new_office(post_office);
    }

    @PutMapping(value = "editoff/{id}")                             //Редактирование информации о почтовом отделении
    public Post_Office edit(
            @PathVariable("id") Post_Office post_office_frombd,
            @RequestBody Post_Office post_office
    ) {
        return post_office_service.edit(post_office_frombd, post_office);
    }

    @DeleteMapping(value = "deloff/{id}")                           //Удаление почтового отделения
    public void delete(@PathVariable("id") Post_Office post_office) { post_office_service.delete(post_office); }
}
