module com.example.e1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.e1 to javafx.fxml;
    exports com.example.e1;
}