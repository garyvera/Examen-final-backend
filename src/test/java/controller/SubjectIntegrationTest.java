package controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espam.api.materia.domain.Subject;
import ec.edu.espam.api.materia.domain.dto.SubjectDto;
import ec.edu.espam.api.materia.repository.SubjectRepository;
import ec.edu.espam.api.materia.util.Mapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ec.edu.espam.api.materia.Application;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubjectIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    ObjectMapper objectMapper;
    static Subject subject;
    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

    @BeforeAll
    void preconditionCreate() {
        subject = subjectRepository.findByName("English")
                .orElseGet(() -> {
                    Subject newClient = Subject.builder()
                            .name("English")
                            .description("essential")
                            .date(LocalDate.now())
                            .build();
                    return subjectRepository.save(newClient);
                });
    }

    @Test
    void getById() throws Exception {
        preconditionCreate();

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/subjects/" + subject.getId()));
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$.name", is("English")));
    }
    private SubjectDto convertEntityToDto(Subject entity) {
        return Mapper.modelMapper().map(entity, SubjectDto.class);
    }
}
