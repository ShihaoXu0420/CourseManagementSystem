package com.example.course_management_system.service;

import com.example.course_management_system.Exception.RecordNotFoundException;
import com.example.course_management_system.model.dto.MajorDto;
import com.example.course_management_system.model.dto.MajorListDto;
import com.example.course_management_system.model.entity.Major;
import com.example.course_management_system.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, String> getMajorMap(int[] ids) {
        List<Major> majors = majorRepository.findByIds(ids);
        HashMap<Integer, String> majorMap = new HashMap<>();
        for (Major major : majors) {
            majorMap.put(major.getId(), major.getName());
        }
        return majorMap;
    }
}
