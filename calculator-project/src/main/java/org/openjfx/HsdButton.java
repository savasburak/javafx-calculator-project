package org.openjfx;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class HsdButton extends Button {

    public HsdButton() {
        super();
        setMinSize(50, 50);
        setStyle("-fx-background-color: white; -fx-border-color: transparent; -fx-border-width: 2;");
        initClickAnimation();
        initHoverEffect();
    }

    private void initClickAnimation(){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(40), this);
        scaleTransition.setByX(0.2);
        scaleTransition.setByY(0.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);

        this.setOnMouseClicked(e -> scaleTransition.playFromStart());
    }
    
    private void initHoverEffect() {
        // to make the border black on hover mode
        this.setOnMouseEntered(e -> this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2;"));

        // to revert to normal color
        this.setOnMouseExited(e -> this.setStyle("-fx-background-color: white; -fx-border-color: transparent; -fx-border-width: 2;"));
    }
}