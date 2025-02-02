package com.example.course_management_system.repository;

import com.example.course_management_system.model.entity.CourseRegistration;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRegistrationRepository {
    @Select("SELECT * FROM course_registrations WHERE id = #{registrationId}")
    CourseRegistration findById(int registrationId);

    @Select("SELECT * FROM course_registrations")
    List<CourseRegistration> findAll();

    @Insert("INSERT INTO course_registrations (course_id, student_id) VALUES (#{courseId}, #{studentId})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void save(CourseRegistration registration);

    @Update("UPDATE course_registrations SET course_id = #{courseId}, student_id = #{studentId} WHERE id = #{id}")
    void update(CourseRegistration registration);

    @Delete("DELETE FROM course_registrations WHERE id = #{registrationId}")
    void delete(int registrationId);
}
