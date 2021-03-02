package management.mail.services;

import java.util.List;
import management.mail.converter.OfficeConverter;
import management.mail.domain.Office;
import management.mail.dto.OfficeDto;
import management.mail.interservices.OfficeServiceInter;
import management.mail.repo.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService implements OfficeServiceInter {

  @Autowired
  private OfficeRepository officeRepository;

  @Autowired
  private OfficeConverter officeConverter;

  public List<OfficeDto> findAll() {
    return officeConverter.entityToDto(officeRepository.findAll());
  }

  public OfficeDto getOne(Long id) {
    return officeConverter.entityToDto(officeRepository.findById(id).get());
  }

  public OfficeDto newOffice(OfficeDto officeDto) {
    Office office = officeConverter.dtoToEntity(officeDto);
    return officeConverter.entityToDto(officeRepository.save(office));
  }

  public OfficeDto edit(Long id, OfficeDto officeDto) {
    Office officeEdit = officeRepository.findById(id).get();
    officeConverter.dtoToEntityEdit(officeDto, officeEdit);
    return officeConverter.entityToDto(officeRepository.saveAndFlush(officeEdit));
  }

  public void delete(Long id) {
    officeRepository.delete(officeRepository.findById(id).get());
  }
}
