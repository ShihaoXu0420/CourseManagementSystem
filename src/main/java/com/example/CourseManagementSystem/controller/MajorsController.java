package com.example.CourseManagementSystem.controller;

import com.example.CourseManagementSystem.model.dto.MajorDto;
import com.example.CourseManagementSystem.model.dto.MajorListDto;
import com.example.CourseManagementSystem.model.dto.Result;
import com.example.CourseManagementSystem.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/majors")
@RequiredArgsConstructor
public class MajorsController {
    private final MajorService majorService;
    private final String SUCCESS = "success";
    private final String ERROR = "error";

    @GetMapping("/{majorId}")
    public Result getMajor(@PathVariable("majorId") int majorId) {
        try {
            MajorDto majorDto = majorService.getMajor(majorId);
            return new Result(SUCCESS, majorDto);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @GetMapping
    public Result getAllMajors() {
        try {
            MajorListDto majorListDto = majorService.getMajorList();
            return new Result(SUCCESS, majorListDto);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @PostMapping
    public Result addMajor(@RequestBody MajorDto majorDto) {
        try {
            MajorDto majorResponse = majorService.addMajor(majorDto);
            return new Result(SUCCESS, majorResponse);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @PutMapping("/edit")
    public Result updateMajor(@RequestBody MajorDto majorDto) {
        try {
            MajorDto majorResponse = majorService.updateMajor(majorDto);
            return new Result(SUCCESS, majorResponse);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }
}
