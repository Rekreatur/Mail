package management.mail.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import management.mail.constants.MailTypeEnum;
import management.mail.domain.Mail;
import management.mail.dto.MailDto;
import management.mail.interservices.MailConverterInter;
import management.mail.repo.MailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Тесты для MailService
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@ContextConfiguration(classes = {MailRepository.class, MailConverterInter.class, MailService.class})
@ExtendWith(SpringExtension.class)
public class MailServiceTest {

  @MockBean
  private MailConverterInter mailConverterInter;

  @MockBean
  private MailRepository mailRepository;

  @Autowired
  private MailService mailService;

  @Test
  public void testFindAll() {
    ArrayList<MailDto> mailDtoList = new ArrayList<MailDto>();
    when(this.mailConverterInter.entityToDto((List<Mail>) any())).thenReturn(mailDtoList);
    when(this.mailRepository.findAll()).thenReturn(new ArrayList<Mail>());
    List<MailDto> actualFindAllResult = this.mailService.findAll();
    assertSame(mailDtoList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(this.mailConverterInter).entityToDto((List<Mail>) any());
    verify(this.mailRepository).findAll();
  }

  @Test
  public void testGetOne() {
    MailDto mailDto = new MailDto();
    when(this.mailConverterInter.entityToDto((Mail) any())).thenReturn(mailDto);

    Mail mail = new Mail();
    mail.setId(123L);
    mail.setName("Name");
    mail.setIndex("Index");
    mail.setType(MailTypeEnum.LETTER);
    mail.setAddress("42 Main St");
    Optional<Mail> ofResult = Optional.<Mail>of(mail);
    when(this.mailRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(mailDto, this.mailService.getOne(123L));
    verify(this.mailConverterInter).entityToDto((Mail) any());
    verify(this.mailRepository).findById((Long) any());
  }


  @Test
  public void testRegistration() {
    Mail mail = new Mail();
    mail.setId(123L);
    mail.setName("Name");
    mail.setIndex("Index");
    mail.setType(MailTypeEnum.LETTER);
    mail.setAddress("42 Main St");
    when(this.mailRepository.saveAndFlush((Mail) any())).thenReturn(mail);

    Mail mail1 = new Mail();
    mail1.setId(123L);
    mail1.setName("Name");
    mail1.setIndex("Index");
    mail1.setType(MailTypeEnum.LETTER);
    mail1.setAddress("42 Main St");
    MailDto mailDto = new MailDto();
    when(this.mailConverterInter.entityToDto((Mail) any())).thenReturn(mailDto);
    when(this.mailConverterInter.dtoToEntity((MailDto) any())).thenReturn(mail1);
    assertSame(mailDto, this.mailService.registration(new MailDto()));
    verify(this.mailConverterInter).entityToDto((Mail) any());
    verify(this.mailConverterInter).dtoToEntity((MailDto) any());
    verify(this.mailRepository).saveAndFlush((Mail) any());
  }

  @Test
  public void testEdit() {
    Mail mail = new Mail();
    mail.setId(123L);
    mail.setName("Name");
    mail.setIndex("Index");
    mail.setType(MailTypeEnum.LETTER);
    mail.setAddress("42 Main St");
    MailDto mailDto = new MailDto();
    when(this.mailConverterInter.entityToDto((Mail) any())).thenReturn(mailDto);
    when(this.mailConverterInter.dtoToEntityEdit((MailDto) any(), (Mail) any())).thenReturn(mail);

    Mail mail1 = new Mail();
    mail1.setId(123L);
    mail1.setName("Name");
    mail1.setIndex("Index");
    mail1.setType(MailTypeEnum.LETTER);
    mail1.setAddress("42 Main St");
    Optional<Mail> ofResult = Optional.<Mail>of(mail1);

    Mail mail2 = new Mail();
    mail2.setId(123L);
    mail2.setName("Name");
    mail2.setIndex("Index");
    mail2.setType(MailTypeEnum.LETTER);
    mail2.setAddress("42 Main St");
    when(this.mailRepository.saveAndFlush((Mail) any())).thenReturn(mail2);
    when(this.mailRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(mailDto, this.mailService.edit(123L, new MailDto()));
    verify(this.mailConverterInter).entityToDto((Mail) any());
    verify(this.mailConverterInter).dtoToEntityEdit((MailDto) any(), (Mail) any());
    verify(this.mailRepository).findById((Long) any());
    verify(this.mailRepository).saveAndFlush((Mail) any());
  }

  @Test
  public void testDelete() {
    Mail mail = new Mail();
    mail.setId(123L);
    mail.setName("Name");
    mail.setIndex("Index");
    mail.setType(MailTypeEnum.LETTER);
    mail.setAddress("42 Main St");
    Optional<Mail> ofResult = Optional.<Mail>of(mail);
    doNothing().when(this.mailRepository).delete((Mail) any());
    when(this.mailRepository.findById((Long) any())).thenReturn(ofResult);
    this.mailService.delete(123L);
    verify(this.mailRepository).delete((Mail) any());
    verify(this.mailRepository).findById((Long) any());
  }


}

