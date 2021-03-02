package ru.geekbrains.java.level2.lesson4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    TextArea textArea;

    @FXML
    TextField messageField;

    public void sendMessage(ActionEvent actionEvent) {
        if (!messageField.getText().isEmpty()) {
            textArea.appendText(messageField.getText());
            textArea.appendText("\n");
            messageField.clear();
        }
    }
}
