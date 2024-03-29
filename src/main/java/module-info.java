module org.example.weatherfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires org.json;

    opens org.example.weatherfx to javafx.fxml;
    exports org.example.weatherfx;
    exports org.example.weatherfx.presentation.controllers;
    opens org.example.weatherfx.presentation.controllers to javafx.fxml;
    exports org.example.weatherfx.domain.models;
    opens org.example.weatherfx.domain.models to javafx.fxml;
}