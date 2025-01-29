package com.example.course_management_system.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Course {
    private int id;
    private String name;
    private int majorId;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
