package isedu.toy.query.mapper;

import isedu.toy.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
    @Select("""
            select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.parent_id as parent_i3_0_0_, student0_.reg_dt as reg_dt4_0_0_, student0_.reg_id as reg_id5_0_0_, student0_.upd_dt as upd_dt6_0_0_, student0_.upd_id as upd_id7_0_0_ 
            from student student0_ where student0_.id=#{id}
            """)
    Student findById(@Param("id") Long id);
}
