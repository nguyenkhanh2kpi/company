module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.eclipse.angus.mail;

    requires org.apache.httpcomponents.httpclient;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.controllers;
    opens com.example.project.controllers to javafx.fxml;
// ????
    exports com.example.project.core.control;
    opens com.example.project.core.control to javafx.fxml;

    opens com.example.project.DTO to javafx.base;

}