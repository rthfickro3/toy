package isedu.toy.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

public interface StudentRepository extends Repository<Student, Long> {
    Student findById(Long id);

    void save(Student student);

    Page<Student> findAll(Specification<Student> spec, Pageable pageable);
}
