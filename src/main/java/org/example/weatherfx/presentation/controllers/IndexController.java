package org.example.weatherfx.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Popup;
import org.example.weatherfx.domain.Domain;
import org.example.weatherfx.domain.JavaFXPresentation;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML
    private Button refreshButton;
    @FXML
    private Button addButton;
    @FXML
    private Button apikeyButton;
    @FXML
    private TextField cityInput;
    @FXML
    private Polygon prevButton;
    @FXML
    private Polygon nextButton;
    @FXML
    private Label cityLabel;
    @FXML
    private TextArea cityTextArea;

    JavaFXPresentation domain;
    int currentPage = 0;
    ArrayList<String> cityList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.domain = new Domain();
        this.cityList = new ArrayList<String>();

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (domain.addCity(cityInput.getText())) {
                    cityLabel.setText(domain.getCityName(currentPage));
                    cityTextArea.setText(domain.getCityData(currentPage));
                    cityInput.setText("");
                }
            }
        });

        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (currentPage >= domain.getListSize() - 1 || domain.isNull(currentPage + 1)) return;

                currentPage++;
                domain.updateCity(currentPage);

                cityLabel.setText(domain.getCityName(currentPage));
                cityTextArea.setText(domain.getCityData(currentPage));
            }
        });

        prevButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (currentPage <= 0 || domain.isNull(currentPage - 1)) return;

                currentPage--;
                domain.updateCity(currentPage);

                cityLabel.setText(domain.getCityName(currentPage));
                cityTextArea.setText(domain.getCityData(currentPage));
            }
        });

        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                domain.updateCity(currentPage);

                cityLabel.setText(domain.getCityName(currentPage));
                cityTextArea.setText(domain.getCityData(currentPage));
            }
        });

        apikeyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Popup popup = new Popup();
                VBox vbox = new VBox();
                Label popupLabel = new Label("Enter API Key:");
                TextField apiTextField = new TextField();
                Button popupButton = new Button("Enter");

                popupButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        domain.storeApiKey(apiTextField.getText());
                        domain.loadApiKey();
                        popup.hide();
                    }
                });

                vbox.setStyle("-fx-background-color: #F4F4F4");
                vbox.setAlignment(Pos.CENTER);
                vbox.setSpacing(20);
                vbox.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                vbox.getChildren().add(popupLabel);
                vbox.getChildren().add(apiTextField);
                vbox.getChildren().add(popupButton);

                popup.getContent().add(vbox);

                popup.show(apikeyButton.getScene().getWindow());
            }
        });
    }
}
