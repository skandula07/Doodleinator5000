/**
 * Contains all the data used needed to build and use the module.
 */
module personalLibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires org.jetbrains.annotations;





    opens project to javafx.fxml;
    exports project;
    exports project.Controller;
    exports project.View;
    opens project.Controller to javafx.fxml;
}


