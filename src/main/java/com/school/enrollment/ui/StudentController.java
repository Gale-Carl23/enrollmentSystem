package com.school.enrollment.ui;

import com.school.enrollment.model.Student;
import com.school.enrollment.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class StudentController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> studentNumberColumn;

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TableColumn<Student, String> statusColumn;

    @FXML
    private Label resultLabel;

    @FXML
    private Button searchButton;

    private final StudentService studentService =
            new StudentService();

    private final ObservableList<Student> students =
            FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        studentNumberColumn.setCellValueFactory(
                new PropertyValueFactory<>("studentNumber")
        );

        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );

        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        studentTable.setItems(students);

        loadStudents();
    }

    @FXML
    private void handleSearch() {

        String studentNumber =
                searchField.getText().trim();

        if (studentNumber.isBlank()) {
            loadStudents();
            return;
        }

        try {

            studentService
                    .findStudentByStudentNumber(studentNumber)
                    .ifPresentOrElse(
                            student -> {
                                students.setAll(student);
                                resultLabel.setText(
                                        "Student found."
                                );
                            },
                            () -> {
                                students.clear();
                                resultLabel.setText(
                                        "No student found."
                                );
                            }
                    );

        } catch (Exception e) {

            showError(
                    "Search Error",
                    e.getMessage()
            );
        }
    }

    @FXML
    private void handleClearSearch() {

        searchField.clear();
        loadStudents();
    }

    private void loadStudents() {

        try {

            List<Student> result =
                    studentService.getAllStudents();

            students.setAll(result);

            resultLabel.setText(
                    result.size() + " student(s) found."
            );

        } catch (Exception e) {

            showError(
                    "Database Error",
                    e.getMessage()
            );
        }
    }

    private void showError(
            String title,
            String message
    ) {

        Alert alert = new Alert(
                Alert.AlertType.ERROR
        );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}