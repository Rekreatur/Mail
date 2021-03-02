package management.mail.controllers;

import management.mail.domain.Office;
import management.mail.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfficeController {
    @Autowired
    private OfficeService _office_service;

    @GetMapping(value = "offices")                                  //Получение списка всех почтовых отделений
    public List<Office> find_all() { return _office_service.find_all(); }

    @GetMapping(value = "office/{id}")                              //Получение почтового отделения по его id
    public Office getOne(@PathVariable("id") Office _office) {
        return _office;
    }

    @PostMapping(value = "newoff")                                  //Добавление почтового отделения
    public Office new_office(@RequestBody Office _office) {
        return _office_service.new_office(_office);
    }

    @PutMapping(value = "editoff/{id}")                             //Редактирование информации о почтовом отделении
    public Office edit(
            @PathVariable("id") Office _office_frombd,
            @RequestBody Office _office
    ) {
        return _office_service.edit(_office_frombd, _office);
    }

    @DeleteMapping(value = "deloff/{id}")                           //Удаление почтового отделения
    public void delete(@PathVariable("id") Office _office) { _office_service.delete(_office); }
}
