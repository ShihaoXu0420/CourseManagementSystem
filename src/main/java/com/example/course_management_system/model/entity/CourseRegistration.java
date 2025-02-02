package com.example.course_management_system.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CourseRegistration {
    private int id;
    private int courseId;
    private int studentId;
    private Date createdAt;
    private Date updatedAt;
}
