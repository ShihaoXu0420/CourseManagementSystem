package com.example.course_management_system.controller;

import com.example.course_management_system.model.dto.CourseRegistrationDto;
import com.example.course_management_system.model.dto.CourseRegistrationListDto;
import com.example.course_management_system.model.dto.Result;
import com.example.course_management_system.model.entity.CourseRegistration;
import com.example.course_management_system.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/course-registrations")
@RequiredArgsConstructor
public class CourseRegistrationController {
    private final CourseRegistrationService courseRegistrationService;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @GetMapping("/{registrationId}")
    public Result getRegistration(@PathVariable int registrationId) {
        try {
            CourseRegistrationDto courseRegistrationDto = courseRegistrationService.getRegistration(registrationId);
            return new Result(SUCCESS, courseRegistrationDto);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @GetMapping
    public Result getRegistrations() {
        try {
            CourseRegistrationListDto courseRegistrationListDto = courseRegistrationService.getRegistrations();
            return new Result(SUCCESS, courseRegistrationListDto);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @PostMapping
    public Result register(@RequestBody CourseRegistrationDto courseRegistrationDto) {
        try {
            CourseRegistrationDto courseRegistrationResponse = courseRegistrationService.register(courseRegistrationDto);
            return new Result(SUCCESS, courseRegistrationResponse);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @PutMapping("/edit")
    public Result editRegistration(@RequestBody CourseRegistrationDto courseRegistrationDto) {
        try {
            CourseRegistrationDto courseRegistrationResponse = courseRegistrationService.editRegistration(courseRegistrationDto);
            return new Result(SUCCESS, courseRegistrationResponse);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{registrationId}")
    public Result deleteRegistration(@PathVariable int registrationId) {
        try {
            courseRegistrationService.deleteRegistration(registrationId);
            return new Result(SUCCESS, null);
        } catch (Exception e) {
            return new Result(ERROR, e.getMessage());
        }
    }
}
