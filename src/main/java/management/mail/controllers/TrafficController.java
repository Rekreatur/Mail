package management.mail.controllers;

import management.mail.domain.Mail;
import management.mail.domain.Traffic;
import management.mail.misc.StatusEnum;
import management.mail.repo.MailRepo;
import management.mail.repo.Post_Office_Repo;
import management.mail.repo.TrafficRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class TrafficController {
    private final TrafficRepo trafficRepo;
    private final MailRepo mailRepo;
    private final Post_Office_Repo post_office_repo;

    Comparator<Traffic> comparator = Comparator.comparing(obj -> obj.getDate());

    @Autowired
    public TrafficController(TrafficRepo trafficRepo, MailRepo mailRepo, Post_Office_Repo post_office_repo) {
        this.trafficRepo = trafficRepo;
        this.mailRepo = mailRepo;
        this.post_office_repo = post_office_repo;
    }

    @GetMapping(value = "traffic")                                //Получение информации о передвижениях всех посылок
    public Object list() {
        return new ResponseEntity<>(trafficRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "traffic/{id}")                          //Получение информации о конкретной записи передвижения
    public Object getOne(@PathVariable("id") Traffic traffic) {
        return new ResponseEntity<>(traffic, HttpStatus.OK);
    }

    @GetMapping(value = "fullpath/{id}")                        //Получение полного пути конкретной посылки
    public Object get_path(@PathVariable("id") Long id) {
        List<Traffic> traffic_list = new ArrayList<Traffic>();
        for(Traffic t : trafficRepo.findAll())
        {
            if(t.getMail_id().equals(id))
                traffic_list.add(t);
        }
        traffic_list.sort(comparator);
        return new ResponseEntity<>(traffic_list, HttpStatus.OK);
    }

    @GetMapping(value = "status/{id}")                          //Получение статуса конкретной посылки
    public Object get_status(@PathVariable("id") Long id) {
        List<Traffic> traffic_list = new ArrayList<Traffic>();
        List<Mail> mail_list = new ArrayList<Mail>();

        for(Traffic t : trafficRepo.findAll()) {
            if(t.getMail_id().equals(id))
                traffic_list.add(t);
        }

        for(Mail m : mailRepo.findAll()) {
            if(m.getId().equals(id))
                mail_list.add(m);
            }

        if(traffic_list.isEmpty()) {
            if(mail_list.isEmpty()) return "the parcel does not exist";
            else if(!mail_list.isEmpty()) return "registered";
        }

        traffic_list.sort(comparator);
        if(traffic_list.get(traffic_list.size()-1).getStatus() != StatusEnum.delivered) {
            return "in transit";
        } else {
            return new ResponseEntity<>(traffic_list.get(traffic_list.size() - 1).getStatus(), HttpStatus.OK);
        }
    }

    @PostMapping(value = "newtraff")                           //Принятие, отправка и доставка посылки
    public Object new_traffic(@RequestBody final Traffic traffic) {
        List<Traffic> traffic_list = new ArrayList<Traffic>();
        boolean arrived_flag_null = false;
        boolean arrived_flag = false;

        for(Traffic t : trafficRepo.findAll())
        {
            if(t.getMail_id() == traffic.getMail_id()) {
                arrived_flag_null = true;
                traffic_list.add(t);

                if(t.getStatus() == StatusEnum.delivered)
                    return "already delivered";

                if((t.getPost_office_id() == traffic.getPost_office_id()) &&
                        (t.getStatus() == StatusEnum.departed) &&
                        (traffic.getStatus() != StatusEnum.arrived)) {
                    return "the package has already been sent";
                }

                if(t.getPost_office_id() == traffic.getPost_office_id()) {
                    if((t.getStatus() == StatusEnum.arrived) && (traffic.getStatus() == StatusEnum.arrived))
                        return "the parcel is already in the post office";
                }

                if((t.getPost_office_id() == traffic.getPost_office_id()) &&
                        (t.getStatus() == StatusEnum.arrived)) arrived_flag = true;
            }
        }

        traffic_list.sort(comparator);

        if(!traffic_list.isEmpty()) {
            if (traffic.getStatus() == StatusEnum.arrived) {
                if (traffic_list.get(traffic_list.size() - 1).getStatus() != StatusEnum.departed)
                    return "The parcel is located in another post office";
            }
        } else if(traffic.getStatus() != StatusEnum.arrived)
            return "The parcel is located in another post office";

        if(!arrived_flag_null) {
            if(traffic.getStatus() != StatusEnum.arrived) return "the parcel is not in the post office";
        }

        if(traffic.getStatus() != StatusEnum.arrived) {
            if(!arrived_flag) return "the parcel is not in the post office";
        }

        traffic.setDate(LocalDateTime.now());

        try{
            trafficRepo.save(traffic);
        } catch (Exception e) {
            return e;
        }
        return new ResponseEntity<>(traffic,HttpStatus.OK);
    }
}
