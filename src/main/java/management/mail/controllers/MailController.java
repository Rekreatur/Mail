package management.mail.controllers;

import management.mail.domain.Mail;
import management.mail.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping(value = "mails")                                //Получение списка всех зарегистрированных посылок
    public List<Mail> find_all() {
        return mailService.find_all();
    }

    @GetMapping(value = "mail/{id}")                            //Получение посылки по её id
    public Mail getOne(@PathVariable("id") Mail mail) {
        return mail;
    }

    @PostMapping(value = "reg")                                 //Регистрация новой посылки
    public Mail registration(@RequestBody Mail mail) { return mailService.registration(mail);}

    @PutMapping(value = "edit/{id}")                            //Редактирование зарегистрированной посылки
    public Mail edit(
            @PathVariable("id") Mail mailfromdb,
            @RequestBody Mail mail
    ) { return mailService.edit(mailfromdb, mail); }

    @DeleteMapping(value = "del/{id}")                          //Удаление посылки
    public void delete(@PathVariable("id") Mail mail) { mailService.delete(mail); }
}
