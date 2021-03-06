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
import management.mail.repo.MailRepository;
import management.mail.repo.OfficeRepository;
import management.mail.repo.TrafficRepository;
import management.mail.servicesinterface.TrafficConverterInterface;
import management.mail.servicesinterface.TrafficServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с передвижениями почтовых отправлений
 *
 * @author Байрамов Искандер
 * @version 1.1
 */
@Service
public class TrafficService implements TrafficServiceInterface {

  /**
   * Репозиторий для работы с Entity Traffic
   */
  @Autowired
  private TrafficRepository trafficRepository;

  /**
   * Репозиторий для работы с Entity Mail
   */
  @Autowired
  private MailRepository mailRepository;

  /**
   * Репозиторий для работы с Entity Office
   */
  @Autowired
  OfficeRepository officeRepository;

  /**
   * Сервис для конвертации TrafficDto в Entity Traffic и Entity Traffic в TrafficDto
   */
  @Autowired
  private TrafficConverterInterface trafficConverter;

  /**
   * Метод получения списка всех передвижений всех почтовых отправлений
   *
   * @return список передвижений почтовых отправлений
   */
  public List<TrafficDto> findAll() {
    return trafficConverter.entityToDto(trafficRepository.findAll());
  }

  /**
   * Метод получения конкретного передвижения почтового отправления
   *
   * @param id это параметр, задающий id необходимого передвижения
   * @return передвижение почтового отправления
   */
  public TrafficDto getOne(Long id) {
    return trafficConverter.entityToDto(trafficRepository.findById(id).get());
  }

  /**
   * Метод получения полного пути передвижения конкретного почтового отправления
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return список передвижений
   */
  public List<TrafficDto> getPath(Long id) {
    List<Traffic> trafficList = new ArrayList<>();

    trafficRepository.findAll().stream().filter(x -> x.getMail_id().equals(id))
        .sorted(Comparator.comparing(Traffic::getDate))
        .forEach(trafficList::add);

    return trafficConverter.entityToDto(trafficList);
  }

  /**
   * Метод получения статуса почтового отправления
   *
   * @param id это параметр, задающий id необходимого почтового отправления
   * @return статус почтового отправления
   */
  public String getStatus(Long id) {
    List<Traffic> trafficList = new ArrayList<>();
    List<Mail> mailList = new ArrayList<>();

    trafficRepository.findAll().stream().filter(x -> x.getMail_id().equals(id))
        .forEach(trafficList::add);

    mailRepository.findAll().stream().filter(x -> x.getId().equals(id))
        .forEach(mailList::add);

    if (trafficList.isEmpty()) {
      if (mailList.isEmpty()) {
        return "the parcel does not exist";
      } else {
        return "registered";
      }
    }

    trafficList.stream().sorted(Comparator.comparing(Traffic::getDate));

    if (!trafficList.get(trafficList.size() - 1).getStatus()
        .equals(TrafficOfficeStatusEnum.DELIVERED)
    ) {
      return "in transit";
    } else {
      return "delivered";
    }
  }

  /**
   * Метод регистрации передвижения почтового отправления
   *
   * @param trafficDto это параметр, задающий информацию о передвижении
   * @return информация о том, удалось ли зарегистрировать передвижение
   */
  public String newTraffic(TrafficDto trafficDto) {
    List<Mail> mailList = new ArrayList<>();
    List<Office> officeList = new ArrayList<>();
    List<Traffic> trafficList = new ArrayList<>();
    List<Traffic> trafficListWork = new ArrayList<>();
    Boolean trafficFirst = false;

    mailRepository.findAll().stream().filter(x -> x.getId().equals(trafficDto.getMail_id()))
        .forEach(mailList::add);

    if (mailList.isEmpty()) {
      return "The postal item does not exist";
    }

    officeRepository.findAll().stream()
        .filter(x -> x.getId().equals(trafficDto.getPost_office_id()))
        .forEach(officeList::add);

    if (officeList.isEmpty()) {
      return "The office doesn't exist";
    }

    trafficRepository.findAll().stream().filter(x -> x.getMail_id().equals(trafficDto.getMail_id()))
        .forEach(trafficList::add);

    if (trafficList.isEmpty()) {
      trafficFirst = true;
    }

    trafficList.stream().filter(x -> x.getStatus().equals(TrafficOfficeStatusEnum.DELIVERED))
        .forEach(trafficListWork::add);

    if (!trafficListWork.isEmpty()) {
      return "the postal already delivered";
    }

    trafficListWork.clear();
    trafficList.stream().filter(x -> x.getPost_office_id().equals(trafficDto.getPost_office_id()))
        .forEach(trafficListWork::add);

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
      trafficList.stream().sorted(Comparator.comparing(Traffic::getDate));
      if (trafficList.get(trafficList.size() - 1).getStatus()
          .equals(TrafficOfficeStatusEnum.ARRIVED)) {
        return "the postal in another office";
      }
    }

    Traffic traffic = trafficConverter.dtoToEntity(trafficDto);
    traffic.setDate(LocalDateTime.now());

    trafficRepository.saveAndFlush(traffic);

    return "new mail movement added";
  }
}
