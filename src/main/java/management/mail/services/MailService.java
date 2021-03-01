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

    public MailDto getOne(Long id) {
        Mail mail = mailRepo.findById(id).get();
        return converterMail.entityToDto(mail);
    }

    public MailDto registration(MailDto mailDto) {
        Mail mail = converterMail.dtoToEntity(mailDto);
        mail = mailRepo.save(mail);
        return converterMail.entityToDto(mail);
    }

    public MailDto edit(Long id, MailDto mailDto) {
        Mail mailEdit =  mailRepo.findById(id).get();
        converterMail.dtoToEntityEdit(mailDto, mailEdit);
        return converterMail.entityToDto(mailRepo.saveAndFlush(mailEdit));
    }

    public void delete(Long id) {
        mailRepo.delete(mailRepo.findById(id).get()); 
    }
}
