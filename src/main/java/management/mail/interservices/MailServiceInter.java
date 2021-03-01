package management.mail.interservices;

import management.mail.domain.Mail;
import management.mail.dto.MailDto;

import java.util.List;

public interface MailServiceInter {
    public List<MailDto> find_all();
    public MailDto getOne(Long id);
    public MailDto registration(MailDto mailDto);
    public MailDto edit(Long id, MailDto mailDto);
    public void delete(Long id);
}
