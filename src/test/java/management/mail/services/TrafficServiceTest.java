package management.mail.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import management.mail.constants.TrafficOfficeStatusEnum;
import management.mail.domain.Mail;
import management.mail.domain.Traffic;
import management.mail.dto.TrafficDto;
import management.mail.repo.MailRepository;
import management.mail.repo.OfficeRepository;
import management.mail.repo.TrafficRepository;
import management.mail.servicesinterface.TrafficConverterInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Тесты для TrafficService
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@ContextConfiguration(classes = {MailRepository.class, OfficeRepository.class,
    TrafficRepository.class,
    TrafficConverterInterface.class, TrafficService.class})
@ExtendWith(SpringExtension.class)
public class TrafficServiceTest {

  @MockBean
  private MailRepository mailRepository;

  @MockBean
  private OfficeRepository officeRepository;

  @MockBean
  private TrafficConverterInterface trafficConverterInterface;

  @MockBean
  private TrafficRepository trafficRepository;

  @Autowired
  private TrafficService trafficService;

  @Test
  public void testFindAll() {
    ArrayList<TrafficDto> trafficDtoList = new ArrayList<TrafficDto>();
    when(this.trafficConverterInterface.entityToDto((List<Traffic>) any()))
        .thenReturn(trafficDtoList);
    when(this.trafficRepository.findAll()).thenReturn(new ArrayList<Traffic>());
    List<TrafficDto> actualFindAllResult = this.trafficService.findAll();
    assertSame(trafficDtoList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(this.trafficConverterInterface).entityToDto((List<Traffic>) any());
    verify(this.trafficRepository).findAll();
  }

  @Test
  public void testGetOne() {
    TrafficDto trafficDto = new TrafficDto(123L, 1L, 1L, TrafficOfficeStatusEnum.ARRIVED,
        LocalDateTime.of(1, 1, 1, 1, 1));
    when(this.trafficConverterInterface.entityToDto((Traffic) any())).thenReturn(trafficDto);

    Traffic traffic = new Traffic();
    traffic.setPost_office_id(1L);
    traffic.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
    traffic.setId(123L);
    traffic.setStatus(TrafficOfficeStatusEnum.ARRIVED);
    traffic.setMail_id(1L);
    Optional<Traffic> ofResult = Optional.<Traffic>of(traffic);
    when(this.trafficRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(trafficDto, this.trafficService.getOne(123L));
    verify(this.trafficConverterInterface).entityToDto((Traffic) any());
    verify(this.trafficRepository).findById((Long) any());
  }

  @Test
  public void testGetPath() {
    ArrayList<TrafficDto> trafficDtoList = new ArrayList<TrafficDto>();
    when(this.trafficConverterInterface.entityToDto((List<Traffic>) any()))
        .thenReturn(trafficDtoList);
    when(this.trafficRepository.findAll()).thenReturn(new ArrayList<Traffic>());
    List<TrafficDto> actualPath = this.trafficService.getPath(123L);
    assertSame(trafficDtoList, actualPath);
    assertTrue(actualPath.isEmpty());
    verify(this.trafficConverterInterface).entityToDto((List<Traffic>) any());
    verify(this.trafficRepository).findAll();
  }

  @Test
  public void testGetStatus() {
    when(this.mailRepository.findAll()).thenReturn(new ArrayList<Mail>());
    when(this.trafficRepository.findAll()).thenReturn(new ArrayList<Traffic>());
    assertEquals("the parcel does not exist", this.trafficService.getStatus(123L));
    verify(this.mailRepository).findAll();
    verify(this.trafficRepository).findAll();
  }

  @Test
  public void testNewTraffic() {
    when(this.mailRepository.findAll()).thenReturn(new ArrayList<Mail>());
    assertEquals("The postal item does not exist", this.trafficService
        .newTraffic(new TrafficDto(123L, 1L, 1L, TrafficOfficeStatusEnum.ARRIVED,
            LocalDateTime.of(1, 1, 1, 1, 1))));
    verify(this.mailRepository).findAll();
  }

}

