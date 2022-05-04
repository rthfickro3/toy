package isedu.toy.query.mapper;

import isedu.toy.domain.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    Student findById(Long id);
}
