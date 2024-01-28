package ec.edu.espam.api.materia.service.impl;

import ec.edu.espam.api.materia.domain.Subject;
import ec.edu.espam.api.materia.domain.dto.SubjectDto;
import ec.edu.espam.api.materia.exceptions.EntityNotFoundException;
import ec.edu.espam.api.materia.repository.SubjectRepository;
import ec.edu.espam.api.materia.service.SubjectService;
import ec.edu.espam.api.materia.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;
    @Override
    public SubjectDto create(SubjectDto dto) {
        log.info("Init method create");
        Subject subject = convertDtoToEntity(dto);
        log.info("End method create");
        return convertEntityToDto(this.repository.save(subject));
    }

    @Override
    public List<SubjectDto> getAll() {
        return repository.findAll().stream().map(this::convertEntityToDto).toList();
    }

    @Override
    public SubjectDto getById(long id) {
        return convertEntityToDto(getEntityById(id));
    }

    @Override
    public SubjectDto update(Long id, SubjectDto dto) {
        Subject subject = getEntityById(id);
        subject.setName(dto.getName());
        subject.setDescription(dto.getDescription());
        subject.setDate(dto.getDate());
        return convertEntityToDto(repository.save(subject));
    }

    @Override
    public void delete(Long id) {
        Subject subject = getEntityById(id);
        repository.delete(subject);
    }

    @Override
    public Subject getEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Subject not found"));
    }
    private SubjectDto convertEntityToDto(Subject subject) {
        return Mapper.modelMapper().map(subject, SubjectDto.class);
    }
    private Subject convertDtoToEntity(SubjectDto dto) {
        return Mapper.modelMapper().map(dto, Subject.class);
    }
}
