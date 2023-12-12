package br.com.fulltime.fullarm.app.javafx.controller.result.longframe.event;

import br.com.fulltime.fullarm.app.javafx.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class EventResultSceneControllerImpl implements EventResultSceneController {
    @FXML
    private Label packageLabel;
    @FXML
    private Label packageTypeLabel;
    @FXML
    private Label connectionTypeLabel;
    @FXML
    private Label accountLabel;
    @FXML
    private Label contactIdLabel;
    @FXML
    private Label qualifierLabel;
    @FXML
    private Label eventCodeLabel;
    @FXML
    private Label partitionLabel;
    @FXML
    private Label argumentLabel;
    @Autowired
    private SceneLoader sceneLoader;

    @Override
    public void displayResult(String rawPackage, List<String> message) {
        packageLabel.setText(rawPackage);
        packageTypeLabel.setText(message.get(0));
        connectionTypeLabel.setText(message.get(1));
        accountLabel.setText(message.get(2));
        contactIdLabel.setText(message.get(3));
        qualifierLabel.setText(message.get(4));
        eventCodeLabel.setText(message.get(5));
        partitionLabel.setText(message.get(6));
        argumentLabel.setText(message.get(7));
    }

    @Override
    public void returnToMainScene(ActionEvent event) {
        Parent root = sceneLoader.loadScene("/view/index.fxml");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
