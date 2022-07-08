package com.denzhn.repo.commons.constants;

public enum ModuleNames {
    HOSPITAL_MODULE("hospital-module", "http://hospital-module"),
    DOCTOR_MODULE("doctor-module", "http://doctor-module"),
    PATIENT_MODULE("patient-module", "http://patient-module");

    private final String name;
    private final String uri;

    ModuleNames(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }
}
