package br.com.fulltime.fullarm.app.javafx.controller.result.longframe.command;

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
public class CommandResultSceneControllerImpl implements CommandResultSceneController {
    @FXML
    private Label packageLabel;
    @FXML
    private Label packageTypeLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label subcommandLabel;
    @Autowired
    private SceneLoader sceneLoader;

    @Override
    public void displayResult(String rawPackage, List<String> message) {
        packageLabel.setText(rawPackage);
        packageTypeLabel.setText(message.get(0));
        passwordLabel.setText(message.get(1));
        subcommandLabel.setText(message.get(2));
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
