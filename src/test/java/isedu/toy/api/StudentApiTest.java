package isedu.toy.api;

import isedu.toy.domain.Student;
import isedu.toy.query.NoStudentExecption;
import isedu.toy.query.StudentQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentApi.class)
class StudentApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentQueryService studentQueryService;

    @Test
    void findStudent_exist() throws Exception, NoStudentExecption {
        given(studentQueryService.findStudent(any())).willThrow(NoStudentExecption.class);
        mockMvc.perform(get("/api/v1/students/{id}", "1"))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("404"))
                .andExpect(status().isOk());

    }

    @Test
    void findStudent_notexist() throws Exception, NoStudentExecption {
        given(studentQueryService.findStudent(any())).willReturn(Student.builder().id(1L).build());
        mockMvc.perform(get("/api/v1/students/{id}", "1"))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(status().isOk());

    }
}