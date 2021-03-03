package management.mail.controllers;

import java.util.List;
import management.mail.domain.Traffic;
import management.mail.dto.TrafficDto;
import management.mail.services.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrafficController {

  @Autowired
  private TrafficService trafficService;

  @GetMapping(value = "traffic")
  public List<TrafficDto> findAll() {
    return trafficService.findAll();
  }

  @GetMapping(value = "traffic/{id}")
  public TrafficDto getOne(@PathVariable(name = "id") Long id) {
    return trafficService.getOne(id);
  }

  @GetMapping(value = "fullpath/{id}")
  public List<TrafficDto> getPath(@PathVariable("id") Long id) {
    return trafficService.getPath(id);
  }

  @GetMapping(value = "status/{id}")
  public String getStatus(@PathVariable("id") Long id) {
    return trafficService.getStatus(id);
  }

  @PostMapping(value = "newtraff")
  public String newTraffic(@RequestBody TrafficDto trafficDto) {
    return trafficService.newTraffic(trafficDto);
  }
}
