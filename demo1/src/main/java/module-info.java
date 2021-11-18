module bg.tu_varna.sit.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;



    exports bg.tu_varna.sit.demo1.application;
    opens bg.tu_varna.sit.demo1.application to javafx.fxml;



    exports bg.tu_varna.sit.demo1.presentation.controllers;
    opens bg.tu_varna.sit.demo1.presentation.controllers to javafx.fxml;
}