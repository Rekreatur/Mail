package management.mail.controllers;

import management.mail.domain.Mail;
import management.mail.dto.MailDto;
import management.mail.interservices.MailServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailController {
    @Autowired
    private MailServiceInter mailService;

    @GetMapping(value = "mails")                                //Получение списка всех зарегистрированных посылок
    public List<MailDto> find_all() { return mailService.find_all(); }

    @GetMapping(value = "mail/{id}")                            //Получение посылки по её id
    public Mail getOne(@PathVariable(name = "id") Long id) {
        return mailService.getOne(id);
    }

    @PostMapping(value = "registration")                                 //Регистрация новой посылки
    public Mail registration(@RequestBody Mail mail) { return mailService.registration(mail);}

    @PutMapping(value = "edit/{id}")                            //Редактирование зарегистрированной посылки
    public Mail edit(@PathVariable(name = "id") Long id ,@RequestBody Mail mail) { return mailService.edit(id,mail); }

    @DeleteMapping(value = "delete/{id}")                          //Удаление посылки
    public void delete(@PathVariable("id") Mail mail) { mailService.delete(mail); }
}
