package com.denzhn.repo.commons.constants;

public enum ApiPaths {
    HOSPITAL_API("/api/hospital"),
    DOCTOR_API("/api/doctor"),
    PATIENT_API("api/patient");

    private final String value;

    ApiPaths(String path) {
        this.value = path;
    }

    public String getValue() {
        return value;
    }
}
