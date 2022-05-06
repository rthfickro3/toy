package isedu.toy.query;

import isedu.toy.domain.Student;
import isedu.toy.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentQueryService {
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public Student findStudent(Long id) throws NoStudentExecption {
        Student student = studentRepository.findById(id);
        if (student == null) throw new NoStudentExecption();
        return student;
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
