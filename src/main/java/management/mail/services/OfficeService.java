package management.mail.services;

import management.mail.domain.Office;
import management.mail.repo.OfficeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository _office_repository;

    public List<Office> find_all() {
        return _office_repository.findAll();
    }

    public Office new_office(Office _office) {
        return _office_repository.save(_office);
    }

    public Office edit( Office _office_frombd, Office _office) {
        BeanUtils.copyProperties(_office, _office_frombd, "id");
        return _office_repository.save(_office_frombd);
    }

    public void delete(Office _office) {
        _office_repository.delete(_office);
    }
}
