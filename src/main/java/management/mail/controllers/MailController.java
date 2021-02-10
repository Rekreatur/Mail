package management.mail.controllers;

import management.mail.domain.Mail;
import management.mail.repo.MailRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailController {
    private final MailRepo mailRepo;

    @Autowired
    public MailController(MailRepo mailRepo) {
        this.mailRepo = mailRepo;
    }

    @GetMapping(value = "Hello")
    public String hello() {return "hello all";}

    @GetMapping(value = "mails")                                //Получение списка всех зарегистрированных посылок
    public List<Mail> list() {
        return mailRepo.findAll();
    }

    @GetMapping(value = "mail/{id}")                            //Получение посылки по её id
    public Mail getOne(@PathVariable("id") Mail mail) {
        return mail;
    }

    @PostMapping(value = "reg")                                 //Регистрация новой посылки
    public Mail registration(@RequestBody Mail mail) {
        return mailRepo.save(mail);
    }

    @PutMapping(value = "edit/{id}")                            //Редактирование зарегистрированной посылки
    public Mail edit(
            @PathVariable("id") Mail mailfromdb,
            @RequestBody Mail mail
    ) {
        BeanUtils.copyProperties(mail, mailfromdb, "id");
        return mailRepo.save(mailfromdb);
    }

    @DeleteMapping(value = "del/{id}")                          //Удаление посылки
    public void delete(@PathVariable("id") Mail mail) {
        mailRepo.delete(mail);
    }
}
