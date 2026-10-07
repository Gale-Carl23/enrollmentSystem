package com.school.enrollment.repository;

import com.school.enrollment.config.TestDatabaseConnection;
import com.school.enrollment.config.TestDatabaseMigration;
import com.school.enrollment.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @BeforeAll
    static void setUpDatabase() {
        TestDatabaseMigration.migrate();
    }

    @Test
    void shouldSaveAndFindStudent() throws Exception {

        String uniqueCode =
        String.valueOf(System.currentTimeMillis());

        long departmentId =
                createDepartment(uniqueCode);

        long programId =
                createProgram(
                        departmentId,
                        uniqueCode
                );

        String studentNumber =
                "STUDENT-" + System.currentTimeMillis();

        Student student = new Student(
                null,
                studentNumber,
                "Juan",
                "Carlos",
                "Dela Cruz",
                null,
                LocalDate.of(2005, 5, 10),
                programId,
                LocalDate.of(2026, 8, 1),
                "ACTIVE"
        );

        StudentRepository repository =
            new StudentRepository(
                    TestDatabaseConnection::getConnection
            );

        Student saved =
                repository.save(student);

        assertNotNull(saved.getId());

        Optional<Student> found =
                repository.findById(saved.getId());

        assertTrue(found.isPresent());

        Student result = found.get();

        assertEquals(
                studentNumber,
                result.getStudentNumber()
        );

        assertEquals(
                "Juan",
                result.getFirstName()
        );

        assertEquals(
                "Dela Cruz",
                result.getLastName()
        );

        assertEquals(
                programId,
                result.getProgramId()
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
                        TestDatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    "D" + System.currentTimeMillis()
            );

            statement.setString(
                    2,
                    "Test Department"
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                resultSet.next();

                return resultSet.getLong("id");
            }
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
                        TestDatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setLong(
                    1,
                    departmentId
            );

            statement.setString(
                    2,
                    "PROG-" + uniqueCode
            );

            statement.setString(
                    3,
                    "Test Program"
            );

            statement.setInt(
                    4,
                    4
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                resultSet.next();

                return resultSet.getLong("id");
            }
        }
    }
}