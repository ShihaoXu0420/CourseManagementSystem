package com.example.course_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MajorListDto {
    private List<MajorDto> majors;
}
