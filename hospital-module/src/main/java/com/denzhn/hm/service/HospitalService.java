package com.denzhn.hm.service;

import com.denzhn.repo.commons.exceptions.BusinessLayerException;
import com.denzhn.repo.dm.model.Doctor;
import com.denzhn.repo.hm.dto.HospitalDto;
import com.denzhn.repo.hm.model.Hospital;

public interface HospitalService {
    HospitalDto create(Hospital hospital) throws BusinessLayerException;

    HospitalDto update(Long id, Doctor doctor) throws BusinessLayerException;

    HospitalDto updateDeletedDoctor(Long id, Doctor doctor) throws BusinessLayerException;

    HospitalDto get(Long id) throws BusinessLayerException;

    boolean delete(Long hospitalId) throws BusinessLayerException;
}
