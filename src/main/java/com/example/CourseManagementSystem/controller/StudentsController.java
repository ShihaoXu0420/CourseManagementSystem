package com.example.CourseManagementSystem.controller;

import com.example.CourseManagementSystem.model.dto.StudentDto;
import com.example.CourseManagementSystem.model.dto.StudentListDto;
import com.example.CourseManagementSystem.model.dto.Result;
import com.example.CourseManagementSystem.service.MajorService;
import com.example.CourseManagementSystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentService studentService;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private final MajorService majorService;

    @GetMapping("/{studentId}")
    public Result getStudent(@PathVariable("studentId") int studentId) {
        try {
            StudentDto studentDto = studentService.getStudent(studentId);
            return new Result(SUCCESS, studentDto);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @GetMapping
    public Result getAllStudents() {
        try {
            StudentListDto studentListDto = studentService.getStudentList();
            return new Result(SUCCESS, studentListDto);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @PostMapping
    public Result addStudent(@RequestBody StudentDto studentDto) {
        try {
            StudentDto studentResponse = studentService.addStudent(studentDto);

            return new Result(SUCCESS, studentResponse);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @PutMapping("/edit")
    public Result updateStudent(@RequestBody StudentDto studentDto) {
        try {
            StudentDto studentResponse = studentService.updateStudent(studentDto);
            return new Result(SUCCESS, studentResponse);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{studentId}")
    public Result deleteStudent(@PathVariable("studentId") int studentId) {
        try {
            studentService.deleteStudent(studentId);
            return new Result(SUCCESS, null);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }
}
