package management.mail.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import management.mail.constants.TrafficOfficeStatusEnum;
import management.mail.domain.Mail;
import management.mail.domain.Office;
import management.mail.domain.Traffic;
import management.mail.dto.TrafficDto;
import management.mail.interservices.TrafficConverterInter;
import management.mail.repo.MailRepository;
import management.mail.repo.OfficeRepository;
import management.mail.repo.TrafficRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с почтовыми отправлениями
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Service
public class TrafficService {

  @Autowired
  private TrafficRepository trafficRepository;

  @Autowired
  private MailRepository mailRepository;

  @Autowired
  OfficeRepository officeRepository;

  @Autowired
  private TrafficConverterInter trafficConverter;

  public List<TrafficDto> findAll() {
    return trafficConverter.entityToDto(trafficRepository.findAll());
  }

  public TrafficDto getOne(Long id) {
    return trafficConverter.entityToDto(trafficRepository.findById(id).get());
  }

  public List<TrafficDto> getPath(Long id) {
    List<Traffic> trafficList = new ArrayList<Traffic>();

    trafficRepository.findAll().stream().filter(x -> x.getMail_id().equals(id))
        .sorted(Comparator.comparing(x -> x.getDate()))
        .forEach(x -> trafficList.add(x));

    return trafficConverter.entityToDto(trafficList);
  }

  public String getStatus(Long id) {
    List<Traffic> trafficList = new ArrayList<>();
    List<Mail> mailList = new ArrayList<>();

    trafficRepository.findAll().stream().filter(x -> x.getMail_id().equals(id))
        .forEach(x -> trafficList.add(x));

    mailRepository.findAll().stream().filter(x -> x.getId().equals(id))
        .forEach(x -> mailList.add(x));

    if (trafficList.isEmpty()) {
      if (mailList.isEmpty()) {
        return "the parcel does not exist";
      } else {
        return "registered";
      }
    }

    trafficList.stream().sorted(Comparator.comparing(x -> x.getDate()));

    if (trafficList.get(trafficList.size() - 1).getStatus()
        != TrafficOfficeStatusEnum.DELIVERED) {
      return "in transit";
    } else {
      return "delivered";
    }
  }

  public String newTraffic(TrafficDto trafficDto) {
    List<Mail> mailList = new ArrayList<>();
    List<Office> officeList = new ArrayList<>();
    List<Traffic> trafficList = new ArrayList<>();
    List<Traffic> trafficListWork = new ArrayList<>();
    Boolean trafficFirst = false;

    mailRepository.findAll().stream().filter(x -> x.getId().equals(trafficDto.getMail_id()))
        .forEach(x -> mailList.add(x));
    if (mailList.isEmpty()) {
      return "The postal item does not exist";
    }

    officeRepository.findAll().stream()
        .filter(x -> x.getId().equals(trafficDto.getPost_office_id()))
        .forEach(x -> officeList.add(x));
    if (officeList.isEmpty()) {
      return "The office doesn't exist";
    }

    trafficRepository.findAll().stream().filter(x -> x.getMail_id().equals(trafficDto.getMail_id()))
        .forEach(x -> trafficList.add(x));
    if (trafficList.isEmpty()) {
      trafficFirst = true;
    }
    trafficList.stream().filter(x -> x.getStatus().equals(TrafficOfficeStatusEnum.DELIVERED))
        .forEach(x -> trafficListWork.add(x));
    if (!trafficListWork.isEmpty()) {
      return "the postal already delivered";
    }

    trafficListWork.clear();
    trafficList.stream().filter(x -> x.getPost_office_id().equals(trafficDto.getPost_office_id()))
        .forEach(x -> trafficListWork.add(x));

    if ((trafficListWork.size() == 1) && (trafficDto.getStatus()
        .equals(TrafficOfficeStatusEnum.ARRIVED))) {
      return "the postal is already in the office";
    }

    if (trafficListWork.size() == 2) {
      return "the postal has already left the office";
    }

    if ((trafficListWork.isEmpty()) && (!(trafficDto.getStatus()
        .equals(TrafficOfficeStatusEnum.ARRIVED)))) {
      return "the postal is not in the office";
    }

    if ((!trafficFirst) && (trafficDto.getStatus().equals(TrafficOfficeStatusEnum.ARRIVED))) {
      trafficList.stream().sorted(Comparator.comparing(x -> x.getDate()));
      if (trafficList.get(trafficList.size() - 1).getStatus()
          .equals(TrafficOfficeStatusEnum.ARRIVED)) {
        return "the postal in another office";
      }
    }

    trafficDto.setDate(LocalDateTime.now());

    trafficRepository.saveAndFlush(trafficConverter.dtoToEntity(trafficDto));

    return "new mail movement added";
  }
}
