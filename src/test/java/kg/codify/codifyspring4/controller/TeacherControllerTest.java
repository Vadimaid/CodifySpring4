package kg.codify.codifyspring4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.codify.codifyspring4.dto.SubjectCreateDto;
import kg.codify.codifyspring4.dto.TeacherCreateDto;
import kg.codify.codifyspring4.dto.TeacherDto;
import kg.codify.codifyspring4.entity.ClassEntity;
import kg.codify.codifyspring4.entity.SubjectEntity;
import kg.codify.codifyspring4.entity.TeacherEntity;
import kg.codify.codifyspring4.repository.ClassEntityRepository;
import kg.codify.codifyspring4.repository.SubjectRepository;
import kg.codify.codifyspring4.repository.TeacherEntityRepository;
import kg.codify.codifyspring4.service.TeacherService;
import kg.codify.codifyspring4.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest(
        properties = {
                "spring.flyway.enabled=false",
                "spring.datasource.driver-class-name=org.h2.Driver",
                "spring.datasource.username=h2",
                "spring.datasource.password=pass",
                "spring.datasource.url=jdbc:h2:mem:test_db",
                "spring.jpa.hibernate.ddl-auto=create",
                "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class TeacherControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    private int port;

    private static final String BASE_URL = "http://localhost:";

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private TeacherEntityRepository repository;

    @SpyBean
    private ClassEntityRepository classRepository;

    @WithMockUser(username = "test", roles = "USER")
    @Test
    public void testById_OK() throws Exception {
        Long testId = 1L;
        final String testValue = "Test";

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(testId);
        teacherEntity.setName(testValue);
        teacherEntity.setSurname(testValue);
        teacherEntity.setClasses(Collections.singletonList(new ClassEntity(testId, testValue)));
        teacherEntity.setSubject(new SubjectEntity(testId, testValue));

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(teacherEntity));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(BASE_URL + port + "/teacher/" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(testValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subject").value(testValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$.classes").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.classes.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.classes[0]").value(testValue));
    }

    @WithMockUser(username = "user", roles = "ADMIN")
    @Test
    public void testById_Forbidden() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(BASE_URL + port + "/teacher/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @WithMockUser(username = "user", roles = "ADMIN")
    @Test
    public void testHireNewTeacher_OK() throws Exception {
        Long testId = 1L;
        final String testValue = "Test";

        SubjectCreateDto subjectCreateDto = new SubjectCreateDto();
        subjectCreateDto.setTitle(testValue);

        TeacherCreateDto createDto = new TeacherCreateDto();
        createDto.setName(testValue);
        createDto.setSurname(testValue);
        createDto.setClasses(Collections.singletonList(testId));
        createDto.setSubject(subjectCreateDto);

        Mockito
                .when(classRepository.findById(anyLong()))
                .thenReturn(Optional.of(new ClassEntity(testId, testValue)));

//        TeacherEntity teacherEntity = new TeacherEntity();
//        teacherEntity.setId(testId);
//        teacherEntity.setName(testValue);
//        teacherEntity.setSurname(testValue);
//        teacherEntity.setClasses(Collections.singletonList(new ClassEntity(testId, testValue)));
//        teacherEntity.setSubject(new SubjectEntity(testId, testValue));
//
//        Mockito.when(teacherEntityRepository.save(any())).thenReturn(teacherEntity);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(BASE_URL + port + "/teacher/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(testValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subject").value(testValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$.classes").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.classes.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.classes[0]").value(testValue));
    }

}