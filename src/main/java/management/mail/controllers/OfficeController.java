package management.mail.controllers;

import java.util.List;
import management.mail.dto.OfficeDto;
import management.mail.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficeController {

  @Autowired
  private OfficeService officeService;

  @GetMapping(value = "offices")
  public List<OfficeDto> findAll() {
    return officeService.findAll();
  }

  @GetMapping(value = "office/{id}")
  public OfficeDto getOne(@PathVariable(name = "id") Long id) {
    return officeService.getOne(id);
  }

  @PostMapping(value = "newoffice")
  public OfficeDto newOffice(@RequestBody OfficeDto officeDto) {
    return officeService.newOffice(officeDto);
  }

  @PutMapping(value = "editoffice/{id}")
  public OfficeDto edit(@PathVariable(name = "id") Long id, @RequestBody OfficeDto officeDto) {
    return officeService.edit(id, officeDto);
  }

  @DeleteMapping(value = "deleteoffice/{id}")
  public void delete(@PathVariable(name = "id") Long id) {
    officeService.delete(id);
  }
}
