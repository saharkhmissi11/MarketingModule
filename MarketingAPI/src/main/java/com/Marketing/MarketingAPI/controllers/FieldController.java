package com.Marketing.MarketingAPI.controllers;
import com.Marketing.MarketingAPI.DTO.ClientDTO;
import com.Marketing.MarketingAPI.DTO.FieldOfActivityDTO;
import com.Marketing.MarketingAPI.services.FieldOfActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/field")
public class FieldController {
    public final FieldOfActivityService fieldService;
    @GetMapping("/name/{id}")
    public ResponseEntity<String> getNameById(@PathVariable("id") Long id){
        String name = fieldService.findFieldNameById(id);
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<FieldOfActivityDTO> addField(@RequestBody FieldOfActivityDTO fieldDTO){
        return new ResponseEntity<>(fieldService.addField(fieldDTO), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<FieldOfActivityDTO>> getAllFields(){
        List<FieldOfActivityDTO> fields = fieldService.getAllFields();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }
}
