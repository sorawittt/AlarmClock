package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


public class AlertController {
    @FXML private Label question;
    @FXML private TextField getMyAns;
    @FXML private Button ok;
    @FXML private Pane pane;
    private MediaPlayer mediaPlayer;
    Question q = new Question();
    private int ans;

    public AlertController() throws URISyntaxException {
    }

    public void initialize() {
        Media media = new Media(getClass().getResource("/sample/alarm.wav").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        mediaPlayer.play();
        pane.setStyle("-fx-background-color: #373737");
        ok.setStyle("-fx-background-color: #ffa826");
        getMyAns.setStyle("-fx-background-color: #ffa826");
        ans = q.getAns();
        question.setText(q.getQuestion());
    }

    @FXML
    public void handleOkButton(ActionEvent actionEvent) {
        System.out.println(getMyAns.getText());
        try {
            int myAns = Integer.parseInt(getMyAns.getText());
            if (myAns == ans) {
                Stage stage = (Stage) ok.getScene().getWindow();
                mediaPlayer.stop();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
                Controller controller =(Controller) loader.getController();
                //controller.handleSaveButton(actionEvent);
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Wrong answer!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

}
