package isedu.toy.query;

import isedu.toy.domain.Student;
import isedu.toy.domain.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class StudentQueryServiceTest {
    private StudentRepository studentRepository;

    private StudentQueryService studentQueryService;

    @BeforeEach
    public void beforeEach(){
        studentRepository = mock(StudentRepository.class);
        studentQueryService = new StudentQueryService(studentRepository);
    }

    @Test
    void findStudent() throws NoStudentExecption {

        given(studentRepository.findById(1L)).willReturn(Student.builder().id(1L).build());
        Student student = studentQueryService.findStudent(1L);
        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(1L);
    }
}