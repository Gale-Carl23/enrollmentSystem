package com.school.enrollment.repository;

import com.school.enrollment.config.DatabaseConnection;
import com.school.enrollment.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    public Student save(Student student) throws SQLException {

        String sql = """
                INSERT INTO students (
                    student_number,
                    first_name,
                    middle_name,
                    last_name,
                    suffix,
                    date_of_birth,
                    program_id,
                    admission_date,
                    status
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getStudentNumber());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getMiddleName());
            statement.setString(4, student.getLastName());
            statement.setString(5, student.getSuffix());

            if (student.getDateOfBirth() != null) {
                statement.setDate(
                        6,
                        Date.valueOf(student.getDateOfBirth())
                );
            } else {
                statement.setNull(6, Types.DATE);
            }

            statement.setLong(7, student.getProgramId());

            statement.setDate(
                    8,
                    Date.valueOf(student.getAdmissionDate())
            );

            statement.setString(9, student.getStatus());

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    student.setId(resultSet.getLong("id"));
                }
            }
        }

        return student;
    }

    public Optional<Student> findById(Long id) throws SQLException {

        String sql = """
                SELECT
                    id,
                    student_number,
                    first_name,
                    middle_name,
                    last_name,
                    suffix,
                    date_of_birth,
                    program_id,
                    admission_date,
                    status
                FROM students
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        }

        return Optional.empty();
    }

    public Optional<Student> findByStudentNumber(
            String studentNumber
    ) throws SQLException {

        String sql = """
                SELECT
                    id,
                    student_number,
                    first_name,
                    middle_name,
                    last_name,
                    suffix,
                    date_of_birth,
                    program_id,
                    admission_date,
                    status
                FROM students
                WHERE student_number = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, studentNumber);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        }

        return Optional.empty();
    }

    public List<Student> findAll() throws SQLException {

        String sql = """
                SELECT
                    id,
                    student_number,
                    first_name,
                    middle_name,
                    last_name,
                    suffix,
                    date_of_birth,
                    program_id,
                    admission_date,
                    status
                FROM students
                ORDER BY last_name, first_name
                """;

        List<Student> students = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                students.add(mapRow(resultSet));
            }
        }

        return students;
    }

    private Student mapRow(ResultSet resultSet)
            throws SQLException {

        Date dateOfBirth =
                resultSet.getDate("date_of_birth");

        Date admissionDate =
                resultSet.getDate("admission_date");

        return new Student(
                resultSet.getLong("id"),
                resultSet.getString("student_number"),
                resultSet.getString("first_name"),
                resultSet.getString("middle_name"),
                resultSet.getString("last_name"),
                resultSet.getString("suffix"),
                dateOfBirth != null
                        ? dateOfBirth.toLocalDate()
                        : null,
                resultSet.getLong("program_id"),
                admissionDate.toLocalDate(),
                resultSet.getString("status")
        );
    }
}