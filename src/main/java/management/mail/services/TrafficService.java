package management.mail.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import management.mail.constants.TrafficOfficeStatusEnum;
import management.mail.domain.Mail;
import management.mail.domain.Traffic;
import management.mail.dto.TrafficDto;
import management.mail.interservices.TrafficConverterInter;
import management.mail.repo.MailRepository;
import management.mail.repo.TrafficRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    List<Traffic> trafficList = new ArrayList<Traffic>();
    List<Mail> mailList = new ArrayList<Mail>();

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
    Traffic traffic = trafficConverter.dtoToEntity(trafficDto);
    List<Traffic> traffic_list = new ArrayList<Traffic>();
    boolean arrived_flag_null = false;
    boolean arrived_flag = false;

    for (Traffic t : trafficRepository.findAll()) {
      if (t.getMail_id() == traffic.getMail_id()) {
        arrived_flag_null = true;
        traffic_list.add(t);

        if (t.getStatus() == TrafficOfficeStatusEnum.DELIVERED) {
          return "already delivered";
        }

        if ((t.getPost_office_id() == traffic.getPost_office_id()) &&
            (t.getStatus() == TrafficOfficeStatusEnum.DEPARTED) &&
            (traffic.getStatus() != TrafficOfficeStatusEnum.ARRIVED)) {
          return "the package has already been sent";
        }

        if (t.getPost_office_id() == traffic.getPost_office_id()) {
          if ((t.getStatus() == TrafficOfficeStatusEnum.ARRIVED) && (traffic.getStatus()
              == TrafficOfficeStatusEnum.ARRIVED)) {
            return "the parcel is already in the post office";
          }
        }

        if ((t.getPost_office_id() == traffic.getPost_office_id()) &&
            (t.getStatus() == TrafficOfficeStatusEnum.ARRIVED)) {
          arrived_flag = true;
        }
      }
    }

    traffic_list.stream().sorted(Comparator.comparing(x -> x.getDate()));

    if (!traffic_list.isEmpty()) {
      if (traffic.getStatus() == TrafficOfficeStatusEnum.ARRIVED) {
        if (traffic_list.get(traffic_list.size() - 1).getStatus()
            != TrafficOfficeStatusEnum.DEPARTED) {
          return "The parcel is located in another post office";
        }
      }
    } else if (traffic.getStatus() != TrafficOfficeStatusEnum.ARRIVED) {
      return "The parcel is located in another post office";
    }

    if (!arrived_flag_null) {
      if (traffic.getStatus() != TrafficOfficeStatusEnum.ARRIVED) {
        return "the parcel is not in the post office";
      }
    }

    if (traffic.getStatus() != TrafficOfficeStatusEnum.ARRIVED) {
      if (!arrived_flag) {
        return "the parcel is not in the post office";
      }
    }

    traffic.setDate(LocalDateTime.now());

    try {
      trafficRepository.save(traffic);
    } catch (Exception e) {
      return "there is no post office";
    }
    return "new mail movement added";
  }
}
