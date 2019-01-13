package sample;

import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Date;

public class Controller {
    static int count = 0;

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button button;

    public void sendMessage() {
        count++;
        if (!textField.getText().isEmpty()) {
            if (count % 2 == 0) {
                textArea.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                textArea.setStyle("-fx-text-fill: green; -fx-text-alignment: right;");
            } else {
                textArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                textArea.setStyle("-fx-text-fill: red; -fx-text-alignment: left;");
            }
            textArea.appendText(new Date() + ":\t" + textField.getText() + "\n");
            textField.clear();
            textField.requestFocus();
        }
    }
}
