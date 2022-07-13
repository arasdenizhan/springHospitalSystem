package com.denzhn.hm.helper;


import com.denzhn.repo.hm.dto.HospitalDto;
import com.denzhn.repo.hm.model.AddressModel;
import com.denzhn.repo.hm.model.Hospital;

public final class PopulateHelper {

    private static final String COMMA = ",";

    private PopulateHelper() {
        throw new UnsupportedOperationException();
    }

    public static HospitalDto convertToHospitalDto(Hospital hospital) {
        return HospitalDto.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .hospitalType(String.valueOf(hospital.getHospitalType()))
                .address(convertToAddressString(hospital.getAddress()))
                .doctors(hospital.getDoctors()).build();
    }

    public static String convertToAddressString(AddressModel addressModel) {
        return addressModel.getStreet() + COMMA + addressModel.getCity() + COMMA + addressModel.getZipCode();
    }
}
