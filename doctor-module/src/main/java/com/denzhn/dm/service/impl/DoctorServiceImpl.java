package com.denzhn.dm.service.impl;

import com.denzhn.dm.feigns.HospitalFeign;
import com.denzhn.dm.helper.PopulateHelper;
import com.denzhn.dm.repository.DoctorRepository;
import com.denzhn.dm.service.DoctorService;
import com.denzhn.repo.commons.exceptions.BusinessLayerException;
import com.denzhn.repo.dm.dto.DoctorDto;
import com.denzhn.repo.dm.model.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository repository;
    private final HospitalFeign hospitalFeign;

    @Override
    public DoctorDto create(Doctor doctor) throws BusinessLayerException {
        try {
            Doctor savedEntity = repository.save(doctor);
            hospitalFeign.updateDoctorInformation(savedEntity.getHospitalId(), savedEntity);
            return PopulateHelper.convertToDoctorDto(savedEntity);
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public DoctorDto get(Long id) throws BusinessLayerException {
        try {
            return PopulateHelper.convertToDoctorDto(repository.findById(id).orElse(null));
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public DoctorDto update(Long id, Long hospitalId) throws BusinessLayerException {
        try {
            Doctor doctor = repository.findById(id).orElse(null);
            if (Objects.isNull(doctor))
                return null;
            hospitalFeign.updateDeletedDoctor(id, doctor);
            doctor.setHospitalId(hospitalId);
            hospitalFeign.updateDoctorInformation(hospitalId, doctor);
            return PopulateHelper.convertToDoctorDto(repository.save(doctor));
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Long doctorId) throws BusinessLayerException {
        try {
            Doctor doctor = repository.findById(doctorId).orElse(null);
            if (Objects.isNull(doctor))
                return false;
            repository.delete(doctor);
            ResponseEntity<String> updateResponse = hospitalFeign.updateDeletedDoctor(doctor.getHospitalId(), doctor);
            return HttpStatus.OK.equals(updateResponse.getStatusCode());
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public List<DoctorDto> list() throws BusinessLayerException {
        try {
            return repository.findAll().stream().map(PopulateHelper::convertToDoctorDto).toList();
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }
}
