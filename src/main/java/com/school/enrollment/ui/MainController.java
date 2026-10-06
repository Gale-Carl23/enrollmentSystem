package com.school.enrollment.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label pageTitle;

    @FXML
    private Label pageDescription;

    @FXML
    private void showDashboard() {
        pageTitle.setText("Dashboard");
        pageDescription.setText(
                "School Enrollment Management System dashboard."
        );
    }

    @FXML
    private void showStudents() {
        pageTitle.setText("Students");
        pageDescription.setText(
                "Student management will be implemented in a later stage."
        );
    }

    @FXML
    private void showEnrollment() {
        pageTitle.setText("Enrollment");
        pageDescription.setText(
                "Enrollment processing will be implemented in a later stage."
        );
    }

    @FXML
    private void showSubjects() {
        pageTitle.setText("Subjects");
        pageDescription.setText(
                "Subject management will be implemented in a later stage."
        );
    }

    @FXML
    private void showCurriculum() {
        pageTitle.setText("Curriculum");
        pageDescription.setText(
                "Curriculum management will be implemented in a later stage."
        );
    }

    @FXML
    private void showTeachers() {
        pageTitle.setText("Teachers");
        pageDescription.setText(
                "Teacher management will be implemented in a later stage."
        );
    }

    @FXML
    private void showRooms() {
        pageTitle.setText("Rooms");
        pageDescription.setText(
                "Room management will be implemented in a later stage."
        );
    }

    @FXML
    private void showReports() {
        pageTitle.setText("Reports");
        pageDescription.setText(
                "Reporting will be implemented in a later stage."
        );
    }
}