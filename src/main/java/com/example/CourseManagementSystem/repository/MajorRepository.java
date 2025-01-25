package com.example.CourseManagementSystem.repository;

import com.example.CourseManagementSystem.model.entity.Major;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MajorRepository {
    @Select("SELECT * FROM majors WHERE id = #{majorId}")
    Major findById(int majorId);

    @Select("SELECT * FROM majors")
    List<Major> findAll();

    @Insert("INSERT INTO majors (name) VALUES (#{name})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void save(Major major);

    @Update("UPDATE majors SET name = #{name} WHERE id = #{id}")
    void update(Major major);

    @Delete("DELETE FROM majors WHERE id = #{majorId}")
    void delete(int majorId);

    List<Major> findByIds(@Param("ids") int[] ids);

    @Select("SELECT * FROM majors WHERE name = #{major}")
    Major findByName(String major);
}
