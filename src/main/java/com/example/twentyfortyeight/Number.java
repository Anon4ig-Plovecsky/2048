package com.example.twentyfortyeight;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Number {
    private final Pane frame;
    private final StackPane stackPane;
    private Label number;
    private Rectangle rectangle;
    public Number(Pane pane, int value) {
        this.frame = pane;
        stackPane = new StackPane();
        stackPane.setPrefSize(112.5, 112.5);
        number = new Label(Integer.toString(value));
        rectangle = new Rectangle();
        rectangle.setHeight(112.5);
        rectangle.setWidth(112.5);
        setNumber(value);
        stackPane.getChildren().addAll(rectangle, number);
        frame.getChildren().add(stackPane);
    }
    public void setNumber(int value) {
        number.setText(Integer.toString(value));
        switch(value) {
            case 2 -> rectangle.setFill(Color.rgb(126, 0, 95));
            case 4 -> rectangle.setFill(Color.rgb(13, 126, 5));
            case 8 -> rectangle.setFill(Color.rgb(126, 68, 21));
            case 16 -> rectangle.setFill(Color.rgb(35, 124, 126));
            case 32 -> rectangle.setFill(Color.rgb(116, 239, 126));
            case 64 -> rectangle.setFill(Color.rgb(17, 9, 126));
            case 128 -> rectangle.setFill(Color.rgb(126, 126, 4));
            case 256 -> rectangle.setFill(Color.rgb(126, 3, 77));
            case 512 -> rectangle.setFill(Color.rgb(3, 126, 75));
            case 1024 -> rectangle.setFill(Color.rgb(126, 7, 7));
            default -> {
                rectangle.setFill(Color.rgb(255, 255, 255));
                number.setTextFill(Color.BLACK);
            }
        }
    }
    public void setCoord(double x, double y) {
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
    }
    public void removeNumber() {
        stackPane.getChildren().removeAll(rectangle, number);
        frame.getChildren().remove(stackPane);
    }
}
