package com.healthcare.treatment_service.model;



public class Treatment {


    private String id;
    private String name;
    private int age;
    private String gender;
    private String blood_type;
    private String medical_Condition;
    private String date_admission;
    private String doctor;
    private String hospital;
    private String insurance_provider;
    private double billing_amount;
    private int room_number;
    private String admission_type;
    private String discharge_date;
    private String medication;
    private String test_results;

    public Treatment() {
    }

    public Treatment(String id, String name, int age, String admission_type, double billing_amount, String blood_type, String date_admission, String discharge_date, String doctor, String gender, String hospital, String insurance_provider, String medical_Condition, String medication, int room_number, String test_results) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.admission_type = admission_type;
        this.billing_amount = billing_amount;
        this.blood_type = blood_type;
        this.date_admission = date_admission;
        this.discharge_date = discharge_date;
        this.doctor = doctor;
        this.gender = gender;
        this.hospital = hospital;
        this.insurance_provider = insurance_provider;
        this.medical_Condition = medical_Condition;
        this.medication = medication;
        this.room_number = room_number;
        this.test_results = test_results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getMedical_Condition() {
        return medical_Condition;
    }

    public void setMedical_Condition(String medical_Condition) {
        this.medical_Condition = medical_Condition;
    }

    public double getBilling_amount() {
        return billing_amount;
    }

    public void setBilling_amount(double billing_amount) {
        this.billing_amount = billing_amount;
    }

    public String getDate_admission() {
        return date_admission;
    }

    public void setDate_admission(String date_admission) {
        this.date_admission = date_admission;
    }

    public String getDischarge_date() {
        return discharge_date;
    }

    public void setDischarge_date(String discharge_date) {
        this.discharge_date = discharge_date;
    }

    public String getAdmission_type() {
        return admission_type;
    }

    public void setAdmission_type(String admission_type) {
        this.admission_type = admission_type;
    }

    public String getInsurance_provider() {
        return insurance_provider;
    }

    public void setInsurance_provider(String insurance_provider) {
        this.insurance_provider = insurance_provider;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public String getTest_results() {
        return test_results;
    }

    public void setTest_results(String test_results) {
        this.test_results = test_results;
    }
}
