package com.example.course_management_system.service;

import com.example.course_management_system.Exception.RecordNotFoundException;
import com.example.course_management_system.model.dto.CourseRegistrationDto;
import com.example.course_management_system.model.dto.CourseRegistrationListDto;
import com.example.course_management_system.model.entity.Course;
import com.example.course_management_system.model.entity.CourseRegistration;
import com.example.course_management_system.model.entity.Student;
import com.example.course_management_system.repository.CourseRegistrationRepository;
import com.example.course_management_system.repository.CourseRepository;
import com.example.course_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseRegistrationService {
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public CourseRegistrationDto getRegistration(int registrationId) {
        CourseRegistration registration = courseRegistrationRepository.findById(registrationId);
        if (registration == null) throw new RecordNotFoundException("Registration not found");

        Student student = studentRepository.findById(registration.getStudentId());
        if (student == null) throw new RecordNotFoundException("Student not found");

        Course course = courseRepository.findById(registration.getCourseId());
        if (course == null) throw new RecordNotFoundException("Course not found");

        return new CourseRegistrationDto(registration.getId(), course.getName(), student.getName());
    }

    public CourseRegistrationListDto getRegistrations() {
        List<CourseRegistration> registrations = courseRegistrationRepository.findAll();
        if (registrations.isEmpty()) return new CourseRegistrationListDto(List.of());

        int[] studentIds = registrations.stream()
                .mapToInt(CourseRegistration::getStudentId)
                .toArray();
        int[] courseIds = registrations.stream()
                .mapToInt(CourseRegistration::getCourseId)
                .toArray();

        Map<Integer, String> studentMap = studentService.getStudentMap(studentIds);
        Map<Integer, String> courseMap = courseService.getCourseMap(courseIds);

        List<CourseRegistrationDto> registrationDtos = registrations.stream()
                .map(registration -> {
                    String studentName = studentMap.get(registration.getStudentId());
                    String courseName = courseMap.get(registration.getCourseId());
                    return new CourseRegistrationDto(registration.getId(), courseName, studentName);
                }).toList();

        return new CourseRegistrationListDto(registrationDtos);
    }

    public CourseRegistrationDto register(CourseRegistrationDto courseRegistrationDto) {
        Course course = courseRepository.findByName(courseRegistrationDto.getCourseName());
        if (course == null) throw new RecordNotFoundException("Course not found");

        Student student = studentRepository.findByName(courseRegistrationDto.getStudentName());
        if (student == null) throw new RecordNotFoundException("Student not found");

        CourseRegistration registration = new CourseRegistration(0, course.getId(), student.getId(), null, null);
        courseRegistrationRepository.save(registration);

        courseRegistrationDto.setId(registration.getId());

        return courseRegistrationDto;
    }

    public CourseRegistrationDto editRegistration(CourseRegistrationDto courseRegistrationDto) {
        CourseRegistration registration = courseRegistrationRepository.findById(courseRegistrationDto.getId());
        if (registration == null) throw new RecordNotFoundException("Registration not found");

        Course course = courseRepository.findByName(courseRegistrationDto.getCourseName());
        if (course == null) throw new RecordNotFoundException("Course not found");

        Student student = studentRepository.findByName(courseRegistrationDto.getStudentName());
        if (student == null) throw new RecordNotFoundException("Student not found");

        registration.setCourseId(course.getId());
        registration.setStudentId(student.getId());

        courseRegistrationRepository.update(registration);

        return courseRegistrationDto;
    }

    public void deleteRegistration(int registrationId) {
        CourseRegistration registration = courseRegistrationRepository.findById(registrationId);
        if (registration == null) throw new RecordNotFoundException("Registration not found");

        courseRegistrationRepository.delete(registrationId);
    }
}
