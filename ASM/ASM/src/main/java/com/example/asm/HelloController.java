package com.example.asm;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TableView<ExpenseRecord> tableView;

    @FXML
    private TableColumn<ExpenseRecord, String> incomeColumn;

    @FXML
    private TableColumn<ExpenseRecord, String> expenseColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Thiết lập các cột sử dụng các thuộc tính của ExpenseRecord
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("income"));
        expenseColumn.setCellValueFactory(new PropertyValueFactory<>("expense"));

        loadData(); // Tải dữ liệu khi khởi tạo
    }

    private void loadData() {
        ObservableList<ExpenseRecord> data = FXCollections.observableArrayList();
        try (InputStream inputStream = getClass().getResourceAsStream("/com/example/assignment/ThuChi.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            br.readLine(); // Bỏ qua tiêu đề
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(new ExpenseRecord(values[0], values[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableView.setItems(data);
    }
}