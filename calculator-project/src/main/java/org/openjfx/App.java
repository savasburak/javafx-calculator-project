package org.openjfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class App extends Application {

    private TextField inputField;
    private TextField resultField;

    @Override
    public void start(Stage primaryStage) {
        inputField = new TextField();
        inputField.setEditable(false);
        inputField.setAlignment(Pos.BOTTOM_RIGHT);

        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setAlignment(Pos.BOTTOM_RIGHT);

        GridPane gridPane = createButtonGrid();
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(inputField);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(resultField);

        BorderPane.setMargin(resultField, new Insets(10, 10, 50, 10));

        Scene scene = new Scene(borderPane, 250, 450);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createButtonGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "+",
                "Clear", "0", ".", "-",
                "=", // Assuming "=" now takes its own row for a clearer layout
        };

        int buttonIndex = 0;
        // Adjusted for a 5x4 grid
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                if (buttonIndex >= buttonLabels.length)
                    break; // Prevents index out of bounds
                String label = buttonLabels[buttonIndex++];
                HsdButton button = new HsdButton(); // Assuming HsdButton is a class that extends Button
                button.setText(label);
                button.setOnAction(e -> onButtonPressed(label));
                gridPane.add(button, col, row);
            }
        }

        return gridPane;
    }

    private void onButtonPressed(String label) {
        if (label.equals("=")) {
            calculateResult();
        } else if (label.equals("Clear")) {
            inputField.clear();
            resultField.clear();
        } else {
            inputField.appendText(label);
        }
    }

    private void calculateResult() {
        try {
            Expression expression = new ExpressionBuilder(inputField.getText()).build();
            double result = expression.evaluate();
            resultField.setText(String.valueOf(result));
        } catch (Exception e) {
            resultField.setText("Error");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}