package management.mail.controllers;

import management.mail.domain.Traffic;
import management.mail.services.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrafficController {
    @Autowired
    private TrafficService trafficService;

    @GetMapping(value = "traffic")                                //Получение информации о передвижениях всех посылок
    public Object find_all() { return trafficService.findAll(); }

    @GetMapping(value = "traffic/{id}")                          //Получение информации о конкретной записи передвижения
    public Object getOne(@PathVariable("id") Traffic traffic) {
        return new ResponseEntity<>(traffic, HttpStatus.OK);
    }

    @GetMapping(value = "fullpath/{id}")                        //Получение полного пути конкретной посылки
    public Object get_path(@PathVariable("id") Long id) { return new ResponseEntity<>(this.trafficService.get_path(id), HttpStatus.OK); }

    @GetMapping(value = "status/{id}")                          //Получение статуса конкретной посылки
    public Object get_status(@PathVariable("id") Long id) { return trafficService.get_status(id); }

    @PostMapping(value = "newtraff")                           //Принятие, отправка и доставка посылки
    public Object new_traffic(@RequestBody final Traffic traffic) { return trafficService.newtraff(traffic); }
}
