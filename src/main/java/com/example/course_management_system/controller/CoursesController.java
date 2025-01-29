package com.example.course_management_system.controller;

import com.example.course_management_system.model.dto.CourseDto;
import com.example.course_management_system.model.dto.CourseListDto;
import com.example.course_management_system.model.dto.Result;
import com.example.course_management_system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CoursesController {
    private final CourseService courseService;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @GetMapping("/{courseId}")
    public Result getCourse(@PathVariable("courseId") int courseId) {
        try {
            CourseDto courseDto = courseService.getCourse(courseId);
            return new Result(SUCCESS, courseDto);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @GetMapping
    public Result getAllCourses() {
        try {
            CourseListDto courseListDto = courseService.getCourseList();
            return new Result(SUCCESS, courseListDto);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @PostMapping
    public Result addCourse(@RequestBody CourseDto courseDto) {
        try {
            CourseDto courseResponse = courseService.addCourse(courseDto);
            return new Result(SUCCESS, courseResponse);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @PutMapping("/edit")
    public Result updateCourse(@RequestBody CourseDto courseDto) {
        try {
            CourseDto courseResponse = courseService.updateCourse(courseDto);
            return new Result(SUCCESS, courseResponse);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{courseId}")
    public Result deleteCourse(@PathVariable("courseId") int courseId) {
        try {
            courseService.deleteCourse(courseId);
            return new Result(SUCCESS, null);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }
}