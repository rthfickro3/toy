package isedu.toy.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        for (int i = 0; i < 99; i++) {
            studentRepository.save(
                    Student.builder()
                            .name("홍길동"+i)
                            .parentId(1L)
                            .regId(1L)
                            .regDt(LocalDateTime.now())
                            .updId(1L)
                            .build());
        }

    }
    @Order(2)
    @Test
    void findById() {
        Student student = studentRepository.findById(1L);
        log.debug("student = {}", student);
        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(1L);
        assertThat(student.getName()).isEqualTo("홍길동"+0);
        assertThat(student.getParentId()).isEqualTo(1L);
        assertThat(student.getRegId()).isEqualTo(1L);
        assertThat(student.getRegDt()).isNotNull();
        assertThat(student.getUpdId()).isEqualTo(1L);
        assertThat(student.getUpdDt()).isNull();
    }
    @Order(3)
    @Test
    void findAll(){
        Page<Student> all = studentRepository.findAll(Specification.where(StudentSpecs.nameLike("홍길동")), PageRequest.of(2, 10));
        assertThat(all.getTotalElements()).isEqualTo(99);
        assertThat(all.getContent().get(0).getName()).isEqualTo("홍길동20");
        assertThat(all.getPageable().getPageSize()).isEqualTo(10);
        assertThat(all.getPageable().getPageNumber() * all.getPageable().getPageSize()).isEqualTo(20);
    }
}
