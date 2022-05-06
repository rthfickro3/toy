package isedu.toy.app;

import isedu.toy.domain.Student;
import isedu.toy.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentCreateService {

    private final StudentRepository studentRepository;

    @Transactional
    public void save(StudentReq studentReq) {
        Student student = Student.builder()
                .name(studentReq.getName())
                .build();
        studentRepository.save(student);
        studentRepository.findById(0L);

    }
}
