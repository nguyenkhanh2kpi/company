package com.example.project.Untilities;

import com.example.project.core.enums.ToastStatus;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class CustomToast {
    public static final int LONG = 3500;
    public static final int SHORT = 500;
    public static final int TOP = 1;
    public static final int BUTTON = 0;

    private CustomToast() {}

    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay, int position, ToastStatus status) {

        int fadeInDelay = 500;
        int fadeOutDelay = 500;

        Stage toastStage = new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Roboto", 20));
        text.setFill(Color.LIGHTGRAY);

        StackPane root = new StackPane(text);
        switch (status) {
            case SUCCESS -> root.setStyle(" -fx-background-radius: 70; -fx-background-color: green");
            case FAIL ->  root.setStyle(" -fx-background-radius: 70; -fx-background-color: red");
            case WARNING ->  root.setStyle(" -fx-background-radius: 70; -fx-background-color: yellow");
            case INFO ->  root.setStyle(" -fx-background-radius: 70; -fx-background-color: blue");
        }
        root.setOpacity(80);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.setWidth(toastMsg.length() * 20);
        toastStage.setHeight(50);

        //set position toast
        if (position == BUTTON) {
//            toastStage.setY(ownerStage.getY() + (ownerStage.getHeight()) - 200);
//            toastStage.setX(ownerStage.getX() + ((ownerStage.getWidth() / 2) - (toastStage.getWidth() / 2)));
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2;
            double toastX = centerX - (toastStage.getWidth() / 2);
            double toastY = centerY - (toastStage.getHeight() / 2);
            toastStage.setX(toastX);
            toastStage.setY(toastY);

        } else {
            toastStage.setY(ownerStage.getY() + 10);
            toastStage.setX(ownerStage.getX() + ((ownerStage.getWidth() / 2) - (toastStage.getWidth() / 2)));
        }

        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) -> {
            new Thread(() -> {
                try {
                    Thread.sleep(toastDelay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Timeline fadeOutTimeline = new Timeline();
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());
                fadeOutTimeline.play();
            }).start();
        });
        fadeInTimeline.play();
    }

    /**
     * Create a Toast at the bottom of the reference stage and with the following editable parameters
     * @param ownerStage
     * @param toastMsg
     * @param toastDelay
     */
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay){
        makeText(ownerStage, toastMsg, toastDelay, BUTTON,ToastStatus.SUCCESS);
    }

//    /**
//     * Create a Toast at the bottom of the reference stage, with maximum display time and with the following editable parameter
//     * @param ownerStage
//     * @param toastMsg
//     */
    public static void toast(String msg,ToastStatus status){
        makeText(new Stage(), msg, LONG, BUTTON,status);
    }
}
