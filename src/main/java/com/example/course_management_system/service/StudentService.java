package com.example.course_management_system.service;

import com.example.course_management_system.Exception.RecordNotFoundException;
import com.example.course_management_system.model.dto.StudentDto;
import com.example.course_management_system.model.dto.StudentListDto;
import com.example.course_management_system.model.entity.Major;
import com.example.course_management_system.model.entity.Student;
import com.example.course_management_system.repository.MajorRepository;
import com.example.course_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final MajorRepository majorRepository;
    private final MajorService majorService;

    public StudentDto getStudent(int studentId) {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new RecordNotFoundException("Student not found");
        }

        Major studentMajor = majorRepository.findById(student.getMajorId());

        return new StudentDto(student.getId(), studentMajor.getName(), student.getName());
    }

    public StudentListDto getStudentList() {
        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()) return new StudentListDto(List.of());

        int[] majorIds = students.stream()
                .mapToInt(Student::getMajorId)
                .toArray();

        Map<Integer, String> majorMap = majorService.getMajorMap(majorIds);

        List<StudentDto> studentDtos = students.stream()
                .map(student -> {
                    String majorName = majorMap.get(student.getMajorId());
                    return new StudentDto(student.getId(), majorName, student.getName());
                }).toList();

        return new StudentListDto(studentDtos);
    }

    public StudentDto addStudent(StudentDto studentDto) {
        Major major = majorRepository.findByName(studentDto.getMajor());
        if (major == null) {
            throw new RecordNotFoundException("Major not found");
        }

        Student student = new Student(0, studentDto.getName(), major.getId(), null, null);

        studentRepository.save(student);

        studentDto.setId(student.getId());

        return studentDto;
    }

    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId());
        if (student == null) {
            throw new RecordNotFoundException("Student not found");
        }

        Major major = majorRepository.findByName(studentDto.getMajor());
        if (major == null) {
            throw new RecordNotFoundException("Major not found");
        }

        student.setMajorId(major.getId());
        student.setName(studentDto.getName());

        studentRepository.update(student);

        return new StudentDto(student.getId(), major.getName(), student.getName());
    }

    public void deleteStudent(int studentId) {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new RecordNotFoundException("Student not found");
        }

        studentRepository.delete(studentId);
    }

    public Map<Integer, String> getStudentMap(int[] studentIds) {
        List<Student> students = studentRepository.findByIds(studentIds);
        Map<Integer, String> studentNameMap = new HashMap<>();

        for (Student student: students) {
            studentNameMap.put(student.getId(), student.getName());
        }

        return studentNameMap;
    }
}
