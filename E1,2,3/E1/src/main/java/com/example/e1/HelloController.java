package com.example.e1;

import Entity.Student;
import Sort.BubbleSort;
import Sort.InsertionSort;
import Sort.SelectionSort;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private TextField NameField;
    @FXML
    private TextField AgeField;
    @FXML
    private TextField ScoreField;

    private List<Student> students = new ArrayList<>();

    @FXML
    public void onSubmitButtonClick() {
        String name = NameField.getText();
        String ageText = AgeField.getText();
        String scoreText = ScoreField.getText();

        try {
            int age = Integer.parseInt(ageText);
            double score = Double.parseDouble(scoreText);

            Student student = new Student(name, age, score);
            students.add(student);

            NameField.clear();
            AgeField.clear();
            ScoreField.clear();

        } catch (NumberFormatException e) {
            showErrorAlert(e.getMessage());
        }
    }

    @FXML
    public void onBubbleSortButtonClick() {
        BubbleSort.sort(students);
        openSortedStudentsWindow("Bubble Sort");
    }

    @FXML
    public void onInsertionSortButtonClick() {
        InsertionSort.sort(students);
        openSortedStudentsWindow("Insertion Sort");
    }

    @FXML
    public void onSelectionSortButtonClick() {
        SelectionSort.sort(students);
        openSortedStudentsWindow("Selection Sort");
    }

    private void openSortedStudentsWindow(String title) {
        Stage sortedStage = new Stage();
        sortedStage.initModality(Modality.WINDOW_MODAL);
        sortedStage.setTitle(title);

        ListView<String> sortedListView = new ListView<>();
        for (Student student : students) {
            sortedListView.getItems().add(student.toString());
        }

        VBox vbox = new VBox(sortedListView);
        Scene scene = new Scene(vbox, 300, 200);
        sortedStage.setScene(scene);
        sortedStage.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}