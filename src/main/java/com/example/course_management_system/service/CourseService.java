package com.example.course_management_system.service;

import com.example.course_management_system.Exception.RecordNotFoundException;
import com.example.course_management_system.model.dto.CourseDto;
import com.example.course_management_system.model.dto.CourseListDto;
import com.example.course_management_system.model.entity.Course;
import com.example.course_management_system.model.entity.Major;
import com.example.course_management_system.repository.CourseRepository;
import com.example.course_management_system.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final MajorRepository majorRepository;
    private final MajorService majorService;

    public CourseDto getCourse(int courseId) {
        Course course = courseRepository.findById(courseId);

        if (course == null) {
            throw new RecordNotFoundException("Course not found");
        }

        Major major = majorRepository.findById(course.getMajorId());

        return new CourseDto(course.getId(), course.getName(), major.getName(), course.getDescription());
    }

    public CourseListDto getCourseList() {
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) return new CourseListDto(List.of());

        int[] majorIds = courses.stream()
                .mapToInt(Course::getMajorId)
                .toArray();

        Map<Integer, String> majorMap = majorService.getMajorMap(majorIds);

        List<CourseDto> courseDtos = courses.stream()
                .map(course -> {
                    String majorName = majorMap.get(course.getMajorId());
                    return new CourseDto(course.getId(), course.getName(), majorName, course.getDescription());
                }).toList();

        return new CourseListDto(courseDtos);
    }

    public CourseDto addCourse(CourseDto courseDto) {
        Major major = majorRepository.findByName(courseDto.getMajor());
        if (major == null) {
            throw new RecordNotFoundException("Major not found");
        }

        Course course = new Course(0, courseDto.getName(), major.getId(), courseDto.getDescription(), null, null);
        courseRepository.save(course);

        courseDto.setId(course.getId());
        return courseDto;
    }

    public CourseDto updateCourse(CourseDto courseDto) {
        Course course = courseRepository.findById(courseDto.getId());
        if (course == null) {
            throw new RecordNotFoundException("Course not found");
        }

        Major major = majorRepository.findByName(courseDto.getMajor());
        if (major == null) {
            throw new RecordNotFoundException("Major not found");
        }

        course.setName(courseDto.getName());
        course.setMajorId(major.getId());
        course.setDescription(courseDto.getDescription());

        courseRepository.update(course);

        return courseDto;
    }

    public void deleteCourse(int courseId) {
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new RecordNotFoundException("Course not found");
        }

        courseRepository.delete(courseId);
    }
}
