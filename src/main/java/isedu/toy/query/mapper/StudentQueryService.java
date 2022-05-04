package isedu.toy.query.mapper;

import isedu.toy.domain.Student;
import isedu.toy.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class StudentQueryService {
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public Student findStudent(Long id){
       return studentRepository.findById(id);

    }
}
