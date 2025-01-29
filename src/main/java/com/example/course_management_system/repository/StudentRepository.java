package com.example.course_management_system.repository;

import com.example.course_management_system.model.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students WHERE id = #{studentId}")
    Student findById(int studentId);

    @Select("SELECT * FROM students")
    List<Student> findAll();

    @Insert("INSERT INTO students (major_id, name) VALUES (#{majorId}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Student student);

    @Insert("UPDATE students SET major_id = #{majorId}, name = #{name} WHERE id = #{id}")
    void update(Student student);

    @Delete("DELETE FROM students WHERE id = #{studentId}")
    void delete(int studentId);
}
