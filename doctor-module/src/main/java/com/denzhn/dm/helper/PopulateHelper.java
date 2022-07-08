package com.denzhn.dm.helper;

import com.denzhn.repo.dm.dto.DoctorDto;
import com.denzhn.repo.dm.model.AddressModel;
import com.denzhn.repo.dm.model.Doctor;

import java.util.Objects;

public final class PopulateHelper {

    private static final String COMMA = ",";

    private PopulateHelper() {
        throw new UnsupportedOperationException();
    }

    public static DoctorDto convertToDoctorDto(Doctor doctor) {
        if (Objects.isNull(doctor))
            return null;
        return DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .type(String.valueOf(doctor.getDoctorType()))
                .address(convertToAddressString(doctor.getAddress()))
                .hospitalId(doctor.getHospitalId()).build();
    }

    public static String convertToAddressString(AddressModel addressModel) {
        return addressModel.getStreet() + COMMA + addressModel.getCity() + COMMA + addressModel.getZipCode();
    }
}
