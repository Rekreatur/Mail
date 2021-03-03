package management.mail.services;

import management.mail.domain.Mail;
import management.mail.domain.Traffic;
import management.mail.constants.TrafficOfficeStatusEnum;
import management.mail.repo.MailRepository;
import management.mail.repo.TrafficRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TrafficService {

    @Autowired
    private TrafficRepository trafficRepository;

    @Autowired
    private MailRepository mailRepository;

    public List<Traffic> find_all() { return trafficRepository.findAll(); }

    public Stream<Traffic> get_path(Long id) {
        List<Traffic> traffic_list = new ArrayList<Traffic>();

        trafficRepository.findAll().stream().filter(x -> x.getMail_id().equals(id)).forEach(x -> traffic_list.add(x));

        return traffic_list.stream().sorted(Comparator.comparing(x -> x.getDate()));
    }

    public String get_status(Long id) {
        List<Traffic> traffic_list = new ArrayList<Traffic>();
        List<Mail> mail_list = new ArrayList<Mail>();

        trafficRepository.findAll().stream().filter(x -> x.getMail_id().equals(id)).forEach(x -> traffic_list.add(x));

        mailRepository.findAll().stream().filter(x -> x.getId().equals(id)).forEach(x -> mail_list.add(x));

        if(traffic_list.isEmpty()) {
            if(mail_list.isEmpty()) return "the parcel does not exist";
            else if(!mail_list.isEmpty()) return "registered";
        }

        traffic_list.stream().sorted(Comparator.comparing(x -> x.getDate()));

        if(traffic_list.get(traffic_list.size()-1).getStatus() != TrafficOfficeStatusEnum.DELIVERED) {
            return "in transit";
        } else {
            return "delivered";
        }
    }

    public Object newtraff(final Traffic traffic) {
        List<Traffic> traffic_list = new ArrayList<Traffic>();
        boolean arrived_flag_null = false;
        boolean arrived_flag = false;

        for(Traffic t : trafficRepository.findAll())
        {
            if(t.getMail_id() == traffic.getMail_id()) {
                arrived_flag_null = true;
                traffic_list.add(t);

                if(t.getStatus() == TrafficOfficeStatusEnum.DELIVERED)
                    return "already delivered";

                if((t.getPost_office_id() == traffic.getPost_office_id()) &&
                        (t.getStatus() == TrafficOfficeStatusEnum.DEPARTED) &&
                        (traffic.getStatus() != TrafficOfficeStatusEnum.ARRIVED)) {
                    return "the package has already been sent";
                }

                if(t.getPost_office_id() == traffic.getPost_office_id()) {
                    if((t.getStatus() == TrafficOfficeStatusEnum.ARRIVED) && (traffic.getStatus() == TrafficOfficeStatusEnum.ARRIVED))
                        return "the parcel is already in the post office";
                }

                if((t.getPost_office_id() == traffic.getPost_office_id()) &&
                        (t.getStatus() == TrafficOfficeStatusEnum.ARRIVED)) arrived_flag = true;
            }
        }

        traffic_list.stream().sorted(Comparator.comparing(x -> x.getDate()));

        if(!traffic_list.isEmpty()) {
            if (traffic.getStatus() == TrafficOfficeStatusEnum.ARRIVED) {
                if (traffic_list.get(traffic_list.size() - 1).getStatus() != TrafficOfficeStatusEnum.DEPARTED)
                    return "The parcel is located in another post office";
            }
        } else if(traffic.getStatus() != TrafficOfficeStatusEnum.ARRIVED)
            return "The parcel is located in another post office";

        if(!arrived_flag_null) {
            if(traffic.getStatus() != TrafficOfficeStatusEnum.ARRIVED) return "the parcel is not in the post office";
        }

        if(traffic.getStatus() != TrafficOfficeStatusEnum.ARRIVED) {
            if(!arrived_flag) return "the parcel is not in the post office";
        }

        traffic.setDate(LocalDateTime.now());

        try{
            trafficRepository.save(traffic);
        } catch (Exception e) {
            return e;
        }
        return new ResponseEntity<>(traffic,HttpStatus.OK);
    }
}
