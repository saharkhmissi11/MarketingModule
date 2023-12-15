package com.Marketing.MarketingAPI.services;

import com.Marketing.MarketingAPI.DTO.ClientDTO;
import com.Marketing.MarketingAPI.DTO.FieldOfActivityDTO;
import com.Marketing.MarketingAPI.models.Client;
import com.Marketing.MarketingAPI.models.FieldOfActivity;
import com.Marketing.MarketingAPI.repositories.FieldOfActivityRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FieldOfActivityService {
    private  final FieldOfActivityRepo fieldOfActivityRepo;
    private final ModelMapper modelMapper ;
    public List<FieldOfActivityDTO> getAllFields() {
        List<FieldOfActivity> fieldOfActivities = fieldOfActivityRepo.findAll();
        return fieldOfActivities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public Optional<FieldOfActivityDTO> findCategoryById(Long id){
        if (id==0){log.error("Field is null");}
        Optional<FieldOfActivity> fieldOfActivity=fieldOfActivityRepo.findById(id);
        return fieldOfActivity.map(u->modelMapper.map(u, FieldOfActivityDTO.class));
    }
    public String findFieldNameById(Long id){
        if (id==0){log.error("Field is null");}
        FieldOfActivity fieldOfActivity=fieldOfActivityRepo.findById(id).get();
        return fieldOfActivity.getField();
    }
    public FieldOfActivityDTO addField (FieldOfActivityDTO fieldDto){
        FieldOfActivity field= modelMapper.map(fieldDto,FieldOfActivity.class);
        FieldOfActivity savedField = fieldOfActivityRepo.save(field);
        return modelMapper.map(savedField,FieldOfActivityDTO.class);
    }
    public FieldOfActivityDTO UpdateCategory(FieldOfActivityDTO categoryDto){
        FieldOfActivity category= modelMapper.map(categoryDto,FieldOfActivity.class);
        FieldOfActivity savedCategory = fieldOfActivityRepo.save(category);
        return modelMapper.map(savedCategory,FieldOfActivityDTO.class);
    }
    public void deleteFieldOfActivity(Long id) {
        fieldOfActivityRepo.deleteById(id);
    }
    private FieldOfActivityDTO convertToDto(FieldOfActivity field) {
        FieldOfActivityDTO fieldOfActivityDTO = modelMapper.map(field, FieldOfActivityDTO.class);
        return fieldOfActivityDTO;
    }
}