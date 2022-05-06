package isedu.toy.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import isedu.toy.app.StudentReq;
import isedu.toy.domain.Student;
import isedu.toy.domain.StudentRepository;
import isedu.toy.query.NoStudentExecption;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentApiIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void findStudent_notexist() throws Exception {
        mockMvc.perform(get("/api/v1/students/{id}", "1"))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("404"))
                .andExpect(status().isOk());

    }

    @Order(2)
    @Test
//    @Rollback(value = false)
    public void save() throws Exception {
        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(StudentReq.builder().name("홍길동").build())))
                .andDo(print())
                .andExpect(status().isOk());

    }


    @Order(3)
    @Test
    void findStudent_exist() throws Exception, NoStudentExecption {
        mockMvc.perform(get("/api/v1/students/{id}", "1"))
                .andDo(print())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(status().isOk());

    }
}
