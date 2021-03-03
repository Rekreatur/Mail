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
import management.mail.domain.Office;
import management.mail.dto.OfficeDto;
import management.mail.interservices.OfficeConverterInter;
import management.mail.repo.OfficeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Тесты для OfficeService
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@ContextConfiguration(classes = {OfficeService.class, OfficeRepository.class,
    OfficeConverterInter.class})
@ExtendWith(SpringExtension.class)
public class OfficeServiceTest {

  @MockBean
  private OfficeConverterInter officeConverterInter;

  @MockBean
  private OfficeRepository officeRepository;

  @Autowired
  private OfficeService officeService;

  @Test
  public void testFindAll() {
    ArrayList<OfficeDto> officeDtoList = new ArrayList<OfficeDto>();
    when(this.officeConverterInter.entityToDto((List<Office>) any())).thenReturn(officeDtoList);
    when(this.officeRepository.findAll()).thenReturn(new ArrayList<Office>());
    List<OfficeDto> actualFindAllResult = this.officeService.findAll();
    assertSame(officeDtoList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(this.officeConverterInter).entityToDto((List<Office>) any());
    verify(this.officeRepository).findAll();
  }

  @Test
  public void testGetOne() {
    OfficeDto officeDto = new OfficeDto();
    when(this.officeConverterInter.entityToDto((Office) any())).thenReturn(officeDto);

    Office office = new Office();
    office.setId(123L);
    office.setName("Name");
    office.setIndex("Index");
    office.setAddress("42 Main St");
    Optional<Office> ofResult = Optional.<Office>of(office);
    when(this.officeRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(officeDto, this.officeService.getOne(123L));
    verify(this.officeConverterInter).entityToDto((Office) any());
    verify(this.officeRepository).findById((Long) any());
  }


  @Test
  public void testNewOffice() {
    Office office = new Office();
    office.setId(123L);
    office.setName("Name");
    office.setIndex("Index");
    office.setAddress("42 Main St");
    when(this.officeRepository.saveAndFlush((Office) any())).thenReturn(office);

    Office office1 = new Office();
    office1.setId(123L);
    office1.setName("Name");
    office1.setIndex("Index");
    office1.setAddress("42 Main St");
    OfficeDto officeDto = new OfficeDto();
    when(this.officeConverterInter.entityToDto((Office) any())).thenReturn(officeDto);
    when(this.officeConverterInter.dtoToEntity((OfficeDto) any())).thenReturn(office1);
    assertSame(officeDto, this.officeService.newOffice(new OfficeDto()));
    verify(this.officeConverterInter).entityToDto((Office) any());
    verify(this.officeConverterInter).dtoToEntity((OfficeDto) any());
    verify(this.officeRepository).saveAndFlush((Office) any());
  }

  @Test
  public void testEdit() {
    Office office = new Office();
    office.setId(123L);
    office.setName("Name");
    office.setIndex("Index");
    office.setAddress("42 Main St");
    OfficeDto officeDto = new OfficeDto();
    when(this.officeConverterInter.entityToDto((Office) any())).thenReturn(officeDto);
    when(this.officeConverterInter.dtoToEntityEdit((OfficeDto) any(), (Office) any()))
        .thenReturn(office);

    Office office1 = new Office();
    office1.setId(123L);
    office1.setName("Name");
    office1.setIndex("Index");
    office1.setAddress("42 Main St");
    Optional<Office> ofResult = Optional.<Office>of(office1);

    Office office2 = new Office();
    office2.setId(123L);
    office2.setName("Name");
    office2.setIndex("Index");
    office2.setAddress("42 Main St");
    when(this.officeRepository.saveAndFlush((Office) any())).thenReturn(office2);
    when(this.officeRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(officeDto, this.officeService.edit(123L, new OfficeDto()));
    verify(this.officeConverterInter).entityToDto((Office) any());
    verify(this.officeConverterInter).dtoToEntityEdit((OfficeDto) any(), (Office) any());
    verify(this.officeRepository).findById((Long) any());
    verify(this.officeRepository).saveAndFlush((Office) any());
  }


  @Test
  public void testDelete() {
    Office office = new Office();
    office.setId(123L);
    office.setName("Name");
    office.setIndex("Index");
    office.setAddress("42 Main St");
    Optional<Office> ofResult = Optional.<Office>of(office);
    doNothing().when(this.officeRepository).delete((Office) any());
    when(this.officeRepository.findById((Long) any())).thenReturn(ofResult);
    this.officeService.delete(123L);
    verify(this.officeRepository).delete((Office) any());
    verify(this.officeRepository).findById((Long) any());
  }

}

