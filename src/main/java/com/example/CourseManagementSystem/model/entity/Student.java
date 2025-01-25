package com.example.CourseManagementSystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private int majorId;
    private Date createdAt;
    private Date updatedAt;
}
