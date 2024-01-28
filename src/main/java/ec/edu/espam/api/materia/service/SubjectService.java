package ec.edu.espam.api.materia.service;

import ec.edu.espam.api.materia.domain.Subject;
import ec.edu.espam.api.materia.domain.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    SubjectDto create(SubjectDto subject);
    List<SubjectDto> getAll();
    SubjectDto getById(long id);
    SubjectDto update(Long id, SubjectDto dto);
    void delete(Long id);
    Subject getEntityById(Long id);
}
