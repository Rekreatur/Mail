package management.mail.services;

import management.mail.converter.ConverterMail;
import management.mail.domain.Mail;
import management.mail.dto.MailDto;
import management.mail.interservices.MailServiceInter;
import management.mail.repo.MailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService implements MailServiceInter {

    @Autowired
    private MailRepo mailRepo;

    @Autowired
    ConverterMail converterMail;

    public List<MailDto> find_all() {
        List<Mail> findAll = mailRepo.findAll();
        return converterMail.entityToDto(findAll);
    }

    public Mail getOne(Long id) { return mailRepo.findById(id).get(); }

    public Mail registration(Mail mail) { return mailRepo.save(mail); }

    public Mail edit(Long id, Mail mail) {
        Mail mailfEdit =  mailRepo.findById(id).get();
        mailfEdit.setType(mail.getType());
        mailfEdit.setIndex(mail.getIndex());
        mailfEdit.setAddress(mail.getAddress());
        mailfEdit.setName(mail.getName());
        return mailRepo.saveAndFlush(mailfEdit);
    }

    public void delete(Mail mail) { mailRepo.delete(mail); }
}
