module com.example.twentyfortyeight {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.twentyfortyeight to javafx.fxml;
    exports com.example.twentyfortyeight;
}