package com.school.enrollment.service;

import com.school.enrollment.config.DatabaseConnection;
import com.school.enrollment.model.Student;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void shouldRegisterValidStudent() throws Exception {

        String uniqueCode =
                String.valueOf(System.currentTimeMillis());

        long departmentId =
                createDepartment(uniqueCode);

        long programId =
                createProgram(
                        departmentId,
                        uniqueCode
                );

        Student student = new Student(
                null,
                "S-" + uniqueCode,
                "Maria",
                null,
                "Santos",
                null,
                LocalDate.of(2005, 3, 15),
                programId,
                LocalDate.of(2026, 8, 1),
                "ACTIVE"
        );

        StudentService service =
                new StudentService();

        Student registered =
                service.registerStudent(student);

        assertNotNull(registered.getId());
        assertEquals(
                student.getStudentNumber(),
                registered.getStudentNumber()
        );
    }

    @Test
    void shouldRejectStudentWithoutStudentNumber() {

        Student student = new Student(
                null,
                "",
                "Maria",
                null,
                "Santos",
                null,
                null,
                1L,
                LocalDate.of(2026, 8, 1),
                "ACTIVE"
        );

        StudentService service =
                new StudentService();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.registerStudent(student)
        );
    }

    @Test
    void shouldRejectStudentWithoutFirstName() {

        Student student = new Student(
                null,
                "TEST-INVALID-FIRST",
                "",
                null,
                "Santos",
                null,
                null,
                1L,
                LocalDate.of(2026, 8, 1),
                "ACTIVE"
        );

        StudentService service =
                new StudentService();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.registerStudent(student)
        );
    }

    @Test
    void shouldRejectStudentWithoutLastName() {

        Student student = new Student(
                null,
                "TEST-INVALID-LAST",
                "Maria",
                null,
                "",
                null,
                null,
                1L,
                LocalDate.of(2026, 8, 1),
                "ACTIVE"
        );

        StudentService service =
                new StudentService();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.registerStudent(student)
        );
    }

    @Test
    void shouldRejectStudentWithoutProgram() {

        Student student = new Student(
                null,
                "TEST-NO-PROGRAM",
                "Maria",
                null,
                "Santos",
                null,
                null,
                null,
                LocalDate.of(2026, 8, 1),
                "ACTIVE"
        );

        StudentService service =
                new StudentService();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.registerStudent(student)
        );
    }

    @Test
void shouldRejectStudentWithoutAdmissionDate() {

    Student student = new Student(
            null,
            "TEST-NO-DATE",
            "Maria",
            null,
            "Santos",
            null,
            null,
            1L,
            null,
            "ACTIVE"
            );

            StudentService service =
                    new StudentService();

            assertThrows(
                    IllegalArgumentException.class,
                    () -> service.registerStudent(student)
            );
        }

                

    private long createDepartment(
            String uniqueCode
    ) throws Exception {

        String sql = """
                INSERT INTO departments (
                    code,
                    name
                )
                VALUES (?, ?)
                RETURNING id
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    "D" + uniqueCode
            );

            statement.setString(
                    2,
                    "Service Test Department"
            );

            var resultSet =
                    statement.executeQuery();

            resultSet.next();

            return resultSet.getLong("id");
        }
    }

    private long createProgram(
            long departmentId,
            String uniqueCode
    ) throws Exception {

        String sql = """
                INSERT INTO programs (
                    department_id,
                    code,
                    name,
                    duration_years
                )
                VALUES (?, ?, ?, ?)
                RETURNING id
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setLong(
                    1,
                    departmentId
            );

            statement.setString(
                    2,
                    "P" + uniqueCode
            );

            statement.setString(
                    3,
                    "Service Test Program"
            );

            statement.setInt(
                    4,
                    4
            );

            var resultSet =
                    statement.executeQuery();

            resultSet.next();

            return resultSet.getLong("id");
        }
    }
}