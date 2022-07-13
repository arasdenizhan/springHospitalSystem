package com.denzhn.hm.service.impl;

import com.denzhn.hm.feigns.DoctorFeign;
import com.denzhn.hm.helper.PopulateHelper;
import com.denzhn.hm.repository.HospitalRepository;
import com.denzhn.hm.service.HospitalService;
import com.denzhn.repo.commons.exceptions.BusinessLayerException;
import com.denzhn.repo.dm.model.Doctor;
import com.denzhn.repo.hm.dto.HospitalDto;
import com.denzhn.repo.hm.model.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository repository;
    private final DoctorFeign doctorFeign;

    @Override
    public HospitalDto create(Hospital hospital) throws BusinessLayerException {
        try {
            hospital.setDoctors(Collections.emptyList());
            return PopulateHelper.convertToHospitalDto(repository.save(hospital));
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public HospitalDto update(Long id, Doctor doctor) throws BusinessLayerException {
        try {
            Hospital hospital = repository.findById(id).orElse(null);
            if (Objects.isNull(hospital))
                return null;
            if (hospital.getDoctors().isEmpty()) {
                List<Long> doctorsList = new ArrayList<>();
                doctorsList.add(doctor.getId());
                hospital.setDoctors(doctorsList);
            } else {
                List<Long> doctorsList = new ArrayList<>(hospital.getDoctors());
                doctorsList.add(doctor.getId());
                hospital.setDoctors(doctorsList);
            }
            return PopulateHelper.convertToHospitalDto(repository.save(hospital));
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public HospitalDto updateDeletedDoctor(Long id, Doctor doctor) throws BusinessLayerException {
        try {
            Hospital hospital = repository.findById(id).orElse(null);
            if (Objects.isNull(hospital))
                return null;
            List<Long> doctorsList = new ArrayList<>(hospital.getDoctors());
            doctorsList.remove(doctor.getId());
            hospital.setDoctors(doctorsList);
            return PopulateHelper.convertToHospitalDto(repository.save(hospital));
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public HospitalDto get(Long id) throws BusinessLayerException {
        try {
            return PopulateHelper.convertToHospitalDto(repository.findById(id).orElse(null));
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Long id) throws BusinessLayerException {
        try {
            Hospital hospital = repository.findById(id).orElse(null);
            if (Objects.isNull(hospital))
                return false;
            doctorFeign.updateDeletedHospital(id);
            repository.delete(hospital);
            return true;
        } catch (Exception e) {
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }
}
