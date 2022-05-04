package isedu.toy.domain;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Student.class)
public class Student_ {
    public static volatile SingularAttribute<Student, Long> id;
    public static volatile SingularAttribute<Student, String> name;
}
