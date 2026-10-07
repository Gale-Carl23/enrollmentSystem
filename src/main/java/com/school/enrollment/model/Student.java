package com.school.enrollment.model;

import java.time.LocalDate;

public class Student {

    private Long id;
    private String studentNumber;

    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;

    private LocalDate dateOfBirth;

    private Long programId;

    private LocalDate admissionDate;

    private String status;

    public Student() {
    }

    public Student(
            Long id,
            String studentNumber,
            String firstName,
            String middleName,
            String lastName,
            String suffix,
            LocalDate dateOfBirth,
            Long programId,
            LocalDate admissionDate,
            String status
    ) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.dateOfBirth = dateOfBirth;
        this.programId = programId;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNumber='" + studentNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suffix='" + suffix + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", programId=" + programId +
                ", admissionDate=" + admissionDate +
                ", status='" + status + '\'' +
                '}';
    }
}