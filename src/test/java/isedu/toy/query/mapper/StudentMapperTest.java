package isedu.toy.query.mapper;

import isedu.toy.domain.Student;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void findById() {
        Student student = studentMapper.findById(1L);
        assertThat(student).isNull();
    }
}