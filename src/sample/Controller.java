package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalTime;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Controller {
    @FXML private ComboBox setHour, setMinute;
    @FXML private Pane background;
    @FXML private Label time, alertAt;
    @FXML private Button save;
    @FXML private ImageView sun, moon;
    private int second, hour, minute;
    AlarmClock alarmClock = new AlarmClock();
    private int alarmClockIsActive = -1;

    public void initialize() {
        sun.setOpacity(0);
        moon.setOpacity(0);
        background.setStyle("-fx-background-color: #373737");
        save.setStyle("-fx-background-color: #ffa826");
        setHour.setStyle("-fx-background-color: #ffa826");
        setMinute.setStyle("-fx-background-color: #ffa826");
        alertAt.setOpacity(0);
        for (int i = 0; i < 24; i++) {
            setHour.getItems().addAll(i);
        }
        setHour.setPromptText("Select Hour");
        for (int i = 0; i < 60; i++) {
            setMinute.getItems().addAll(i);
        }
        setMinute.setPromptText("Select Minute");
        currentTime();
    }

    public void currentTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            hour = LocalTime.now().getHour();
            minute = LocalTime.now().getMinute();
            second = LocalTime.now().getSecond();
            String h = String.format("%02d", hour);
            String m = String.format("%02d", minute);
            String s = String.format("%02d", second);
            time.setText(h + " : " + m + " : " + s);
            if (hour >= 6 && hour < 19) {
                sun.setOpacity(1);
                moon.setOpacity(0);
            } else {
                sun.setOpacity(0);
                moon.setOpacity(1);
            }
            if (alarmClockIsActive == 0 && alarmClock.check(hour, minute)){
                FXMLLoader alertWindow = new FXMLLoader(getClass().getResource("AlertWindow.fxml"));
                try {
                    Parent parent = alertWindow.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent, 600, 400));
                    stage.setResizable(false);
                    stage.show();
                    alarmClockIsActive = 1;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }


    @FXML
    public void handleSaveButton(ActionEvent actionEvent) throws IOException {
        if (setHour.getValue() == null && setMinute.getValue() == null) {
            error("Please select hour and minute!");
        } else if (setHour.getValue() == null) {
            error("Please select hour!");
        } else if (setMinute.getValue() == null) {
            error("Please select minute!");
        } else {
            try {
                if (save.getText().equals("Save")) {
                    String a = String.format("%02d", Integer.parseInt((String) setHour.getValue()));
                    String b = String.format("%02d", Integer.parseInt((String) setMinute.getValue()));
                    alarmClockIsActive = 0;
                    setHour.setDisable(true);
                    setMinute.setDisable(true);
                    alarmClock = new AlarmClock(Integer.parseInt((String) setHour.getValue()), Integer.parseInt((String) setMinute.getValue()));
                    alertAt.setText(a + " : " + b);
                    alertAt.setOpacity(100);
                    save.setText("Cancle");
                } else if (save.getText().equals("Cancle")) {
                    alertAt.setOpacity(0);
                    alarmClockIsActive = -1;
                    setHour.setDisable(false);
                    setMinute.setDisable(false);
                    save.setText("Save");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

    }

    public void error(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(err);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
