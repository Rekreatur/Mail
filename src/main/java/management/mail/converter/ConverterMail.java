package management.mail.converter;

import management.mail.domain.Mail;
import management.mail.dto.MailDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterMail {

    public MailDto entityToDto(Mail mail) {
        MailDto dto = new MailDto();
        dto.setId(mail.getId());
        dto.setType(mail.getType());
        dto.setIndex(mail.getIndex());
        dto.setAddress(mail.getAddress());
        dto.setName(mail.getName());
        return dto;
    }

    public List<MailDto> entityToDto(List<Mail> mail) {
        return mail.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public Mail dtoToEntity(MailDto mailDto) {
        Mail mail = new Mail();
        mail.setId(mailDto.getId());
        mail.setType(mailDto.getType());
        mail.setIndex(mailDto.getIndex());
        mail.setAddress(mailDto.getAddress());
        mail.setName(mailDto.getName());
        return mail;
    }

    public List<Mail> dtoToEntity(List<MailDto> dto) {
        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

    public Mail dtoToEntityEdit(MailDto mailDto, Mail mail) {
        mail.setType(mailDto.getType());
        mail.setIndex(mailDto.getIndex());
        mail.setAddress(mailDto.getAddress());
        mail.setName(mailDto.getName());
        return mail;
    }
}
