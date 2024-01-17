module org.example.weatherfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.weatherfx to javafx.fxml;
    exports org.example.weatherfx;
    exports org.example.weatherfx.controllers;
    opens org.example.weatherfx.controllers to javafx.fxml;
}