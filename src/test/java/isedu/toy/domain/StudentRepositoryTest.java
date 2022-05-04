package isedu.toy.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Order(1)
    @Test
    @Rollback(value = false)
    void save(){
        studentRepository.save(Student.builder()
                .name("홍길동")
                .parentId(1L)
                .regId(1L)
                .updId(1L)
                .build());
    }
    @Order(2)
    @Test
    void findById() {
        Student student = studentRepository.findById(1L);
        log.debug("student = {}", student);
        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(1L);
        assertThat(student.getParentId()).isEqualTo(1L);
        assertThat(student.getRegId()).isEqualTo(1L);
        assertThat(student.getRegDt()).isNotNull();
        assertThat(student.getUpdId()).isEqualTo(1L);
        assertThat(student.getUpdDt()).isNotNull();
    }
}