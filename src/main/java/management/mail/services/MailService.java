package management.mail.services;

import management.mail.domain.Mail;
import management.mail.repo.MailRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    @Autowired
    private MailRepo mailRepo;

    public List<Mail> find_all() { return mailRepo.findAll(); }

    public Mail registration(Mail mail) { return mailRepo.save(mail); }

    public Mail edit(Mail mailfromdb, Mail mail) {
        BeanUtils.copyProperties(mail, mailfromdb, "id");
        return mailRepo.save(mailfromdb);
    }

    public void delete(Mail mail) { mailRepo.delete(mail); }
}
