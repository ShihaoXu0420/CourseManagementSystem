package com.example.CourseManagementSystem.service;

import com.example.CourseManagementSystem.Exception.RecordNotFoundException;
import com.example.CourseManagementSystem.model.dto.MajorDto;
import com.example.CourseManagementSystem.model.dto.MajorListDto;
import com.example.CourseManagementSystem.model.entity.Major;
import com.example.CourseManagementSystem.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public MajorDto getMajor(int majorId) {
        Major major = majorRepository.findById(majorId);

        return new MajorDto(major.getId(), major.getName());
    }

    public MajorListDto getMajorList() {
        List<Major> majors = majorRepository.findAll();
        List<MajorDto> majorDtos = majors.stream()
                .map(major -> new MajorDto(major.getId(), major.getName()))
                .toList();

        return new MajorListDto(majorDtos);
    }

    public MajorDto addMajor(MajorDto majorDto) {
        Major major = new Major(0, majorDto.getName(), null, null);
        majorRepository.save(major);

        majorDto.setId(major.getId());

        return majorDto;
    }

    public MajorDto updateMajor(MajorDto majorDto) {
        Major major = majorRepository.findById(majorDto.getId());
        if (major == null) {
            throw new RecordNotFoundException("Major not found");
        }
        major.setName(majorDto.getName());
        majorRepository.update(major);
        major = majorRepository.findById(major.getId());

        return new MajorDto(major.getId(), major.getName());
    }

    public void deleteMajor(int majorId) {
        Major major = majorRepository.findById(majorId);
        if (major == null) {
            throw new RecordNotFoundException("Major not found");
        }
        majorRepository.delete(majorId);
    }
}
