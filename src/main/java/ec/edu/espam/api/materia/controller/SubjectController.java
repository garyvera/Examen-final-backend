package ec.edu.espam.api.materia.controller;

import ec.edu.espam.api.materia.domain.dto.SubjectDto;
import ec.edu.espam.api.materia.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/subjects")
@Slf4j
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectDto> create(@RequestBody @Valid SubjectDto subject) {
        return new ResponseEntity<>(subjectService.create(subject),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getById(@PathVariable Long id) {
        log.info("Get By Id {}", id);
        return ResponseEntity.ok(subjectService.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAll() {
        return ResponseEntity.ok(subjectService.getAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> update(@PathVariable Long id, @Valid @RequestBody SubjectDto dto) {
        return ResponseEntity.ok(subjectService.update(id, dto));
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        subjectService.delete(id);
    }
}
