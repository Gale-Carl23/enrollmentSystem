package com.school.enrollment.service;

import com.school.enrollment.model.Student;
import com.school.enrollment.repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    public Student registerStudent(Student student)
            throws SQLException {

        validateStudent(student);

        Optional<Student> existingStudent =
                studentRepository.findByStudentNumber(
                        student.getStudentNumber()
                );

        if (existingStudent.isPresent()) {
            throw new IllegalArgumentException(
                    "Student number already exists: "
                            + student.getStudentNumber()
            );
        }

        return studentRepository.save(student);
    }

    public Optional<Student> findStudentById(Long id)
            throws SQLException {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be greater than zero."
            );
        }

        return studentRepository.findById(id);
    }

    public Optional<Student> findStudentByStudentNumber(
            String studentNumber
    ) throws SQLException {

        if (studentNumber == null ||
                studentNumber.isBlank()) {

            throw new IllegalArgumentException(
                    "Student number is required."
            );
        }

        return studentRepository.findByStudentNumber(
                studentNumber.trim()
        );
    }

    public List<Student> getAllStudents()
            throws SQLException {

        return studentRepository.findAll();
    }

    private void validateStudent(Student student) {

        if (student == null) {
            throw new IllegalArgumentException(
                    "Student cannot be null."
            );
        }

        if (student.getStudentNumber() == null ||
                student.getStudentNumber().isBlank()) {

            throw new IllegalArgumentException(
                    "Student number is required."
            );
        }

        if (student.getFirstName() == null ||
                student.getFirstName().isBlank()) {

            throw new IllegalArgumentException(
                    "First name is required."
            );
        }

        if (student.getLastName() == null ||
                student.getLastName().isBlank()) {

            throw new IllegalArgumentException(
                    "Last name is required."
            );
        }

        if (student.getProgramId() == null ||
                student.getProgramId() <= 0) {

            throw new IllegalArgumentException(
                    "Program is required."
            );
        }

        if (student.getAdmissionDate() == null) {
            throw new IllegalArgumentException(
                    "Admission date is required."
            );
        }

        if (student.getStatus() == null ||
                student.getStatus().isBlank()) {

            throw new IllegalArgumentException(
                    "Student status is required."
            );
        }
    }
}