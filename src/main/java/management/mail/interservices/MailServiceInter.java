package management.mail.interservices;

import management.mail.domain.Mail;
import management.mail.dto.MailDto;

import java.util.List;

public interface MailServiceInter {
    public List<MailDto> find_all();
    public Mail getOne(Long id);
    public Mail registration(Mail mail);
    public Mail edit(Long id, Mail mail);
    public void delete(Mail mail);
}
