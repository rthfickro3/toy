package isedu.toy.domain;

import org.springframework.data.jpa.domain.Specification;

public class StudentSpecs {
    public static Specification<Student> id(Long id){
        return (root, query, cb) -> cb.equal(root.get(Student_.id), id);
    }

    public static Specification<Student> nameLike(String name){
        return (root, query, cb) -> cb.like(root.get(Student_.name), "%"+name+"%");
    }
}
