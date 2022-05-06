package isedu.toy.query;

import isedu.toy.domain.Student;
import isedu.toy.domain.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class StudentQueryServiceIT {
    @Autowired
    private StudentQueryService studentQueryService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void beforeEach(){
        studentRepository.save(Student.builder().id(1L).build());
    }

    @Test
    public void findOne() throws NoStudentExecption {

        Student student = studentQueryService.findStudent(1L);
        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(1L);
    }
}
