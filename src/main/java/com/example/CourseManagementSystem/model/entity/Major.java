package com.example.CourseManagementSystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Major {
    private int id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
