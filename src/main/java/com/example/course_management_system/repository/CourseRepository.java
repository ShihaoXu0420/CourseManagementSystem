package com.example.course_management_system.repository;

import com.example.course_management_system.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Select("SELECT * FROM courses WHERE id = #{courseId}")
    Course findById(int courseId);

    @Select("SELECT * FROM courses")
    List<Course> findAll();

    @Insert("INSERT INTO courses (name, major_id, description) VALUES (#{name}, #{majorId}, #{description})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void save(Course course);

    @Insert("UPDATE courses SET name = #{name}, major_id = #{majorId}, description = #{description} WHERE id = #{id}")
    void update(Course course);

    @Delete("DELETE FROM courses WHERE id = #{courseId}")
    void delete(int courseId);

    List<Course> findByIds(int[] courseIds);

    @Select("SELECT * FROM courses WHERE name = #{course}")
    Course findByName(String course);
}
