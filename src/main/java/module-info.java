module com.nocturnal {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.nocturnal to javafx.fxml;
    exports com.nocturnal;
    exports com.model;
}
