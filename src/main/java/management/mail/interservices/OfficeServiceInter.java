package management.mail.interservices;

import java.util.List;
import management.mail.domain.Office;
import management.mail.dto.OfficeDto;

public interface OfficeServiceInter {

  List<OfficeDto> findAll();

  OfficeDto getOne(Long id);

  OfficeDto newOffice(OfficeDto officeDto);

  OfficeDto edit(Long id, OfficeDto officeDto);

  void delete(Long id);

}
