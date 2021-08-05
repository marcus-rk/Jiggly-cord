module com.example.javafx_course_3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafx_jigglycord to javafx.fxml;
    exports com.example.javafx_jigglycord;
}