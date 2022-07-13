package com.denzhn.hm.controller;

import com.denzhn.hm.service.HospitalService;
import com.denzhn.repo.commons.exceptions.BusinessLayerException;
import com.denzhn.repo.dm.model.Doctor;
import com.denzhn.repo.hm.dto.HospitalDto;
import com.denzhn.repo.hm.dto.HospitalUpdateDto;
import com.denzhn.repo.hm.model.Hospital;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService service;
    private final ObjectMapper objectMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@Valid @RequestBody Hospital hospital) {
        try {
            HospitalDto hospitalDto = service.create(hospital);
            return Objects.isNull(hospitalDto) ?
                    ResponseEntity.internalServerError().build() :
                    ResponseEntity.ok(objectMapper.writeValueAsString(hospitalDto));
        } catch (JsonProcessingException | BusinessLayerException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable Long id) {
        try {
            HospitalDto hospitalDto = service.get(id);
            return Objects.isNull(hospitalDto) ?
                    ResponseEntity.notFound().build() :
                    ResponseEntity.ok(objectMapper.writeValueAsString(hospitalDto));
        } catch (JsonProcessingException | BusinessLayerException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list() {
        try {
            List<HospitalDto> list = service.list();
            return BooleanUtils.isTrue(list.isEmpty()) ?
                    ResponseEntity.notFound().build() :
                    ResponseEntity.ok(objectMapper.writeValueAsString(list));
        } catch (JsonProcessingException | BusinessLayerException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            boolean serviceResult = service.delete(id);
            return BooleanUtils.isFalse(serviceResult) ?
                    ResponseEntity.notFound().build() :
                    ResponseEntity.ok("Entity with: " + id + " deleted.");
        } catch (BusinessLayerException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@Valid @RequestBody HospitalUpdateDto dto) {
        try {
            HospitalDto hospitalDto = service.update(dto);
            return Objects.isNull(hospitalDto) ?
                    ResponseEntity.internalServerError().build() :
                    ResponseEntity.ok(objectMapper.writeValueAsString(hospitalDto));
        } catch (JsonProcessingException | BusinessLayerException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping(path = "/updated-doctor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateDoctorInformation(@PathVariable Long id, @RequestBody Doctor doctor) {
        try {
            HospitalDto hospitalDto = service.updateDoctorInfo(id, doctor);
            return Objects.isNull(hospitalDto) ?
                    ResponseEntity.internalServerError().build() :
                    ResponseEntity.ok(objectMapper.writeValueAsString(hospitalDto));
        } catch (JsonProcessingException | BusinessLayerException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping(path = "/deleted-doctor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateDeletedDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        try {
            HospitalDto hospitalDto = service.updateDeletedDoctor(id, doctor);
            return Objects.isNull(hospitalDto) ?
                    ResponseEntity.internalServerError().build() :
                    ResponseEntity.ok(objectMapper.writeValueAsString(hospitalDto));
        } catch (JsonProcessingException | BusinessLayerException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
